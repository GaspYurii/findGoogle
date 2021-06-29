package com.melon.parserquery.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class Parser {
    private String url;

    public Parser(String url) {
        this.url = url;
    }

    public Document getDocument() throws IOException {
            return Jsoup.connect(url).get();
    }

   public abstract void parse() throws IOException;
}
