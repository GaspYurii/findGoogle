package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Parser;
import com.melon.parserquery.parser.ParserCache;
import com.melon.parserquery.parser.Searcher;
import com.melon.parserquery.view.View;
import com.melon.parserquery.view.ViewConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Controller {
    Logger logger = LoggerFactory.getLogger(Controller.class);

    private final View view;
    private static final Locale locale = Locale.UK;
    private boolean unsavedChoiceParsers = true;
    private List<Searcher> searchers;

    public Controller(View view) {
        this.view = view;
    }

    public void process() {
        try {
            while (true) {
                if (unsavedChoiceParsers) {
                    searchers = view.getSearchers();
                    unsavedChoiceParsers = !view.isSavedChoiceParser(searchers);
                }

                List<Parser> parsers = ParserCache.getParsers(searchers);
                view.println(ViewConstants.ENTER_QUERY);
                String query = view.getInput();
                if (ViewConstants.EXIT_KEY.equals(query)) {
                    break;
                }

                view.println(ViewConstants.CONNECTING);

                List<SearchQueryDTO> models = getSearchQueryDTOList(query, parsers);

                view.printSearchQueryDTO(models, locale);
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
