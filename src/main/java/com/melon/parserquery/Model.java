package com.melon.parserquery;

public class Model {
    private final String query;
    private final String resultStats;

    public Model(String query, String resultStats) {
        this.query = query;
        this.resultStats = resultStats;
    }

    public String getResultStats() {
        return resultStats;
    }

    public String getQuery() {
        return query;
    }

}
