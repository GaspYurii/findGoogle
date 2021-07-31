package com.melon.parserquery.webutils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

public class WebPageConnector {
    private static final Logger logger = LoggerFactory.getLogger(WebPageConnector.class);

    private WebPageConnector() {
    }

    public static Document getDocument(String url, Locale locale) throws IOException {
        logger.info("Connecting to: {}", url);
        return Jsoup.connect(url)
                .header("Accept-Language", locale.toLanguageTag())
                .get();
    }
}
