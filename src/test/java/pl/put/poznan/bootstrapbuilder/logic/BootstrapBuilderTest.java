package pl.put.poznan.bootstrapbuilder.logic;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BootstrapBuilderTest {

    Tag mockedTag = mock(Tag.class);
    Header mockedHeader = mock(Header.class);
    Footer mockedFooter = mock(Footer.class);

    @Test
    void build() throws JSONException {
        when(mockedTag.buildMeta()).thenReturn("<meta name= \"test\" content=\"contenttest\">");
        when(mockedTag.buildMetaTwitter()).thenReturn("<meta name=\"twitter:test\" content=\"contenttest\">");
        when(mockedTag.buildMetaOG()).thenReturn("<meta property=\"og:test\" content=\"contenttest\">");
        when(mockedHeader.buildHeader()).thenReturn("<header></header>");
        when(mockedFooter.buildFooter()).thenReturn("<footer></footer>");

        String expectedResult = """
                    <!doctype html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name= "test" content="contenttest">
                        <meta name="twitter:test" content="contenttest">
                        <meta property="og:test" content="contenttest">
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
                    </head>
                    <body>
                        <header></header>
                        <main class="container">
                        </main>
                        <footer></footer>
                    </body>
                </html>
                """;
        BootstrapBuilder bootstrapBuilder = new BootstrapBuilder();
        bootstrapBuilder.setFooter(mockedFooter).setHeader(mockedHeader).setTag(mockedTag);
        assertEquals(expectedResult,bootstrapBuilder.build());

    }
}