package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

public class View {

    public String getInput() {
        String input = input().trim();

        while (input.equals("")) {
            println(Constants.INPUT_EMPTY);
            input = input().trim();
        }
        return input;
    }

    private String input() {
        String input;
        Scanner scanner;
        scanner = new Scanner(System.in);
        println(Constants.ENTER_QUERY);
        input = scanner.nextLine();

        return input;
    }

    public void println(String s) {
        System.out.println(s);
    }

    public void printSearchQueryDTO(SearchQueryDTO model, Locale locale) {
        println(format(model, locale));
    }

    public void printSearchQueryDTO(Collection<SearchQueryDTO> models, Locale locale) {
        for (SearchQueryDTO model : models) {
            printSearchQueryDTO(model, locale);
        }
    }

    private String format(SearchQueryDTO model, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getInstance(locale);

        return String.format("About %s results in %s",
                numberFormat.format(model.getResultCount()),
                model.getSearcher().toString());
    }
}
