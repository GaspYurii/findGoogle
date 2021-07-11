package com.melon.parserquery.parser;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.webutils.WebPageConnector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ParserYahoo implements Parser {
    private static final String YAHOO_SEARCH_URL = "https://search.yahoo.com/search?p=";
    private static final String STATISTIC_SELECTOR_USUAL = "div.compPagination > span";
    private static final String STATISTIC_SELECTOR_UNUSUAL = "div.compTitle.fc-smoke";
    private static final String REG_EX = "([0-9]{1,3},)+([0-9]{3})++";

    ParserYahoo() { /**/ }

    @Override
    public SearchQueryDTO getSearchQueryDTO(String query) throws IOException {
        return SearchQueryDTO.builder()
                .setQuery(query)
                .setResultCount(getResultStats(query))
                .setSearcher(Searchers.YAHOO)
                .build();
    }

    @Override
    public long getResultStats(String query) throws IOException {
        Document document = WebPageConnector.getDocument(YAHOO_SEARCH_URL + query);
        Element resultStats;
        resultStats = document.selectFirst(STATISTIC_SELECTOR_USUAL);

        if (resultStats == null) {
            resultStats = document.selectFirst(STATISTIC_SELECTOR_UNUSUAL);
        }

        return getResultCount(resultStats.text(), REG_EX);
    }

}
