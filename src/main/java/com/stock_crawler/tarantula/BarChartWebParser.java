package com.stock_crawler.tarantula;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Created by wakahiu on 4/28/15.
 */
public class BarChartWebParser {
    public BarChartWebParser() {
    }

    public List<String> extractTop100() throws IOException {
        List<String> stockList = new ArrayList<String>();
        /*
        Document doc = Jsoup.connect("http://www.barchart.com/stocks/signals/top100").get();
        Elements pageTables = doc.select("tbody");
        for (Element tb : pageTables) {
            Elements tableRows = tb.getElementsByTag("tr");
            continue;
        }
        System.out.println("\n*******************************************************************************");
        System.out.println(tableRows.size());
        for (Element row : tableRows) {
            Elements entrys = row.getElementsByTag("td");
            String sym = entrys.get(0).text();
            String name = entrys.get(1).text();
            String price = entrys.get(2).text();
            String percentChange = entrys.get(3).text();
            String opinionToday = entrys.get(4).text();
            String opinionPrev = entrys.get(5).text();
            String opinionLastWeek = entrys.get(6).text();
            String opinionLastMonth = entrys.get(7).text();
            System.out.print(sym + " " + name + " " + price + " " + percentChange );
            System.out.println(opinionToday + " " + opinionPrev + " " + opinionLastWeek + " " + opinionLastMonth );
            stockList.add(sym);
        }
        */
        return stockList;
    }
}