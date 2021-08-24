package com.melon.parserquery;

import com.melon.parserquery.view.ConsoleView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Application started");
        ConsoleView view = new ConsoleView();
        Controller controller = new Controller(view);
        controller.process();
        logger.info("Application closed");
    }
}
