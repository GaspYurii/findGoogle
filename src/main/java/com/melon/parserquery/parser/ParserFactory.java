package com.melon.parserquery.parser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ParserFactory {
    private Parser create(Searcher searcher) {
        if (Searcher.GOOGLE.equals(searcher)) {
            return new ParserGoogle();
        }

        if (Searcher.YAHOO.equals(searcher)) {
            return new ParserYahoo();
        }

        throw new IllegalArgumentException("Wrong searcher type: " + searcher);
    }

    public List<Parser> getParsers(Collection<Searcher> searchers) {
        return searchers.stream().parallel()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
