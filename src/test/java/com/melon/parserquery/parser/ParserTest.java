package com.melon.parserquery.parser;

import com.melon.parserquery.locale.LocaleConstants;
import com.melon.parserquery.locale.LocaleService;
import com.melon.parserquery.model.SearchQueryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    private static final Parser parser = new ParserTestImpl();

    @Test
    void shouldParseLongFromStringRu() {
        long expected = 1234567890L;
        String valueToParse = "1 234 567 890";
        Parser parser = new ParserTestImpl();
        Locale locale = new Locale("ru", "RU");
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));
        String delimiter = LocaleService.getValue(LocaleConstants.DELIMITER, locale);

        long actual = parser.parseLongResultCount(valueToParse, pattern, delimiter);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(value = ValueWithoutDelimiter.class)
    void shouldParseLongFromStringRuWithoutDelimiter(ValueWithoutDelimiter valueWithoutDelimiter) {
        long expected = valueWithoutDelimiter.expected;
        String valueToParse = valueWithoutDelimiter.getValueToParse();
        Parser parser = new ParserTestImpl();
        Locale locale = new Locale("ru", "RU");
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));
        String delimiter = LocaleService.getValue(LocaleConstants.DELIMITER, locale);

        long actual = parser.parseLongResultCount(valueToParse, pattern, delimiter);

        assertEquals(expected, actual);
    }

    @Test
    void shouldParseLongFromStringUk() {
        long expected = 1234567890L;
        String valueToParse = "1,234,567,890";
        Locale locale = Locale.UK;
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));
        String delimiter = LocaleService.getValue(LocaleConstants.DELIMITER, locale);

        long actual = parser.parseLongResultCount(valueToParse, pattern, delimiter);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(value = ValueWithoutDelimiter.class)
    void shouldParseLongFromStringUkWithoutDelimiter(ValueWithoutDelimiter valueWithoutDelimiter) {
        long expected = valueWithoutDelimiter.getExpected();
        String valueToParse = valueWithoutDelimiter.getValueToParse();
        Locale locale = Locale.UK;
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));
        String delimiter = LocaleService.getValue(LocaleConstants.DELIMITER, locale);

        long actual = parser.parseLongResultCount(valueToParse, pattern, delimiter);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(value = ParamsOfGetResultCount.class)
    void shouldThrowIllegalArgumentExceptionWhenParamsOfGetResultCountIsNullOrEmpty(
            ParamsOfGetResultCount params) {

        String valueToParse = params.getValueToParse();
        Pattern pattern = params.getPattern();
        String delimiter = params.getDelimiter();

        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parseLongResultCount(valueToParse, pattern, delimiter)
        );
    }

    private enum ValueWithoutDelimiter {
        ONE_DIGIT(1L, "1"),
        TWO_DIGITS(22L, "22"),
        THREE_DIGITS(999L, "999");

        private final long expected;
        private final String valueToParse;

        ValueWithoutDelimiter(long expected, String valueToParse) {
            this.expected = expected;
            this.valueToParse = valueToParse;
        }

        public long getExpected() {
            return expected;
        }

        public String getValueToParse() {
            return valueToParse;

        }
    }

    private enum ParamsOfGetResultCount {
        VALUE_IS_EMPTY("", Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX)), ","),
        VALUE_IS_NULL(null, Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX)), ","),
        PATTERN_IS_NULL("1", null, ","),
        DELIMITER_IS_EMPTY("1", Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX)), ""),
        DELIMITER_IS_NULL("1", Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX)), null);

        private final String valueToParse;
        private final Pattern pattern;
        private final String delimiter;

        ParamsOfGetResultCount(String valueToParse, Pattern pattern, String delimiter) {
            this.valueToParse = valueToParse;
            this.pattern = pattern;
            this.delimiter = delimiter;
        }

        public String getValueToParse() {
            return valueToParse;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public String getDelimiter() {
            return delimiter;
        }
    }

    private static class ParserTestImpl implements Parser {

        private ParserTestImpl() {
        }

        @Override
        public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) {
            return null;
        }

        @Override
        public long getResultCount(String query, Locale locale) {
            return 0;
        }

        @Override
        public String getResultStats(String query, Locale locale) {
            return "0";
        }

    }


}