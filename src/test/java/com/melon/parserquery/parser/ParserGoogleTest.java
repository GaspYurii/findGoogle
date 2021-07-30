package com.melon.parserquery.parser;

import com.melon.parserquery.model.SearchQueryDTO;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserGoogleTest {

    @Test
    void getSearchQueryDTO() {
        Parser parser = new ParserGoogle();
        assertEquals(parser.getSearchQueryDTO("", Locale.UK).getClass(), SearchQueryDTO.class);
    }

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
        assertEquals(1364973L, parser.getResultCount(
                "1,364,973",
                Pattern.compile("([0-9]{1,3},)+([0-9]{3})++"),
                ",")
        );
    }

    @Test
    void parseFromStringRU() {
        Parser parser = new ParserGoogle();
        assertEquals(1364973L, parser.getResultCount(
                "1 364 973",
                Pattern.compile("([0-9]{1,3} )+([0-9]{3})++"),
                " ")
        );
    }
}