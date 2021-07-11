package com.melon.parserquery.parser;


import com.melon.parserquery.model.SearchQueryDTO;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Parser {

    SearchQueryDTO getSearchQueryDTO(String query) throws IOException;

    long getResultStats(String query) throws IOException;

    default long getResultCount(String string, String regEx) {
        String resultString = "";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            resultString = string.substring(matcher.start(), matcher.end());
        }
        resultString = resultString.replace(",", "");
        return Long.parseLong(resultString);
    }
}
