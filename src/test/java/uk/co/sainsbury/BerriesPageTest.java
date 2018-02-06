package uk.co.sainsbury;

import org.junit.Test;
import uk.co.sainsbury.domain.Result;

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



    @Test
    public void testThatInvalidUrlWillResultInErrorOutput()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        WebScraper webScraper = new WebScraper("invald url");

        assertEquals("Invalid URL for scraping\n", outContent.toString());
    }

    public void testThatBerriesPageHasItemCountOf17() throws IOException
    {
        WebScraper webScraper = new WebScraper(BERRIES_URL);
        assert webScraper.getItemCount() == 17;
    }


    @Test
    public void testThatBerriesPageHasCorrectItems() throws IOException
    {

        WebScraper webScraper = new WebScraper(BERRIES_URL);

        Result result = webScraper.getItemsForUrl();
        assert result.getResults().size() == 17;
        assert result.getTotal() == 39.5;

    }


}
