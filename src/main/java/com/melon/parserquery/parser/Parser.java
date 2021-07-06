package com.melon.parserquery.parser;


import java.io.IOException;

public interface  Parser {

    long getResultStats(String query) throws IOException;
}
