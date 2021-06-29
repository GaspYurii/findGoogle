package com.melon.parserquery;

import com.melon.parserquery.parser.ParserGoogle;

import java.io.IOException;

public class Controller {

    private String exitKey = "!exit";
    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    public void process() throws IOException {
        while (true) {
            String input = view.getInput(exitKey);
            if (input.equals(exitKey)) { break; }

            view.printConnection();
            ParserGoogle parserGoogle = new ParserGoogle(input);

            Model model = new Model(input, parserGoogle.getResultStats());

            view.show(model.getResultStats());
        }

    }




}
