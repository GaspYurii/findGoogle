package com.melon.parserquery.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class Parser {
    public Parser() {
    }

    public Document getDocument(String url) throws IOException {
            return Jsoup.connect(url).get();
    }

    public abstract String getResultStats(String query) throws IOException;
}
