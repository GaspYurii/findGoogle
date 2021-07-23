package com.melon.parserquery.view;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Searcher;
import com.melon.parserquery.view.menu.LocaleMenuItem;
import com.melon.parserquery.view.menu.SearcherMenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.util.*;

public class ConsoleView {
    private final Logger logger = LoggerFactory.getLogger(ConsoleView.class);
    private final Scanner scanner = new Scanner(System.in);

    private Locale locale = Locale.UK;
    private ResourceBundle rb = ResourceBundle.getBundle("lang", locale);

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

    private String getInputFromKeyboard() {
        return scanner.nextLine();
    }

    public String getInput() {
        String input = getInputFromKeyboard().trim();

        while (input.equals("")) {
            println(rb.getString(ViewConstants.INPUT_EMPTY));
            logger.debug("User input is empty or only spaces");
            input = getInputFromKeyboard().trim();
        }
        return input;
    }

    public List<Searcher> getSearchers() {
        Searcher[] searchers = null;
        logger.debug("{}", rb.getLocale());
        do {
            if (searchers != null) {
                println(rb.getString(ViewConstants.WRONG_OPTION));
                logger.debug("Wrong option of searcher menu");
            }
            println(rb.getString(ViewConstants.CHOOSE_SEARCHERS));
            searchers = SearcherMenuItem.getSearcherByKey(getInput());
        } while (searchers.length == 0);
        return Arrays.asList(searchers);
    }

    private String format(SearchQueryDTO model, Locale locale) {
        return String.format(rb.getString(ViewConstants.RESULTS),
                NumberFormat.getInstance(locale).format(model.getResultCount()),
                model.getSearcher());
    }

    public boolean isSavedChoiceParser(List<Searcher> searchers) {
        String input;
        do {
            println(rb.getString(ViewConstants.SAVE_PARSERS)
                    + " " + rb.getString(ViewConstants.ENTER) + " "
                    + rb.getString(ViewConstants.YES)
                    + "/"
                    + rb.getString(ViewConstants.NO)
            );
            input = getInput();
        } while (rb.getString(ViewConstants.YES).equals(input) == rb.getString(ViewConstants.NO).equals(input));
        boolean result = rb.getString(ViewConstants.YES).equals(input);
        if (result) {
            logger.info("Search engine selection saved: {}", searchers);
        }
        return result;
    }

    /**
     * Print menu to console and get locale from menu item input
     *
     * @return object of Locale class or null
     */
    public Locale getLocaleFromInput() {
        Locale locale1;
        do {
            println("Choose language | Выберете язык");
            for (LocaleMenuItem value : LocaleMenuItem.values()) {
                println(value.toString());
            }
            println("Enter number of your choice | Введите номер:");

            locale1 = LocaleMenuItem.getLocaleByKey(getInput());
        } while (null == locale1);
        return locale1;
    }

    public Locale getLocale() {
        return locale;
    }

    /**
     * Set locale in object of View class and update resource bundle according to the new locale
     *
     * @param locale set locale in object of View class
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        rb = ResourceBundle.getBundle("lang", locale);
    }
}
