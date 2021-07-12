package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Parser;
import com.melon.parserquery.parser.ParserFactory;
import com.melon.parserquery.parser.Searchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final View view;
    private final ParserFactory parserFactory = new ParserFactory();

    public Controller(View view) {
        this.view = view;
    }

    public void process() throws IOException {

        while (true) {
            String input = view.getInput();
            if (input.equals(Constants.EXIT_KEY)) { break; }

            view.println("");

            Searchers[] searchers = new Searchers[] { Searchers.GOOGLE, Searchers.YAHOO };
            List<SearchQueryDTO> models = getSearchQueryDTOList(input, searchers);

            view.show(models);
        }
    }

    private List<SearchQueryDTO> getSearchQueryDTOList(String query, Searchers[] searchers) throws IOException {
        List<SearchQueryDTO> models = new ArrayList<>();

        for (Searchers searcher : searchers) {
            Parser parser = parserFactory.create(searcher);
            models.add(parser.getSearchQueryDTO(query));
        }

        return models;
    }

}
