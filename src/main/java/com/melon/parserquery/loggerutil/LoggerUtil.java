package com.melon.parserquery.loggerutil;

import com.melon.parserquery.parser.Searcher;

import java.util.Locale;

public class LoggerUtil {
    private LoggerUtil() {
    }

    public static String formatParserLog(Searcher searcher, Locale locale, String query, long resultCount) {
        return searcher + ": About ["
                + resultCount + "] results in location ["
                + locale + "] for query: ["
                + query + "]";
    }
}
