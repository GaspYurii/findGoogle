package com.melon.parserquery.view;

import com.melon.parserquery.Constants;
import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Searcher;
import com.melon.parserquery.view.menu.MenuItem;
import com.melon.parserquery.view.menu.SearcherMenu;
import com.melon.parserquery.view.menu.SearcherMenuKeys;

import java.text.NumberFormat;
import java.util.*;

public class View {
    private final Scanner scanner = new Scanner(System.in);
    private final SearcherMenu searcherMenu = SearcherMenu.getInstance();

    public String getInput() {
        String input = getInputFromKeyboard().trim();

        while (input.equals("")) {
            println(Constants.INPUT_EMPTY);
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
        List<Searcher> list = new ArrayList<>();
        String input = getInput();
        while (!validateSearcherInput(input)) {
            println(Constants.WRONG_OPTION);
            println(Constants.CHOOSE_SEARCHERS);
            input = getInput();
        }

        if (SearcherMenuKeys.ALL.equals(input)) {
            list.add(Searcher.GOOGLE);
            list.add(Searcher.YAHOO);
        } else if (SearcherMenuKeys.GOOGLE.equals(input)) {
            list.add(Searcher.GOOGLE);
        } else if (SearcherMenuKeys.YAHOO.equals(input)) {
            list.add(Searcher.YAHOO);
        }

        return list;
    }

    private boolean validateSearcherInput(String input) {
        for (MenuItem item : searcherMenu.getMenuList()) {
            if (item.getMenuItemKey().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
