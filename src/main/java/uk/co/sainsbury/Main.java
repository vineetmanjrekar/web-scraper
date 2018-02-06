package uk.co.sainsbury;

import uk.co.sainsbury.domain.Result;

import java.io.IOException;

/**
 * Created by vineet on 05/02/18.
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        WebScraper webScraper = new WebScraper(args[0]);
        Result itemsForUrl = webScraper.getItemsForUrl();
        double total = itemsForUrl.getTotal();
        System.out.println(itemsForUrl.toString());
        System.out.println(total);
    }

}
