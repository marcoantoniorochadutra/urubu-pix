package com.urubu.web.endpoint;

import com.urubu.core.auth.LoginOrigin;
import com.urubu.core.auth.LoginWeb;
import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.core.constants.SystemConstants;
import com.urubu.core.exceptions.LoginException;
import com.urubu.core.exceptions.ref.LoginError;
import com.urubu.model.auth.AccountAcessDto;
import com.urubu.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

@Path("/v1/auth")
public class AuthenticationController extends AbstractController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public LoginWeb login(@Context UriInfo uriInfo,
                          @Context HttpServletRequest httpServletRequest,
                          @HeaderParam("authorization") String authorization) {

        AccountAcessDto access = getAuthorizationHeader(authorization);
        LoginOrigin loginOrigin = buildOrigin(httpServletRequest);
        return authenticationService.login(access, loginOrigin);
    }

    private AccountAcessDto getAuthorizationHeader(String authorization) {
        if (StringUtils.isEmpty(authorization)) {
            throw new LoginException(ReturnMessage.getMessage(CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR), LoginError.TOKEN);
        }
        byte[] decoded = Base64.getDecoder().decode(authorization);
        String[] divided = new String(decoded).split(SystemConstants.DEFAULT_DIVISOR);
        System.err.println(divided[0]);
        if (divided.length < 1) {
            throw new LoginException(ReturnMessage.getMessage(CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR), LoginError.TOKEN);
        }
        return new AccountAcessDto(divided[0], divided[1]);
    }

}
