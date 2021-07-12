package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;

import java.util.List;
import java.util.Scanner;

public class View {
    public String getInput() {
        String input = input().trim();

        while (input.equals("")) {
            println(Constants.INPUT_EMPTY);
            input = input().trim();
        }
        return input;
    }

    private String input() {
        String input;
        Scanner scanner;
        scanner = new Scanner(System.in);
        println(Constants.ENTER_QUERY);
        input = scanner.nextLine();

        return input;
    }

    public void println(String s) {
        System.out.println(s);
    }

    public void show(SearchQueryDTO model) {
        println(String.format("About %d results in %s",
                model.getResultCount(),
                model.getSearcher().toString()
                )
        );
    }

    public void show(List<SearchQueryDTO> models) {
        for (SearchQueryDTO model : models) {
            show(model);
        }
    }

}
