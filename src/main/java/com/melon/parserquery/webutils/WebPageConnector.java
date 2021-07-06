package com.melon.parserquery.webutils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebPageConnector {
    public static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .header("Accept-Language", "en-UK")
                .get();
    }
}
