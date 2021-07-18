package com.melon.parserquery.parser;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.webutils.WebPageConnector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

public class ParserYahoo implements Parser {
    private static final String YAHOO_SEARCH_URL = "https://search.yahoo.com/search?p=";
    private static final String STATISTIC_SELECTOR_USUAL = "div.compPagination > span";
    private static final String STATISTIC_SELECTOR_UNUSUAL = "div.compTitle.fc-smoke";
    private static final String REG_EX = "([0-9]{1,3},)+([0-9]{3})++";
    private static final Pattern pattern = Pattern.compile(REG_EX);

    ParserYahoo() { /**/ }

    @Override
    public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) throws IOException {
        return SearchQueryDTO.builder()
                .setQuery(query)
                .setResultCount(getResultStats(query, locale))
                .setSearcher(Searcher.YAHOO)
                .build();
    }

    @Override
    public long getResultStats(String query, Locale locale) throws IOException {
        Document document = WebPageConnector.getDocument(YAHOO_SEARCH_URL + query, locale);
        Element resultStats;
        resultStats = document.selectFirst(STATISTIC_SELECTOR_USUAL);

        if (resultStats == null) {
            resultStats = document.selectFirst(STATISTIC_SELECTOR_UNUSUAL);
        }

        return getResultCount(resultStats.text(), pattern, ",");
    }

}
