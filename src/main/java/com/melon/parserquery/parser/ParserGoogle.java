package com.melon.parserquery.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ParserGoogle extends Parser{

    private Document document;
    Element resultStats;

    public ParserGoogle(String query) {
        super ("https://www.google.ru/search?q=" + query);
    }

    @Override
    public void parse() throws IOException { document = getDocument(); }

    public String getResultStats() throws IOException {
        parse();
        resultStats = document.getElementById("result-stats");
        return resultStats.text();
    }
}
