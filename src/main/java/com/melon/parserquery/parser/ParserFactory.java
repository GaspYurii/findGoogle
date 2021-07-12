package com.melon.parserquery.parser;

public class ParserFactory {
    public Parser create(Searchers searcher) {
        if (Searchers.GOOGLE.equals(searcher)) {
            return new ParserGoogle();
        }

        if (Searchers.YAHOO.equals(searcher))  {
            return new ParserYahoo();
        }

        throw new IllegalArgumentException("Wrong searcher type: " + searcher);
    }
}
