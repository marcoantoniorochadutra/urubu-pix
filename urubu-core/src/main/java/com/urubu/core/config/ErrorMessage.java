package com.urubu.core.config;

import com.urubu.core.constants.CoreReturnMessages;

import java.util.ResourceBundle;

public class ErrorMessage {

    private static final String BUNDLE_PATH = "return_messages/messages";

    public static String getMessage(CoreReturnMessages msg) {
        return findMessage(msg);
    }

    public static String getMessageWithField(CoreReturnMessages msg) {
        String message = findMessage(msg);
        return message + " %s";
    }

    private static String findMessage(CoreReturnMessages msg) {
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
