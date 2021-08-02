package com.melon.parserquery.view;

import com.melon.parserquery.model.SearchQueryDTO;
import com.melon.parserquery.parser.Searcher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleViewTest {
    private static final ConsoleView view = new ConsoleView();
    private static final Locale locale = Locale.UK;

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterModelOfPrintSearchQueryDTOIsNull() {
        SearchQueryDTO searchQueryDTO = null;

        assertThrows(IllegalArgumentException.class, () -> view.printSearchQueryDTO(searchQueryDTO, locale));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterLocaleOfPrintSearchQueryDTOIsNull() {
        SearchQueryDTO searchQueryDTO = SearchQueryDTO.builder().build();
        Locale locale = null;

        assertThrows(IllegalArgumentException.class,
                () -> view.printSearchQueryDTO(searchQueryDTO, locale)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterModelsPrintSearchQueryDTOisNull() {
        Collection<SearchQueryDTO> collectionSearchQueryDTO = null;

        assertThrows(IllegalArgumentException.class,
                () -> view.printSearchQueryDTO(collectionSearchQueryDTO, locale)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterLocalePrintSearchQueryDTOisEmpty() {
        Collection<SearchQueryDTO> collectionSearchQueryDTO = new ArrayList<>();
        Locale locale = null;

        assertThrows(IllegalArgumentException.class,
                () -> view.printSearchQueryDTO(collectionSearchQueryDTO, locale)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterIsSavedChoiceParserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> view.isSavedChoiceParser(null)
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterIsSavedChoiceParserIsEmpty() {
        List<Searcher> searchers = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> view.isSavedChoiceParser(searchers)
        );
    }

    @Test
    void setLocale() {
        Locale locale = null;

        assertThrows(IllegalArgumentException.class,
                () -> view.setLocale(locale)
        );
    }
}