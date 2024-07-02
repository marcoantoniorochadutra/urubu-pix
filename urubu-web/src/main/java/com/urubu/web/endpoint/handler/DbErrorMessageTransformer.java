package com.urubu.web.endpoint.handler;


import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.model.base.MessageDto;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbErrorMessageTransformer {
	
	private final static Logger log = LoggerFactory.getLogger(DbErrorMessageTransformer.class);
	
	public static final int DB_DUPLICATE_KEY = 1062;
	public static final int DB_PARENT_KEY = 1451;
	public static final int DB_PARENT_NOT_FOUND = 1452;
	public static final int DB_FIELD_NO_NULL = 1048;
	public static final int DB_DATA_TOO_LONG = 1406;
	public static final int DB_FOREIGN_KEY_FAIL = 1216;
	
	public static Response transformDBException(int sqlErrotCode, String sqlErrorMsg) {
		switch (sqlErrotCode) {
			case DB_DUPLICATE_KEY:
				return Response.status(Status.CONFLICT)
					.entity(new MessageDto(getDuplicatedFieldMessage(sqlErrorMsg)))
					.type(MediaType.APPLICATION_JSON).build();
			case DB_PARENT_KEY:
				return Response.status(Status.BAD_REQUEST)
						.entity(new MessageDto(getParentFieldMessage(sqlErrorMsg)))
						.type(MediaType.APPLICATION_JSON).build();
			case DB_FIELD_NO_NULL: 
				return Response.status(Status.BAD_REQUEST)
						.entity(new MessageDto(getFieldNotNullMessage(sqlErrorMsg)))
						.type(MediaType.APPLICATION_JSON).build();
			case DB_DATA_TOO_LONG:
				return Response.status(Status.BAD_REQUEST)
						.entity(new MessageDto(getDataTooLongMessage(sqlErrorMsg)))
						.type(MediaType.APPLICATION_JSON).build();
			case DB_FOREIGN_KEY_FAIL:
				return Response.status(Status.BAD_REQUEST)
						.entity(new MessageDto(getForeignKeyMessage(sqlErrorMsg)))
						.type(MediaType.APPLICATION_JSON).build();
			default:
				log.warn("SQL error code not mapped {} {}", sqlErrotCode, sqlErrorMsg);
				
				return Response.status(Status.BAD_REQUEST)
						.entity(new MessageDto(sqlErrorMsg))
						.type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	public static Response transformEntityNotFoundException(String javaxError) {
		String id = javaxError.substring(javaxError.lastIndexOf(StringUtils.SPACE), javaxError.length());
		
		int entityStartOf = javaxError.indexOf("jpa.entity.") + 11;
		String entity = javaxError.substring(entityStartOf, javaxError.indexOf(StringUtils.SPACE, entityStartOf));
		
		return Response.status(Status.NOT_FOUND)
				.entity(new MessageDto(String.format(ReturnMessage.getMessage(CoreReturnMessage.NO_RECORD_FOUND), entity, id)))
						.type(MediaType.APPLICATION_JSON).build();
	}
	

	private static String getDataTooLongMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("column '") + 8;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() -1);
		int endIndex = keyName.indexOf("' at");
		String fieldName = keyName.substring(0,endIndex);
		return String.format("%s [ %s ].", CoreReturnMessage.INVALID_SIZE, fieldName);
	}

	private static String getFieldNotNullMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("Column '") + 8;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() -1);
		int endIndex = keyName.indexOf("'");
		String fieldName = keyName.substring(0, endIndex);
		return String.format("%s [ %s ].", CoreReturnMessage.NOT_NULL_MESSAGE, fieldName);
	}

	private static String getDuplicatedFieldMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("entry '") + 7;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() -1);
		int endIndex = keyName.indexOf("'");
		String fieldValue = keyName.substring(0, endIndex);
		return String.format("%s [ %s ].", ReturnMessage.getMessageWithField(CoreReturnMessage.DUPLICATED_ERROR), fieldValue);
	}
	
	private static String getForeignKeyMessage(String sqlMsg) {
		return ReturnMessage.getMessage(CoreReturnMessage.INVALID_ARGUMENT);
	}
	
	private static String getParentFieldMessage(String sqlMsg){
		return "Registro possui outros registros vinculados";
	}

}
