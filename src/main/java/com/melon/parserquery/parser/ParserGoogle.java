package com.melon.parserquery.parser;

import com.melon.parserquery.webutils.WebPageConnector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserGoogle implements Parser{
    private static final String GOOGLE_SEARCH_URL = "https://www.google.ru/search?q=";
    private static final String RESULT_STATS_ID = "result-stats";
    private static final String REG_EX = "([0-9]{1,3},)+([0-9]{3})++";

    public ParserGoogle() { /**/ }

    @Override
    public long getResultStats(String query) throws IOException {
        Document document = WebPageConnector.getDocument(GOOGLE_SEARCH_URL + query);
        Element resultStats = document.getElementById(RESULT_STATS_ID);
        return getResultCount(resultStats.text());
    }

    private long getResultCount(String string) {
        String resultString ="";

        Pattern pattern = Pattern.compile(REG_EX);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            resultString = string.substring(matcher.start(), matcher.end());
        }
        resultString = resultString.replace(",", "");
        return Long.parseLong(resultString);
    }
}
