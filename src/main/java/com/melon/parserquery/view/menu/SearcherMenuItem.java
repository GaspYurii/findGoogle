package com.melon.parserquery.view.menu;

import com.melon.parserquery.parser.Searcher;

public enum SearcherMenuItem {
    ALL("0", Searcher.values()),
    GOOGLE("1", new Searcher[]{Searcher.GOOGLE}),
    YAHOO("2", new Searcher[]{Searcher.YAHOO});

    private final String key;
    private final Searcher[] searchers;

    SearcherMenuItem(String key, Searcher[] searchers) {
        this.key = key;
        this.searchers = searchers;
    }

    public static Searcher[] getSearcherByKey(String key) {
        for (SearcherMenuItem item : SearcherMenuItem.values()) {
            if (item.getKey().equals(key)) {
                return item.getSearchers();
            }
        }
        return new Searcher[0];
    }

    public String getKey() {
        return key;
    }

    public Searcher[] getSearchers() {
        return searchers;
    }
}
