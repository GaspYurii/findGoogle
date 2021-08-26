package com.melon.parserquery.parser;

import com.melon.parserquery.webutils.WebPageConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserFactory {
    private final Logger logger = LoggerFactory.getLogger(ParserFactory.class);
    private static final WebPageConnector webPageConnector = new WebPageConnector();

    public Parser create(Searcher searcher) {
        if (Searcher.GOOGLE.equals(searcher)) {
            logInfo(searcher);
            return new ParserGoogle(webPageConnector);
        }

        if (Searcher.YAHOO.equals(searcher)) {
            logInfo(searcher);
            return new ParserYahoo(webPageConnector);
        }

        throw new IllegalArgumentException("Wrong searcher type: " + searcher);
    }

    private void logInfo(Searcher searcher) {
        logger.info("Parser created for: {}", searcher);
    }
}
