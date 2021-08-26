package com.melon.parserquery.locale;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocaleServiceTest {
    @Test
    void shouldReturnDelimiterFromUkLocale() {
        String expected = ",";
        String property = LocaleConstants.DELIMITER;

        String actual = LocaleService.getValue(property, Locale.UK);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnDelimiterFromRuLocale() {
        String expected = " ";
        String property = LocaleConstants.DELIMITER;

        String actual = LocaleService.getValue(property, new Locale("ru", "RU"));

        assertEquals(expected, actual);
    }

}