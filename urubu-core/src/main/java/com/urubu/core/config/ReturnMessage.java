package com.urubu.core.config;

import com.urubu.core.constants.CoreReturnMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.ResourceBundle;

public class ReturnMessage {

    private static final String BUNDLE_PATH = "return_messages/messages";

    public static String getMessage(CoreReturnMessage msg) {
        return findMessage(msg);
    }

    public static String getMessageWithField(CoreReturnMessage msg, String field) {
        String message = findMessage(msg);
        return message + StringUtils.SPACE + field;
    }

    private static String findMessage(CoreReturnMessage msg) {
        String message;
        try {
            ResourceBundle rs = ResourceBundle.getBundle(BUNDLE_PATH, LocaleContext.getCurrentLocale());
            message = rs.getString(msg.name());
        } catch (Exception e) {
            e.printStackTrace();
            message = "UNDEFINED";
        }
        return message;
    }

}
