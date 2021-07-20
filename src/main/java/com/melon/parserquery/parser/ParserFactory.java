package com.melon.parserquery.parser;

public class ParserFactory {
    public Parser create(Searcher searcher) {
        if (Searcher.GOOGLE.equals(searcher)) {
            return new ParserGoogle();
        }

        if (Searcher.YAHOO.equals(searcher)) {
            return new ParserYahoo();
        }

        throw new IllegalArgumentException("Wrong searcher type: " + searcher);
    }
}
