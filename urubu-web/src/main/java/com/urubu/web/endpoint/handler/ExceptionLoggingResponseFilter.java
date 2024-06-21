package com.urubu.web.endpoint.handler;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExceptionLoggingResponseFilter implements ContainerResponseFilter {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingResponseFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        if (responseContext.getStatus() != HttpStatus.SC_OK
                && responseContext.getStatus() != HttpStatus.SC_CREATED
                && responseContext.getStatus() != HttpStatus.SC_NO_CONTENT){
            log.info("USER EXCEPTION MESSAGE: {}", responseContext.getEntity());
        }
    }


}
