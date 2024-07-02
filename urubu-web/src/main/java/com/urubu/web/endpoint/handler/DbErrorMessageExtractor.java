package com.urubu.web.endpoint.handler;

import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DbErrorMessageExtractor {

	private final static Logger log = LoggerFactory.getLogger(DbErrorMessageExtractor.class);

	public static final int DB_DUPLICATE_KEY = 1062;
	public static final int DB_PARENT_KEY = 1451;
	public static final int DB_PARENT_NOT_FOUND = 1452;
	public static final int DB_FIELD_NO_NULL = 1048;
	public static final int DB_DATA_TOO_LONG = 1406;
	public static final int DB_FOREIGN_KEY_FAIL = 1216;

	public static String transformDBException(int sqlErrorCode, String sqlErrorMsg) {
		switch (sqlErrorCode) {
		case DB_DUPLICATE_KEY:
			return getDuplicatedFieldMessage(sqlErrorMsg);
		case DB_PARENT_KEY:
			return getParentFieldMessage(sqlErrorMsg);
		case DB_FIELD_NO_NULL:
			return getFieldNotNullMessage(sqlErrorMsg);
		case DB_DATA_TOO_LONG:
			return getDataTooLongMessage(sqlErrorMsg);
		case DB_FOREIGN_KEY_FAIL:
			return getForeignKeyMessage(sqlErrorMsg);
		case DB_PARENT_NOT_FOUND:
			return getParentNotFoundFieldMessage(sqlErrorMsg);
		default:
			log.warn("SQL error code not mapped {} {}", sqlErrorCode, sqlErrorMsg);
			return null;
		}

	}

	private static String getDataTooLongMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("column '") + 8;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() - 1);
		int endIndex = keyName.indexOf("' at");
		String fieldName = keyName.substring(0, endIndex);
		return String.format("%s [ %s ].", ReturnMessage.getMessage(CoreReturnMessage.INVALID_SIZE), fieldName);
	}

	private static String getFieldNotNullMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("Column '") + 8;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() - 1);
		int endIndex = keyName.indexOf("'");
		String fieldName = keyName.substring(0, endIndex);
		return String.format("%s [ %s ].", ReturnMessage.getMessage(CoreReturnMessage.NOT_NULL_MESSAGE), fieldName);
	}

	private static String getDuplicatedFieldMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("entry '") + 7;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() - 1);
		int endIndex = keyName.indexOf("'");
		String fieldValue = keyName.substring(0, endIndex);
		return String.format("%s [ %s ].", ReturnMessage.getMessage(CoreReturnMessage.DUPLICATED_ERROR), fieldValue);
	}
	
	private static String getParentNotFoundFieldMessage(String sqlMsg) {
		int firstIndex = sqlMsg.indexOf("REFERENCES");
		String keyName = sqlMsg.substring(firstIndex, sqlMsg.length() - 1);
		int startIndex = keyName.indexOf("`");
		int endIndex = keyName.indexOf("` (");
		String fieldValue = keyName.substring(startIndex + 1, endIndex);
		return String.format("%s [ %s ].", ReturnMessage.getMessage(CoreReturnMessage.NO_RECORD_FOUND), fieldValue);
	}

	private static String getForeignKeyMessage(String sqlMsg) {
		return ReturnMessage.getMessage(CoreReturnMessage.INVALID_ARGUMENT);
	}

	private static String getParentFieldMessage(String sqlMsg) {
		return "Registro possui outros registros vinculados";
	}

}
