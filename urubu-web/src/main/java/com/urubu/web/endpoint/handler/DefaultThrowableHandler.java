package com.urubu.web.endpoint.handler;

import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.model.base.MessageDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;


public class DefaultThrowableHandler implements ExceptionMapper<Throwable> {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingResponseFilter.class);

    @Override
    public Response toResponse(Throwable exception) {
        log.error("INTERNAL ERROR - CAUSE: {} {} {}", exception, System.lineSeparator(),
                Arrays.stream(exception.getStackTrace())
                        .map(Object::toString)
                        .collect(Collectors.joining(System.lineSeparator())) );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new MessageDto(ReturnMessage.getMessage(CoreReturnMessage.INTERNAL_ERROR)))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}