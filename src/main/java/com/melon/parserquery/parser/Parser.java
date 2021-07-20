package com.melon.parserquery.parser;


import com.melon.parserquery.model.SearchQueryDTO;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {

    SearchQueryDTO getSearchQueryDTO(String query, Locale locale);

    long getResultStats(String query, Locale locale);

    default long getResultCount(String string, Pattern pattern, String delimiter) {
        String resultString = "";
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            resultString = matcher.group();
        }

        resultString = resultString.replace(delimiter, "");
        return Long.parseLong(resultString);
    }
}
