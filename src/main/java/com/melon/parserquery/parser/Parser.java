package com.melon.parserquery.parser;

import com.melon.parserquery.model.SearchQueryDTO;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {

    SearchQueryDTO getSearchQueryDTO(String query, Locale locale);

    long getResultCount(String query, Locale locale);

    String getResultStats(String query, Locale locale);

    default long parseLongResultCount(String stringToParse, Pattern pattern, String delimiter) {
        if (stringToParse == null || stringToParse.isEmpty() ||
                delimiter == null || delimiter.isEmpty() ||
                pattern == null
        ) {
            throw new IllegalArgumentException(
                    String.format("Parameter shouldn't be empty or null. Method invoked with params: stringToParse - %s, pattern - %s, delimiter - %s",
                            stringToParse, pattern, delimiter));
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
