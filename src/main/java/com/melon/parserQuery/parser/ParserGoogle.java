package com.melon.parserQuery.parser;

import com.melon.parserQuery.Model;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParserGoogle extends Parser{

    public ParserGoogle(Model model, String query) {
        super ("https://www.google.ru/search?q=" + query, model);
       // this.model = model;
    }

    @Override
    public void parse() {
        Document document = getDocument();
        Element element = document.getElementById("result-stats");
        System.out.println(element.text());
        model.setCountQuery(element.text());
        System.out.println("model: " + model.getCountQuery());
    }
}
