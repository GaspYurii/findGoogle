package com.melon.parserquery.parser;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class ParserGoogleTest {
    @Mock
    Document document = mock(Document.class);

    @Mock
    ParserGoogle parserGoogle = mock(ParserGoogle.class);

    @Test
    void getParserTest() {

        document.getElementById("result-stats");
        verify(document, times(1)).getElementById("result-stats");
    }
}