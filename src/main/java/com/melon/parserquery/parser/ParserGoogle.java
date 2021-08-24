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

    ParserGoogle() { /**/ }

    @Override
    public SearchQueryDTO getSearchQueryDTO(String query, Locale locale) {
        return SearchQueryDTO.builder()
                .setQuery(query)
                .setResultCount(getResultStats(query, locale))
                .setSearcher(Searcher.GOOGLE)
                .build();
    }

    @Override
    public long getResultStats(String query, Locale locale) {
        try {
            Document document = WebPageConnector.getDocument(GOOGLE_SEARCH_URL + query, locale);
            Element resultStats = document.getElementById(RESULT_STATS_ID);
            Pattern pattern = Pattern.compile(LocaleService.getValue(LocaleConstants.REG_EX, locale));
            long resultCount = getResultCount(
                    resultStats.text(),
                    pattern,
                    LocaleService.getValue(LocaleConstants.DELIMITER, locale)
            );
            logger.info("{}: About [{}] results in location [{}] for query: [{}]",
                    Searcher.GOOGLE, resultCount, locale, query);
            return resultCount;
        } catch (IOException e) {
            throw new ParserException();
        }
    }
}
