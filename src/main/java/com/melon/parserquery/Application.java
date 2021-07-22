package com.melon.parserquery;

import com.melon.parserquery.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Application started");
        View view = new View();
        Controller controller = new Controller(view);
        controller.process();
        logger.info("Application closed");
    }
}
