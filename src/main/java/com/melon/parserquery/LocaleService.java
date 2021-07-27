package com.melon.parserquery;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleService {
    private LocaleService() {
    }

    public static String getString(String string) {
        return ResourceBundle.getBundle("lang").getString(string);
    }

    public static String getString(String string, Locale locale) {
        return ResourceBundle.getBundle("lang", locale).getString(string);
    }
}
