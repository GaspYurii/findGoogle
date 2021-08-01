package com.melon.parserquery.parser;


import com.melon.parserquery.model.SearchQueryDTO;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {

    SearchQueryDTO getSearchQueryDTO(String query, Locale locale);

    long getResultStats(String query, Locale locale);

    default long getResultCount(String stringToParse, Pattern pattern, String delimiter) {
        if (stringToParse == null || stringToParse.isEmpty()) {
            throw new IllegalArgumentException("String to parse shouldn't be empty");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern shouldn't be null");
        }

        if (delimiter == null || delimiter.isEmpty()) {
            throw new IllegalArgumentException("Delimiter shouldn't be empty");
        }

        String resultString = "";
        Matcher matcher = pattern.matcher(stringToParse);

        while (matcher.find()) {
            resultString = matcher.group();
        }

        resultString = resultString.replace(delimiter, "");
        return Long.parseLong(resultString);
    }
}
