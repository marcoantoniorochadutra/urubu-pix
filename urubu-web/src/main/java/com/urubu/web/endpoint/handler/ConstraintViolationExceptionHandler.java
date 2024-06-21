package com.urubu.web.endpoint.handler;


import com.urubu.domain.constants.CoreReturnMessage;
import com.urubu.model.base.MessageDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException ex) {
		
		String msg = buildMessage(ex.getConstraintViolations());

        return Response.status(Response.Status.BAD_REQUEST)
				.entity(new MessageDto(msg))
				.type(MediaType.APPLICATION_JSON).build();
	}
	
	private String buildMessage(Set<ConstraintViolation<?>> violations) {
	
		StringBuilder sb = new StringBuilder();
		
		List<String> nullFields = new ArrayList<>();
		
		List<String> patternFields = new ArrayList<>();
		
		List<String> sizeFields = new ArrayList<>();
		
		List<String> numberFields = new ArrayList<>();
		
		for(ConstraintViolation<?> violation : violations){		
			if (!matchNullAnnotation(violation, nullFields) 
					&& !matchPatternAnnotation(violation, patternFields)
					&& !matchSizeAnnotaion(violation, sizeFields)
					&& !matchNumberAnnotaion(violation, numberFields)){
				sb.append(String.format(" %s ", violation.getMessageTemplate()));
			}			
		}
		
		if (!nullFields.isEmpty()){
			sb.append(String.format(" %s  %s.", CoreReturnMessage.NOT_NULL_MESSAGE, nullFields.toString()));
		}
		
		if (!patternFields.isEmpty()){
			sb.append(String.format(" %s  %s.", CoreReturnMessage.INVALID_ARGUMENTO, patternFields.toString()));
		}
		
		if (!sizeFields.isEmpty()){
			sb.append(String.format(" %s  %s.", CoreReturnMessage.INVALID_SIZE, sizeFields.toString()));
		}
		
		if (!numberFields.isEmpty()){
			sb.append(String.format(" %s  %s.", CoreReturnMessage.INVALID_ARGUMENTO, numberFields.toString()));
		}
		
		return sb.toString();
	}
	
	private boolean matchNullAnnotation(ConstraintViolation<?> violation, List<String> nullFields){
		boolean isNullAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof NotNull) {
			nullFields.add(violation.getPropertyPath().toString());
			isNullAnnotation = true;
		}
		return isNullAnnotation;
	}
	
	private boolean matchPatternAnnotation(ConstraintViolation<?> violation, List<String> patternFields){
		boolean isPatternAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof Pattern) {
			patternFields.add(violation.getPropertyPath().toString());
			isPatternAnnotation = true;
		}
		return isPatternAnnotation;
	}
	
	private boolean matchSizeAnnotaion(ConstraintViolation<?> violation, List<String> sizeFields){
		boolean isSizeAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof Size) {
			sizeFields.add(violation.getPropertyPath().toString());
			isSizeAnnotation = true;
		}
		return isSizeAnnotation;
	}
	
	private boolean matchNumberAnnotaion(ConstraintViolation<?> violation, List<String> numberFields){
		boolean isSizeAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof Min
				|| violation.getConstraintDescriptor().getAnnotation() instanceof Max) {
			numberFields.add(violation.getPropertyPath().toString());
			isSizeAnnotation = true;
		}
		return isSizeAnnotation;
	}


}
