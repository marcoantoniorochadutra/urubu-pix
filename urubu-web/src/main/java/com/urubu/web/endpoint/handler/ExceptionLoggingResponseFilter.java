package com.urubu.web.endpoint.handler;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


public class ExceptionLoggingResponseFilter implements ContainerResponseFilter {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingResponseFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        if (responseContext.getStatus() != HttpStatus.OK.value()
                && responseContext.getStatus() != HttpStatus.CREATED.value()
                && responseContext.getStatus() != HttpStatus.NO_CONTENT.value()){
            log.info("USER EXCEPTION MESSAGE: {}", responseContext.getEntity());
        }
    }


}
