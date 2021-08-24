package com.melon.parserquery.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserCacheTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWhenParamsOfGetParserIsNull() {

        assertThrows(
                IllegalArgumentException.class,
                () -> ParserCache.getParser(null)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParamsOfGetParsersIsNull() {
        List<Searcher> empty = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> ParserCache.getParsers(empty)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParamsOfGetParsersIsEmpty() {
        List<Searcher> empty = new ArrayList<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> ParserCache.getParsers(empty)
        );
    }
}