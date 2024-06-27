package com.urubu.web.endpoint.filter;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.urubu.core.auth.JwtUtils;
import com.urubu.core.auth.LoginDto;
import com.urubu.web.authentication.LoginFactory;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;


@Provider
@PreMatching
public class PreMatchingFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_PARAM = "x-up-auth";
    private static final String AUTHORIZATION_HEADER = "up-auth";

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String token = getAuthorization(requestContext);
        if (StringUtils.isBlank(token)) {
            token = requestContext.getUriInfo().getQueryParameters().getFirst(AUTHENTICATION_PARAM);
        }

        if(StringUtils.isNotBlank(token)) {
            processToken(requestContext, token);
        }
    }

    private static String getAuthorization(ContainerRequestContext requestContext) {
        String auth = requestContext.getHeaderString(AUTHENTICATION_PARAM);
        return Objects.nonNull(auth) ? auth : requestContext.getHeaderString(AUTHORIZATION_HEADER);
    }

    private void processToken(ContainerRequestContext requestContext, String token) {
        LoginDto login = JwtUtils.getApplicationLogin(token);
        requestContext.setProperty(LoginFactory.LOGIN_PROPERTY, login);
    }
}
