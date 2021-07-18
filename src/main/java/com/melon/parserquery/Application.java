package com.melon.parserquery;

import com.melon.parserquery.view.View;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        View view = new View();
        Controller controller = new Controller(view);
        controller.process();
    }
}
