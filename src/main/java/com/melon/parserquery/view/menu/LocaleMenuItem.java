package com.melon.parserquery.view.menu;

import java.util.Locale;

public enum LocaleMenuItem {
    EN_UK("1", Locale.UK, "english"),
    RU_RU("2", new Locale("ru", "RU"), "русский");

    private final String key;
    private final Locale locale;
    private final String langName;

    LocaleMenuItem(String key, Locale locale, String langName) {
        this.key = key;
        this.locale = locale;
        this.langName = langName;
    }

    public static Locale getLocaleByKey(String key) {
        for (LocaleMenuItem item : LocaleMenuItem.values()) {
            if (item.key.equals(key)) {
                return item.getLocale();
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLangName() {
        return langName;
    }

    @Override
    public String toString() {
        return "   " + getKey() + " - " + getLangName();
    }
}
