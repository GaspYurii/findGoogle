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
        if (searcher == null) {
            throw new IllegalArgumentException("Parameter shouldn't be null");
        }
        return cache.computeIfAbsent(searcher, k -> factory.create(searcher));
    }

    public static List<Parser> getParsers(Collection<Searcher> searchers) {
        if (searchers == null || searchers.isEmpty()) {
            throw new IllegalArgumentException("Parameter shouldn't be null or empty");
        }
        return searchers.stream()
                .map(ParserCache::getParser)
                .collect(Collectors.toList());
    }
}
