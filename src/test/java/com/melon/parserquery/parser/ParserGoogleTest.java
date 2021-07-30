package com.melon.parserquery.parser;

import com.melon.parserquery.LocaleService;
import com.melon.parserquery.model.SearchQueryDTO;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserGoogleTest {

    @Test
    void getResultStats() {
        SearchQueryDTO.Builder builder = new SearchQueryDTO.Builder();
        SearchQueryDTO model = builder
                .setQuery("java")
                .setSearcher(Searcher.GOOGLE)
                .setResultCount(1000L)
                .build();
        assertEquals(1000L, model.getResultCount());
    }

    @Test
    void parseFromStringEN() {
        Parser parser = new ParserGoogle();
        Locale locale = Locale.UK;
        assertEquals(1364973L, parser.getResultCount(
                "1,364,973",
                Pattern.compile(LocaleService.getString("number_reg_ex.regexp", locale)),
                LocaleService.getString("delimiter", locale))
        );
    }

    @Test
    void parseFromStringRU() {
        Parser parser = new ParserGoogle();
        Locale locale = new Locale("ru", "RU");
        assertEquals(1364973L, parser.getResultCount(
                "1 364 973",
                Pattern.compile(LocaleService.getString("number_reg_ex.regexp", locale)),
                LocaleService.getString("delimiter", locale))
        );
    }
}