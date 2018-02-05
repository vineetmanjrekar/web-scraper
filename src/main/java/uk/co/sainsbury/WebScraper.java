package uk.co.sainsbury;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;
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

    public WebScraper(String pageUrl) throws IOException
    {
        webClient.getOptions().setJavaScriptEnabled(false);
        url = new URL(pageUrl);
        page = (HtmlPage)webClient.getPage(url);
    }

    public int getItemCount()
    {
        String headingTitle = page.getElementById("resultsHeading").getTextContent();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(headingTitle);
        while (m.find()) {
            return Integer.parseInt(m.group(0));
        }
        return 0;
    }
}
