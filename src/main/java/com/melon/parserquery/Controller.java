package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Parser;
import com.melon.parserquery.parser.ParserFactory;
import com.melon.parserquery.view.View;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Controller {

    private final View view;
    private final ParserFactory parserFactory = new ParserFactory();
    private final Locale locale = Locale.UK;

    public Controller(View view) {
        this.view = view;
    }

    public void process() {
        view.println(Constants.CHOOSE_SEARCHERS);
        List<Parser> parsers = parserFactory.getParsers(view.getSearchers());

        while (true) {
            view.println(Constants.ENTER_QUERY);
            String input = view.getInput();
            if (input.equals(Constants.EXIT_KEY)) {
                break;
            }

            view.println(Constants.CONNECTING);

            List<SearchQueryDTO> models = getSearchQueryDTOList(input, parsers);

            view.printSearchQueryDTO(models, locale);
        }
    }

    private List<SearchQueryDTO> getSearchQueryDTOList(String query, Collection<Parser> parsers) {
        return parsers.stream().parallel()
                .map(parser -> {
                    try {
                        return parser.getSearchQueryDTO(query, locale);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


}
