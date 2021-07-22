package com.melon.parserquery.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserFactory {
    private final Logger logger = LoggerFactory.getLogger(ParserFactory.class);

    public Parser create(Searcher searcher) {
        if (Searcher.GOOGLE.equals(searcher)) {
            logInfo(searcher);
            return new ParserGoogle();
        }

        if (Searcher.YAHOO.equals(searcher)) {
            logInfo(searcher);
            return new ParserYahoo();
        }

        throw new IllegalArgumentException("Wrong searcher type: " + searcher);
    }

    private void logInfo(Searcher searcher) {
        logger.info("Parser created for: {}", searcher);
    }
}
