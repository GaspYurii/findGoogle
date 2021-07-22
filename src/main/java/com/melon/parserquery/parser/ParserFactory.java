package com.melon.parserquery.parser;

import com.melon.parserquery.LogConstants;
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

        logInfo(searcher);

        throw new IllegalArgumentException("Wrong searcher type: " + searcher);
    }

    private void logInfo(Searcher searcher) {
        String logInfo = LogConstants.PARSER_CREATED + searcher;
        logger.info(logInfo);
    }
}
