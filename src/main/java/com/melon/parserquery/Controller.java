package com.melon.parserquery;

import com.melon.parserquery.parser.ParserGoogle;

import java.io.IOException;

public class Controller {

    private String exitKey = ":q";
    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    public void process() throws IOException {
        ParserGoogle parserGoogle = new ParserGoogle();

        while (true) {
            String input = view.getInput();
            if (input.equals(exitKey)) { break; }

            view.printConnection();

            Model model = new Model(input, parserGoogle.getResultStats(input));

            view.show(model.getResultStats());
        }

    }




}
