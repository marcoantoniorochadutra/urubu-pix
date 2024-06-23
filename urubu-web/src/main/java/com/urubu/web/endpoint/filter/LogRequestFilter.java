package com.urubu.web.endpoint.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class LogRequestFilter implements ContainerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(LogRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
         log.info("Requisição Recebida | URL: {} | HEADERS: {} | QUERY: {}", requestContext.getUriInfo().getPath(), requestContext.getHeaders(), requestContext.getUriInfo().getQueryParameters());
    }
}