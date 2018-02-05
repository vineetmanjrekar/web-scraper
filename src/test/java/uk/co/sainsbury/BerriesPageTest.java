package uk.co.sainsbury;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by vineet on 05/02/18.
 */
public class BerriesPageTest
{

    public static final String BERRIES_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";


    @Test
    public void testThatBerriesPageHas18ItemsOnly() throws IOException
    {

        WebScraper webScraper = new WebScraper(BERRIES_URL);
        assert webScraper.getItemCount() == 17;


    }


}
