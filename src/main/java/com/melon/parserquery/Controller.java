package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Parser;
import com.melon.parserquery.parser.ParserCache;
import com.melon.parserquery.parser.Searcher;
import com.melon.parserquery.view.View;
import com.melon.parserquery.view.ViewConstants;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Controller {

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
                    unsavedChoiceParsers = view.saveChoiceParser();
                }
                List<Parser> parsers = ParserCache.getParsers(searchers);
                view.println(ViewConstants.ENTER_QUERY);
                String input = view.getInput();
                if (input.equals(ViewConstants.EXIT_KEY)) {
                    break;
                }

                view.println(ViewConstants.CONNECTING);

                List<SearchQueryDTO> models = getSearchQueryDTOList(input, parsers);

                view.printSearchQueryDTO(models, locale);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<SearchQueryDTO> getSearchQueryDTOList(String query, Collection<Parser> parsers) {
        return parsers.stream().parallel()
                .map(parser -> parser.getSearchQueryDTO(query, locale))
                .collect(Collectors.toList());
    }
}
