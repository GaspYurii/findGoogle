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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller {
    private final Locale locale = Locale.UK;
    private final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final ConsoleView view;
    private boolean unsavedChoiceParsers = true;
    private List<Searcher> searchers;

    public Controller(ConsoleView view) {
        this.view = view;
    }

    public void process() {
        try {
            view.setLocale(view.getLocaleFromInput());
            ResourceBundle rb = ResourceBundle.getBundle("lang", locale);

            while (true) {
                if (unsavedChoiceParsers) {
                    searchers = view.getSearchers();
                    unsavedChoiceParsers = !view.isSavedChoiceParser(searchers);
                }

                List<Parser> parsers = ParserCache.getParsers(searchers);
                view.println(rb.getString(ViewConstants.ENTER_QUERY)
                        + " "
                        + ViewConstants.EXIT_KEY
                );
                String query = view.getInput();
                if (ViewConstants.EXIT_KEY.equals(query)) {
                    break;
                }

                view.println(rb.getString(ViewConstants.CONNECTING));

                List<SearchQueryDTO> models = getSearchQueryDTOList(query, parsers);

                view.printSearchQueryDTO(models, view.getLocale());
            }
        } catch (Exception e) {
            logger.error("Trouble", e);
        }

    }

    private List<SearchQueryDTO> getSearchQueryDTOList(String query, Collection<Parser> parsers) {
        return parsers.stream().parallel()
                .map(parser -> parser.getSearchQueryDTO(query, locale))
                .collect(Collectors.toList());
    }
}
