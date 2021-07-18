package com.melon.parserquery.view.menu;

import java.util.List;

public interface Menu {
    List<MenuItem> getMenuList();

    void addItemToMenu(MenuItem item);

}
