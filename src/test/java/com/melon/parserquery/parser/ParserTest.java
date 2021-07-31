package com.melon.parserquery.parser;

import com.melon.parserquery.locale.LocaleService;
import com.melon.parserquery.model.SearchQueryDTO;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void shouldParseLongFromStringRU() {
        long expected = 1234567890L;
        String valueToParse = "1 234 567 890";
        Parser parser = new ParserTestImpl();
        Locale locale = new Locale("ru", "RU");

        long actual = parser.getResultCount(
                valueToParse,
                Pattern.compile(LocaleService.getValue("number_reg_ex.regexp", locale)),
                LocaleService.getValue("delimiter", locale)
        );

        assertEquals(expected, actual);
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
    }

}