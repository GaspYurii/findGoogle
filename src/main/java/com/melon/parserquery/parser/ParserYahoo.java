package com.melon.parserquery.parser;

import com.melon.parserquery.webutils.WebPageConnector;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserYahoo implements Parser {
    private static final String YAHOO_SEARCH_URL = "https://search.yahoo.com/search?p=";
    private static final String COMP_PAGINATION = "compPagination";
    private static final String TITLE = "title";
    private static final String REG_EX = "([0-9]{1,3},)+([0-9]{3})++";

    public ParserYahoo() { /**/ }

    @Override
    public long getResultStats(String query) throws IOException {
        Document document = WebPageConnector.getDocument(YAHOO_SEARCH_URL + query);
        Elements resultStats;
        String resultClass;
        if (document.hasClass(TITLE)) {
            resultClass = TITLE;
            System.out.println("Old Yahoo");
        } else {
            resultClass = COMP_PAGINATION;
        }
        resultStats = document.getElementsByClass(resultClass);
        return getResultCount(resultStats.text(), document);
    }

    private long getResultCount(String string, Document document) {
        String resultString ="";
        long result = 0;
        Pattern pattern = Pattern.compile(REG_EX);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            resultString = string.substring(matcher.start(), matcher.end());
        }
        resultString = resultString.replace(",", "");
        try {
            result = Long.parseLong(resultString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //System.out.println(document);
        }
        return result;
    }

}
