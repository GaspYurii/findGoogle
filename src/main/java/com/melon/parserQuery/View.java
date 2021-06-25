package com.melon.parserQuery;

import com.melon.parserQuery.parser.ParserGoogle;

public class View {
    long countQuery;
    public void enterQuery(String exitKey) {
        System.out.println("Enter your query. To exit enter: " + exitKey);
    }

    public void show() {
        //System.out.println("Somebody searched for this on Google " + countQuery + " times!");
        Model model = new Model();

        System.out.println(model.getCountQuery());
    }

    public void printConnection() {
        System.out.println("Connection...");
    }
}
