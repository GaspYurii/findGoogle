package com.melon.parserquery.parser;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParserCache {
    private static final Map<Searcher, Parser> cache = new ConcurrentHashMap<>();
    private static final ParserFactory factory = new ParserFactory();

    private ParserCache() {
    }

    public static Parser getParser(Searcher searcher) {
        return cache.computeIfAbsent(searcher, k -> factory.create(searcher));
    }

    public static List<Parser> getParsers(Collection<Searcher> searchers) {
        return searchers.stream().parallel()
                .map(ParserCache::getParser)
                .collect(Collectors.toList());
    }
}
