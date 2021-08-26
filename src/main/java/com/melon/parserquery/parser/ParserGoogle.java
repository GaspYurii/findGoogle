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

public class ParserGoogle implements Parser {
    private static final Logger logger = LoggerFactory.getLogger(ParserGoogle.class);

    private static final String GOOGLE_SEARCH_URL = "https://www.google.ru/search?q=";
    private static final String RESULT_STATS_ID = "result-stats";

    private final WebPageConnector webPageConnector;

    ParserGoogle(WebPageConnector webPageConnector) {
        this.webPageConnector = webPageConnector;
    }

    @Override
    public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) {
        return SearchQueryDTO.builder()
                .setQuery(query)
                .setResultCount(getResultCount(query, locale))
                .setSearcher(Searcher.GOOGLE)
                .build();
    }

    @Override
    public String getResultStats(String query, Locale locale) {
        try {
            Document document = webPageConnector.getDocument(GOOGLE_SEARCH_URL + query, locale);
            Element resultStats = document.getElementById(RESULT_STATS_ID);

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
                LocaleService.getValue(LocaleConstants.DELIMITER, locale)
        );
        logger.info("{}: About [{}] results in location [{}]",
                Searcher.GOOGLE, resultCount, locale);
        return resultCount;
    }
}
