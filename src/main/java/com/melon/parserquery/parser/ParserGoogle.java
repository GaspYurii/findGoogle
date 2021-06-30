package com.melon.parserquery.parser;

import com.melon.parserquery.Constants;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ParserGoogle extends Parser{

    Element resultStats;

    public ParserGoogle() { }

    @Override
    public String getResultStats(String query) throws IOException {
        Document document = getDocument("https://www.google.ru/search?q=" + query);
        resultStats = document.getElementById(Constants.RESULT_STATS);
        return resultStats.text();
    }
}
