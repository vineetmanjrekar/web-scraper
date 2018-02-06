package uk.co.sainsbury.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by vineet on 05/02/18.
 */
public class Result
{
    private List<Map<String, String>> results;
    private double total;


    public void setResults(List<Map<String, String>> results)
    {
        this.results = results;
    }

    public List<Map<String, String>> getResults()
    {
        return results;
    }

    public double getTotal()
    {
        total = 0;

        for (Map<String, String> item : results)
        {
            for (Map.Entry entry : item.entrySet())
            {
                if (entry.getKey().equals("unit_price"))
                {
                    total = total + Double.parseDouble((String) entry.getValue());
                }
            }
        }
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "Result{" +
                "results=" + results +
                ", total=" + total +
                '}';
    }
}
