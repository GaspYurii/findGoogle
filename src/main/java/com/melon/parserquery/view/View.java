package com.melon.parserquery.view;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Searcher;
import com.melon.parserquery.view.menu.SearcherMenuItem;

import java.text.NumberFormat;
import java.util.*;

public class View {
    private final Scanner scanner = new Scanner(System.in);

    public String getInput() {
        String input = getInputFromKeyboard().trim();

        while (input.equals("")) {
            println(ViewConstants.INPUT_EMPTY);
            input = getInputFromKeyboard().trim();
        }
        return input;
    }

    private String getInputFromKeyboard() {
        return scanner.nextLine();
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
        return String.format("   About %s results in %s",
                NumberFormat.getInstance(locale).format(model.getResultCount()),
                model.getSearcher());
    }

    public List<Searcher> getSearchers() {
        Searcher[] searchers = null;
        do {
            if (searchers != null) {
                println(ViewConstants.WRONG_OPTION);
            }
            println(ViewConstants.CHOOSE_SEARCHERS);
            searchers = SearcherMenuItem.getSearcherByKey(getInput());
        } while (searchers.length == 0);
        return Arrays.asList(searchers);
    }

    public boolean saveChoiceParser() {
        String input;
        do {
            println(ViewConstants.SAVE_PARSERS);
            input = getInput();
        } while (ViewConstants.Y.equals(input) == ViewConstants.N.equals(input));
        return !ViewConstants.Y.equals(input);
    }
}
