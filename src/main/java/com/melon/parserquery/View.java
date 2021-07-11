package com.melon.parserquery;

import com.melon.parserquery.model.SearchQueryDTO;

import java.util.List;
import java.util.Scanner;

public class View {
    public String getInput() {
        String input;

        input = input().trim();

        while (input.equals("")) {
            inputIsEmpty();
            input = input().trim();
        }
        return input;
    }

    private String input() {
        String input;
        Scanner scanner;
        scanner = new Scanner(System.in);
        printEnterQuery(Constants.EXIT_KEY);
        input = scanner.nextLine();

        return input;
    }

    public void printEnterQuery(String exitKey) {
        System.out.println("Enter your query. To exit enter: " + exitKey);
    }

    public void show(SearchQueryDTO model) {
        System.out.printf("About %d results in %s\n",
                model.getResultCount(),
                model.getSearcher().toString()
        );
    }

    public void show(List<SearchQueryDTO> models) {
        for (SearchQueryDTO model : models) {
            show(model);
        }
    }

    public void printConnection() { System.out.println("Connection..."); }

    public void inputIsEmpty() {System.out.println("Your input should not be empty");}
}
