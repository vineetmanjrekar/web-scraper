package uk.co.sainsbury;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by vineet on 05/02/18.
 */
public class BerriesPageTest
{

    public static final String BERRIES_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    WebScraper webScraper = new WebScraper(BERRIES_URL);


    @Test
    public void testThatInvalidUrlWillResultInErrorOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        webScraper = new WebScraper("invald url");

        assertEquals("Invalid URL for scraping\n", outContent.toString());
    }

    @Test
    public void testThatBerriesPageHasItemCountOf17() throws IOException
    {
        assert webScraper.getItemCount() == 17;
    }


    @Test
    public void testThatBerriesPageHasCorrectItems() throws IOException
    {

    }

}
