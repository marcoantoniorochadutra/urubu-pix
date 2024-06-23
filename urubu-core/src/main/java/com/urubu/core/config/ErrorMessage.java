package com.urubu.core.config;

import com.urubu.core.constants.CoreReturnMessages;

import java.util.ResourceBundle;

public class ErrorMessage {

    private static final String BUNDLE_PATH = "error_messages/messages";

    public static String getMessage(CoreReturnMessages msg) {
        String message;
        try {
            ResourceBundle rs = ResourceBundle.getBundle(BUNDLE_PATH, LocaleContext.getCurrentLocale());
            message = rs.getString(msg.name());
        } catch (Exception e) {
            message = "UNDEFINED";
        }
        return message;
    }
}
