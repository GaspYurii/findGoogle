package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Parser;
import com.melon.parserquery.parser.ParserGoogle;
import com.melon.parserquery.parser.ParserYahoo;
import com.melon.parserquery.parser.Searchers;

import java.io.IOException;

public class Controller {

    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    public void process() throws IOException {
        Parser parserGoogle = new ParserGoogle();
        Parser parserYahoo = new ParserYahoo();

        while (true) {
            String input = view.getInput();
            if (input.equals(Constants.EXIT_KEY)) { break; }

            view.printConnection();

            SearchQueryDTO modelGoogle = new SearchQueryDTO(input, parserGoogle.getResultStats(input), Searchers.GOOGLE);
            SearchQueryDTO modelYahoo = new SearchQueryDTO(input, parserYahoo.getResultStats(input), Searchers.YAHOO);

            view.show(modelGoogle);
            view.show(modelYahoo);
        }

    }

}
