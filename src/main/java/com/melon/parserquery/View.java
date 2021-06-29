package com.melon.parserquery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
    public String getInput(String exitKey) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {
            printEnterQuery(exitKey);
            input = reader.readLine();
            input = input.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.equals("")) {
            inputIsEmpty();
            getInput(exitKey);
        }

        return input;
    }

    public void printEnterQuery(String exitKey) {
        System.out.println("Enter your query. To exit enter: " + exitKey);
    }

    public void show(String string) { System.out.println(string); }

    public void printConnection() {
        System.out.println("Connection...");
    }

    public void inputIsEmpty() {System.out.println("Your input should not be empty");}
}
