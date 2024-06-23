package com.urubu.core.config;

import java.util.Locale;

public class LocaleContext {

    private static final ThreadLocal<Locale> currentLocale = ThreadLocal.withInitial(LocaleContext::defaultLocale);

    public static Locale defaultLocale() {
        return new Locale("en", "US");
    }

    public static void setCurrentLocale(Locale tenant) {
        currentLocale.set(tenant);
    }

    public static Locale getCurrentLocale() {
        return currentLocale.get();
    }

}
