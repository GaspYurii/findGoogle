package com.melon.parserquery.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserFactoryTest {

    @Test
    void createGoogleParser() {
        Parser googleParser = new ParserFactory().create(Searcher.GOOGLE);
        assertEquals(ParserGoogle.class, googleParser.getClass());
    }

    @Test
    void createYahooParser() {
        Parser googleParser = new ParserFactory().create(Searcher.YAHOO);
        assertEquals(ParserYahoo.class, googleParser.getClass());
    }
}