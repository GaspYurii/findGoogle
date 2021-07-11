package com.melon.parserquery.parser;

public class ParserFactory {
    public Parser create(Searchers searcher) {
        Parser parser = null;

        if (searcher.equals(Searchers.GOOGLE)) { parser = new ParserGoogle(); }

        if (searcher.equals(Searchers.YAHOO))  { parser = new ParserYahoo(); }

        return parser;
    }
}
