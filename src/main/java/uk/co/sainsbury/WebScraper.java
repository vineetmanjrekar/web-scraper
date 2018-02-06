package uk.co.sainsbury;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import uk.co.sainsbury.domain.Result;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vineet on 05/02/18.
 */
public class WebScraper
{
    WebClient webClient = new WebClient();

    URL url;
    HtmlPage page;

    public WebScraper(String pageUrl)
    {
        webClient.getOptions().setJavaScriptEnabled(false);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        try
        {
            url = new URL(pageUrl);
            page = (HtmlPage) webClient.getPage(url);
        } catch (IOException e)
        {
            System.err.println("Invalid URL for scraping");
        }
    }

    public int getItemCount()
    {
        String headingTitle = page.getElementById("resultsHeading").getTextContent();
        return getIntegerMatching(headingTitle);
    }


    public Result getItemsForUrl() throws IOException
    {
        Result result = new Result();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (int i = 1; i <= getItemCount(); i++)
        {
            final Map<String, String> item = new HashMap<String, String>();
            HtmlAnchor htmlAnchor = (HtmlAnchor) page.getByXPath("//*[@id=\"productLister\"]/ul/li[" + i + "]/div/div[1]/div/h3/a").get(0);
            List<Object> byXPath = ((HtmlPage) htmlAnchor.click()).getByXPath("//*[@id=\"information\"]/productcontent/htmlcontent/div[1]/p[1]");
            String description = "";
            if (byXPath.size() > 0)
            {
                description = ((HtmlParagraph) byXPath.get(0)).getTextContent();
            }
            String title = ((HtmlHeading1) ((HtmlPage) htmlAnchor.click()).getByXPath("//*[@id=\"content\"]/div[2]/div[2]/div[1]/div[1]/h1").get(0)).getTextContent();
            List<Object> kCalElement = ((HtmlPage) htmlAnchor.click()).getByXPath("//*[@id=\"information\"]/productcontent/htmlcontent/div[2]/div/div/table/tbody/tr[2]/td[1]");
            String kCal = "0";
            if (kCalElement.size() > 0)
            {
                kCal = ((HtmlTableDataCell) ((List<Object>) kCalElement).get(0)).getTextContent();
            }
            String id = ((HtmlPage) htmlAnchor.click()).getElementByName("catEntryId").getAttribute("value");
            String unitPrice = ((HtmlParagraph) ((HtmlPage) htmlAnchor.click()).getByXPath("//*[@id=\"addItem_" + id + "\"]/div[1]/p[1]").get(0)).getTextContent();
            item.put("description", description);
            item.put("title", title);
            item.put("kcal_per_100g", String.valueOf(getIntegerMatching(kCal)));
            item.put("unit_price", String.valueOf(findUnitPriceFor(unitPrice)));
            list.add(item);
        }

        result.setResults(list);

        return result;
    }

    private int getIntegerMatching(String text)
    {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while (m.find())
        {
            return Integer.parseInt(m.group(0));
        }
        return 0;
    }

    private double findUnitPriceFor(String text)
    {
        String unitRate = text.substring(text.indexOf("£") + 1 , text.indexOf("/"));
        return Double.parseDouble(unitRate);
    }


}
