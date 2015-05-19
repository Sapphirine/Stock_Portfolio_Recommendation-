package com.stock_crawler.tarantula;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Created by wakahiu on 4/28/15.
 */
public class YahooFinanceUpdater {
    private List<String> symbols;
    private Map<String, Stock> stockQuotes;

    public YahooFinanceUpdater() {
        symbols = new ArrayList<String>();
        stockQuotes = new HashMap<String, Stock>();
    }

    public void addSymbol(String symbol) {
        symbols.add(symbol);
    }

    @SuppressWarnings("unchecked")
    public void update() {
        YahooFinance yahooFinance = new YahooFinance();
        Object [] symbolObj = symbols.toArray();
        String [] symbolStrings = Arrays.copyOf(symbolObj, symbolObj.length, String[].class);
        Map<String, Stock> stocksMap = yahooFinance.get(symbolStrings, false);
        Map<String, AugmentedStock> augmentedStockMap = new HashMap<String, AugmentedStock>();

        Iterator mapIt = stocksMap.entrySet().iterator();
        while (mapIt.hasNext()) {
            Map.Entry<String, Stock> pair = (Map.Entry<String, Stock>) mapIt.next();
            Stock stock = pair.getValue();
            AugmentedStock augmentedStock = new AugmentedStock(stock);
            System.out.println(pair.getKey());
            augmentedStock.getFeatureVector();
            augmentedStock.printFeatures();
            augmentedStockMap.put(pair.getKey(), augmentedStock);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n-------------------------------------------------------------------");
        stringBuilder.append("\n\t\t STOCK DATA ");
        //for(Entry<String, AugmentedStock> stock : sQuotes.entrySet()){
        //  String symbol = entry.getKey();
        //  Hash

        stringBuilder.append("\n-------------------------------------------------------------------");
        return stringBuilder.toString();
    }

}
