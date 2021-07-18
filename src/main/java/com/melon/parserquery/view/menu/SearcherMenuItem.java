package com.melon.parserquery.view.menu;

public class SearcherMenuItem implements MenuItem {
    private final String menuItemKey;

    public SearcherMenuItem(String menuItemKey) {
        this.menuItemKey = menuItemKey;
    }

    @Override
    public String getMenuItemKey() {
        return menuItemKey;
    }
}
