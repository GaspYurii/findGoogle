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

    private final WebPageConnector webPageConnector;

    ParserYahoo(WebPageConnector webPageConnector) {
        this.webPageConnector = webPageConnector;
    }

    @Override
    public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) {
        return SearchQueryDTO.builder()
                .setQuery(query)
                .setResultCount(getResultCount(query, locale))
                .setSearcher(Searcher.YAHOO)
                .build();
    }

    @Override
    public String getResultStats(String query, Locale locale) {
        try {
            Document document = webPageConnector.getDocument(YAHOO_SEARCH_URL + query, locale);
            Element resultStats = document.selectFirst(STATISTIC_SELECTOR_USUAL);

            if (resultStats == null) {
                resultStats = document.selectFirst(STATISTIC_SELECTOR_UNUSUAL);
            }
            logger.info("{}: About [{}] results in location [{}] for query: [{}]",
                    Searcher.GOOGLE, resultStats.text(), locale, query);
            return resultStats.text();
        } catch (IOException e) {
            throw new ParserException();
        }
    }

    @Override
    public long getResultCount(String query, Locale locale) {
        Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));

        long resultCount = parseLongResultCount(
                getResultStats(query, locale),
                pattern,
                LocaleService.getValue(LocaleConstants.DELIMITER, locale));
        logger.info("{}: About [{}] results in location [{}] for query: [{}]",
                Searcher.YAHOO, resultCount, locale, query);

        return resultCount;
    }

}
