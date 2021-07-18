package com.melon.parserquery.view.menu;

import java.util.ArrayList;
import java.util.List;

public class SearcherMenu implements Menu {
    private static SearcherMenu instance;
    private final List<MenuItem> menuItems = new ArrayList<>();

    private SearcherMenu() {
    }

    public static SearcherMenu getInstance() {
        if (instance == null) {
            instance = new SearcherMenu();
            init();
        }
        return instance;
    }

    private static void init() {
        getInstance().addItemToMenu(new SearcherMenuItem(SearcherMenuKeys.ALL));
        getInstance().addItemToMenu(new SearcherMenuItem(SearcherMenuKeys.GOOGLE));
        getInstance().addItemToMenu(new SearcherMenuItem(SearcherMenuKeys.YAHOO));
    }

    @Override
    public List<MenuItem> getMenuList() {
        return menuItems;
    }

    @Override
    public void addItemToMenu(MenuItem item) {
        menuItems.add(item);
    }
}
