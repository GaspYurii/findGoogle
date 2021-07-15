package com.melon.parserquery.webutils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Locale;

public class WebPageConnector {
    public static Document getDocument(String url, Locale locale) throws IOException {
        return Jsoup.connect(url)
                .header("Accept-Language", locale.toLanguageTag())
                .get();
    }
}
