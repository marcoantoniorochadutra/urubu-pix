package com.urubu.web.endpoint.handler;

import java.sql.SQLIntegrityConstraintViolationException;


import com.urubu.model.base.MessageDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;


@Provider
public class DataIntegrityViolationExceptionHandler implements ExceptionMapper<DataIntegrityViolationException> {

	private final static Logger log = LoggerFactory.getLogger(DataIntegrityViolationExceptionHandler.class);
	
	@Override
	public Response toResponse(DataIntegrityViolationException ex) {
		Response response = null;
		
		Throwable cause = ex.getCause();
		
		if (cause instanceof JDBCException) {
			JDBCException jdbcException = (JDBCException) cause;
			response = DbErrorMessageTransformer.transformDBException(jdbcException.getErrorCode(), jdbcException.getCause().getMessage());
		} else if (cause instanceof SQLIntegrityConstraintViolationException) {
			SQLIntegrityConstraintViolationException sqlIntegrityException = (SQLIntegrityConstraintViolationException) cause;
			response = DbErrorMessageTransformer.transformDBException(sqlIntegrityException.getErrorCode(), sqlIntegrityException.getMessage());
		} else {
			log.warn("Database exception not mapped {} ", ex.getMessage(), ex);
			
			response = Response.status(Response.Status.BAD_REQUEST)
						.entity(new MessageDto(ex.getMessage()))
						.type(MediaType.APPLICATION_JSON).build();
		}
		return response;
	}
	
	
}
