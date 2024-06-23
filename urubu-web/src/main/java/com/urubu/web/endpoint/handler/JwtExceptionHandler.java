package com.urubu.web.endpoint.handler;


import com.urubu.model.base.MessageDto;
import io.jsonwebtoken.JwtException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class JwtExceptionHandler implements ExceptionMapper<JwtException> {

    private static final Logger log = LoggerFactory.getLogger(JwtExceptionHandler.class);

    @Override
    public Response toResponse(JwtException exception) {
        log.warn("JWT EXCEPTION: {}", exception.getMessage());
//        String errorMsg = CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR + StringUtils.SPACE + CoreReturnMessage.TOKEN_ERROR;
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new MessageDto("errorMsg"))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
