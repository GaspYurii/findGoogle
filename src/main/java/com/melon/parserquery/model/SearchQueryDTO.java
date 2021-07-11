package com.melon.parserquery.model;

import com.melon.parserquery.parser.Searchers;


public class SearchQueryDTO {
    private final String query;
    private final long resultCount;
    private final Searchers searcher;

    public SearchQueryDTO(Builder builder) {
        this.query = builder.query;
        this.resultCount = builder.resultStats;
        this.searcher = builder.searcher;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getQuery() {
        return query;
    }

    public long getResultCount() {
        return resultCount;
    }

    public Searchers getSearcher() { return searcher; }

    public static class Builder {
        private String query;
        private long resultStats;
        private Searchers searcher;

        public Builder setQuery(String input) {
            this.query = input;
            return this;
        }

        public Builder setResultCount(long resultStats) {
            this.resultStats = resultStats;
            return this;
        }

        public Builder setSearcher(Searchers searcher) {
            this.searcher = searcher;
            return this;
        }

        public SearchQueryDTO build() { return new SearchQueryDTO(this); }
    }

}
