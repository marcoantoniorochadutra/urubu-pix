package com.urubu.web.authentication;

import com.urubu.core.auth.LoginDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.glassfish.hk2.api.Factory;

public class LoginFactory implements Factory<LoginDto> {

    public static final String LOGIN_PROPERTY = "login";

    private final ContainerRequestContext context;

    @Inject
    public LoginFactory(ContainerRequestContext context) {
        this.context = context;
    }

    @Override
    public void dispose(LoginDto login) {}

    @Override
    public LoginDto provide() {
        return (LoginDto) context.getProperty(LOGIN_PROPERTY);
    }
}
