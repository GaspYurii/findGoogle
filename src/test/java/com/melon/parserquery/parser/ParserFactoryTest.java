package com.melon.parserquery.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserFactoryTest {
    @Test
    void test() {
        ParserFactory factory = new ParserFactory();
        Searcher searcher = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(searcher)
        );
    }

}