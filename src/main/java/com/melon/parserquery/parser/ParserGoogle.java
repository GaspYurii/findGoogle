package com.melon.parserquery.parser;

import com.melon.parserquery.exception.ParserException;
import com.melon.parserquery.loggerutil.LoggerUtil;
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
    private static final String REG_EX = "([0-9]{1,3},)+([0-9]{3})++";
    private static final Pattern pattern = Pattern.compile(REG_EX);

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
            long resultCount = getResultCount(resultStats.text(), pattern, ",");
            String logInfo = LoggerUtil.formatParserLog(Searcher.GOOGLE, locale, query, resultCount);
            logger.info(logInfo);
            return resultCount;
        } catch (IOException e) {
            throw new ParserException();
        }
    }
}
