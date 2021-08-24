package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Parser;
import com.melon.parserquery.parser.ParserCache;
import com.melon.parserquery.parser.Searcher;
import com.melon.parserquery.view.ConsoleView;
import com.melon.parserquery.view.ViewConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final ConsoleView view;
    private boolean savedChoiceParsers = false;
    private List<Searcher> searchers;

    public Controller(ConsoleView view) {
        this.view = view;
    }

    /**
     * Main logic method
     */
    public void process() {
        try {
            Locale locale = view.getLocaleFromInput();
            view.setLocale(locale);

            while (true) {
                if (!savedChoiceParsers) {
                    searchers = view.getSearchers();
                    savedChoiceParsers = view.isSavedChoiceParser(searchers);
                }
                List<Parser> parsers = ParserCache.getParsers(searchers);

                String query = view.getQuery();
                if (ViewConstants.EXIT_KEY.equals(query)) {
                    break;
                }

                view.printConnection();
                List<SearchQueryDTO> models = getSearchQueryDTOList(query, parsers, locale);
                view.printSearchQueryDTO(models, locale);
            }
        } catch (Exception e) {
            logger.error("Trouble", e);
        }

    }

    private List<SearchQueryDTO> getSearchQueryDTOList(String query, Collection<Parser> parsers, Locale locale) {
        return parsers.stream().parallel()
                .map(parser -> parser.getSearchQueryDTO(query, locale))
                .collect(Collectors.toList());
    }
}
