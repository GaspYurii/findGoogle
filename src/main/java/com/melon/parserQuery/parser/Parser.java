package com.melon.parserQuery.parser;

import com.melon.parserQuery.Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class Parser {
    static String url;
    Model model;

    public Parser(String url, Model model) {
        this.url = url;
    }

    public static Document getDocument() {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   public abstract void parse();
}
