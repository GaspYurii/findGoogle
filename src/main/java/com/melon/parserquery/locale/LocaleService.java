package com.melon.parserquery.locale;

import com.melon.parserquery.ApplicationConstants;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleService {

    private LocaleService() {
    }

    public static String getValue(String key, String baseName, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale).getString(key);
    }

    public static String getValue(String key) {
        return getValue(key, ApplicationConstants.DEFAULT_LOCALE_BASE_NAME, Locale.getDefault());
    }

    public static String getValue(String key, Locale locale) {
        return getValue(key, ApplicationConstants.DEFAULT_LOCALE_BASE_NAME, locale);
    }
}
