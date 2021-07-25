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

    private String getInputFromKeyboard() {
        return scanner.nextLine();
    }

    private String format(SearchQueryDTO model, Locale locale) {
        return String.format(rb.getString(ViewConstants.RESULTS),
                NumberFormat.getInstance(locale).format(model.getResultCount()),
                model.getSearcher());
    }

    /**
     * Print string to console
     *
     * @param s String to be output to the console
     */
    public void println(String s) {
        System.out.println(s);
    }

    /**
     * Construct a string from SearchQueryDTO model parameters
     *
     * @param model  SearchQueryDTO
     * @param locale localization for formatting constructed string
     */
    public void printSearchQueryDTO(SearchQueryDTO model, Locale locale) {
        println(format(model, locale));
    }

    /**
     * Print to console every model from collection of SearchQueryDTO
     *
     * @param models collection of SearchQueryDTO models
     * @param locale localization for formatting constructed string
     */
    public void printSearchQueryDTO(Collection<SearchQueryDTO> models, Locale locale) {
        for (SearchQueryDTO model : models) {
            printSearchQueryDTO(model, locale);
        }
    }

    /**
     * Receives input from keyboard without leading and trailing spaces. This method validate not empty input
     *
     * @return input as String
     */
    public String getInput() {
        String input = getInputFromKeyboard().trim();

        while (input.equals("")) {
            println(rb.getString(ViewConstants.INPUT_EMPTY));
            logger.debug("User input is empty or only spaces");
            input = getInputFromKeyboard().trim();
        }
        return input;
    }

    /**
     * Receives input from keyboard and get
     *
     * @return List of Searcher objects
     */
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

    /**
     * Receives input from keyboard to save searchers or not
     *
     * @param searchers need to log saved searchers
     * @return "true" if searchers are saved
     */
    public boolean isSavedChoiceParser(List<Searcher> searchers) {
        String input;
        String yes = rb.getString(ViewConstants.YES);
        String y = yes.substring(0, 1);
        String no = rb.getString(ViewConstants.NO);
        String n = no.substring(0, 1);
        boolean result;
        do {
            println(String.format("%s %s %s(%s)/%s(%s)",
                    rb.getString(ViewConstants.SAVE_PARSERS),
                    rb.getString(ViewConstants.ENTER),
                    yes,
                    y,
                    no,
                    n)
            );
            input = getInput();
            result = yes.equalsIgnoreCase(input) || y.equalsIgnoreCase(input.substring(0, 1));
        } while (result == (no.equalsIgnoreCase(input) || n.equalsIgnoreCase(input)));

        if (result) {
            logger.info("Search engine selection saved: {}", searchers);
        }
        return result;
    }

    /**
     * Print menu to console and get locale from menu item input
     *
     * @return object of Locale class
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
