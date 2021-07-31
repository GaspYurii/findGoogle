package com.melon.parserquery.parser;

import com.melon.parserquery.exception.ParserException;
import com.melon.parserquery.locale.LocaleConstants;
import com.melon.parserquery.locale.LocaleService;
import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.webutils.WebPageConnector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

public class ParserYahoo implements Parser {
    private static final Logger logger = LoggerFactory.getLogger(ParserYahoo.class);

    private static final String YAHOO_SEARCH_URL = "https://search.yahoo.com/search?p=";
    private static final String STATISTIC_SELECTOR_USUAL = "div.compPagination > span";
    private static final String STATISTIC_SELECTOR_UNUSUAL = "div.compTitle.fc-smoke";

    ParserYahoo() { /**/ }

    @Override
    public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) {
        return SearchQueryDTO.builder()
                .setQuery(query)
                .setResultCount(getResultStats(query, locale))
                .setSearcher(Searcher.YAHOO)
                .build();
    }

    @Override
    public long getResultStats(String query, Locale locale) {
        try {
            Document document = WebPageConnector.getDocument(YAHOO_SEARCH_URL + query, locale);
            Element resultStats = document.selectFirst(STATISTIC_SELECTOR_USUAL);

            if (resultStats == null) {
                resultStats = document.selectFirst(STATISTIC_SELECTOR_UNUSUAL);
            }

            Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));

            long resultCount = getResultCount(
                    resultStats.text(),
                    pattern,
                    LocaleService.getValue(LocaleConstants.DELIMITER, locale));
            logger.info("{}: About [{}] results in location [{}] for query: [{}]",
                    Searcher.YAHOO, resultCount, locale, query);
            return resultCount;
        } catch (IOException e) {
            throw new ParserException();
        }
    }

}
