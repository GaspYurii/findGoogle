package com.melon.parserquery.parser;


import com.melon.parserquery.model.SearchQueryDTO;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {

    SearchQueryDTO getSearchQueryDTO(String query, Locale locale);

    long getResultStats(String query, Locale locale);

    default long getResultCount(String stringToParse, @NotNull Pattern pattern, String delimiter) {
        if ("".equals(stringToParse)) {
            throw new IllegalArgumentException("String to parse shouldn't be empty");
        }
        if ("".equals(delimiter)) {
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
