package com.melon.parserquery.model;

import com.melon.parserquery.parser.Searchers;

public class SearchQueryDTO {
    private final String query;
    private final long resultCount;
    private final Searchers searcher;

    public SearchQueryDTO(String query, long resultCount, Searchers searcher) {
        this.query = query;
        this.resultCount = resultCount;
        this.searcher = searcher;
    }

    public String getQuery() {
        return query;
    }

    public long getResultCount() {
        return resultCount;
    }

    public Searchers getSearcher() { return searcher; }

}
