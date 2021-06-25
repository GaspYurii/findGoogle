package com.melon.parserQuery;

import com.melon.parserQuery.parser.ParserGoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    final String exitKey = "!exit";
    private final Model model;
    private final View view;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String input;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void process() {
        do {
            inputQuery(exitKey);
            if (input.equals(exitKey)) { break; }
            else {
                searchQuery();
                showResult();
            }
        } while (true);

    }

    private void inputQuery(String exitKey) {
        try {
            view.enterQuery(exitKey);
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchQuery() {
        view.printConnection();
        ParserGoogle parserGoogle = new ParserGoogle(model, input);
        parserGoogle.parse();
    }

    private void showResult() {
        view.show();
    }


}
