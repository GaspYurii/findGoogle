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
    private static final ParserTestImpl parser = new ParserTestImpl();

    @Test
    void shouldParseLongFromStringRu() {
        long expected = 1234567890L;
        String valueToParse = "1 234 567 890";
        Parser parser = new ParserTestImpl();
        Locale locale = new Locale("ru", "RU");

        long actual = parser.getResultCount(
                valueToParse,
                Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale)),
                LocaleService.getValue(LocaleConstants.DELIMITER, locale)
        );

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(value = ValueToParse.class)
    void shouldParseLongFromStringRuWithoutDelimiter(ValueToParse valueToParse) {
        long expected = valueToParse.expected;
        Parser parser = new ParserTestImpl();
        Locale locale = new Locale("ru", "RU");

        long actual = parser.getResultCount(
                valueToParse.getValueToParse(),
                Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale)),
                LocaleService.getValue(LocaleConstants.DELIMITER, locale)
        );

        assertEquals(expected, actual);
    }


    @Test
    void shouldParseLongFromStringUk() {
        long expected = 1234567890L;
        String valueToParse = "1,234,567,890";
        Parser parser = new ParserTestImpl();
        Locale locale = Locale.UK;

        long actual = parser.getResultCount(
                valueToParse,
                Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale)),
                LocaleService.getValue(LocaleConstants.DELIMITER, locale)
        );

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @EnumSource(value = ValueToParse.class)
    void shouldParseLongFromStringUkWithoutDelimiter(ValueToParse valueToParse) {
        long expected = valueToParse.getExpected();
        Parser parser = new ParserTestImpl();
        Locale locale = Locale.UK;

        long actual = parser.getResultCount(
                valueToParse.getValueToParse(),
                Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale)),
                LocaleService.getValue(LocaleConstants.DELIMITER, locale)
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenStringToParseIsEmpty() {
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX));
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.getResultCount("", pattern, ",")
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenPatternIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.getResultCount("1", null, ",")
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDelimiterIsEmpty() {
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX));
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.getResultCount("1", pattern, "")
        );
    }

    private enum ValueToParse {
        ONE_DIGIT(1L, "1"),
        TWO_DIGITS(22L, "22"),
        THREE_DIGITS(999L, "999");

        private final long expected;
        private final String valueToParse;

        ValueToParse(long expected, String valueToParse) {
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

    private static class ParserTestImpl implements Parser {
        @Override
        public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) {
            return null;
        }

        @Override
        public long getResultStats(String query, Locale locale) {
            return 0;
        }

        private ParserTestImpl() {
        }
    }
}