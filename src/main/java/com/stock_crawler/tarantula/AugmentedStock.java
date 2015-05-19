package com.stock_crawler.tarantula;

import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.OpenMapRealVector;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wakahiu on 4/27/15.
 */

/**
 * A wrapper class for Yahoo yahoofinance.Stock. Used to generate feature vectors for learning.
 */
public class AugmentedStock {

  private Stock stock;
  private Map<String, Double> featureMap;

  public  AugmentedStock(Stock stk) {
    stock = stk;
    featureMap = new HashMap<String, Double>();
  }

  /**
   * Populates a HashMap with features. These features are then converted into a feature vector
   * for learning.
   */
  private void generateFeatureMap() {
    StockQuote stockQuote = stock.getQuote();
    StockStats stockStats = stock.getStats();
    featureMap.put("Price                             ", stockQuote.getPrice().doubleValue());
    featureMap.put("Volume                            ", new Double(stockQuote.getVolume()));
    featureMap.put("Average Volume                    ", new Double(stockQuote.getAvgVolume()));
    featureMap.put("Average Volume Percent            ",
        100.0 * stockQuote.getAvgVolume()/stockQuote.getVolume());
    featureMap.put("Price Avg 50                      ", stockQuote.getPriceAvg50().doubleValue());
    featureMap.put("Price Avg 200                     ", stockQuote.getPriceAvg200().doubleValue());
    featureMap.put("Year High                         ", stockQuote.getYearHigh().doubleValue());
    featureMap.put("Year Low                          ", stockQuote.getYearLow().doubleValue());
    featureMap.put("Change In Percent                 ",
        stockQuote.getChangeInPercent().doubleValue());
    featureMap.put("Change From Avg 50 In Percent     ",
        stockQuote.getChangeFromAvg50InPercent().doubleValue());
    featureMap.put("Change From Avg 200 In Percent    ",
        stockQuote.getChangeFromAvg200InPercent().doubleValue());
    featureMap.put("Change From Year High In Percent  ",
        stockQuote.getChangeFromYearHighInPercent().doubleValue());
    featureMap.put("Change From Year Low In Percent   ",
        stockQuote.getChangeFromYearLowInPercent().doubleValue());
    featureMap.put("Market Capitalization             ", stockStats.getMarketCap().doubleValue());
    featureMap.put("Revenue                           ", stockStats.getRevenue().doubleValue());
    featureMap.put("Return On Equity (ROE)            ", stockStats.getROE().doubleValue());
    featureMap.put("Price-Earnings (P/E) ratio        ", stockStats.getPe().doubleValue());
    featureMap.put("Price-Earnings to Growth ratio    ", stockStats.getPeg().doubleValue());
    featureMap.put("Earnings Before ITDA              ", stockStats.getEBITDA().doubleValue());
    featureMap.put("Earnings per Share (EPS)          ", stockStats.getEps().doubleValue());
    featureMap.put("EPS estimate current year         ",
        stockStats.getEpsEstimateCurrentYear().doubleValue());
    featureMap.put("EPS estimate next quarter         ",
        stockStats.getEpsEstimateNextQuarter().doubleValue());
    featureMap.put("EPS estimate next year            ",
        stockStats.getEpsEstimateNextYear().doubleValue());
    featureMap.put("One year target price             ",
        stockStats.getOneYearTargetPrice().doubleValue());
    double priceDiff =
        stockStats.getOneYearTargetPrice().doubleValue() - stockQuote.getPrice().doubleValue();
    featureMap.put("One year target price % Diff      ",
        priceDiff / stockQuote.getPrice().doubleValue());
    double yrDiff = stockQuote.getYearHigh().doubleValue() - stockQuote.getYearLow().doubleValue();
    featureMap.put("Year High-Low diff Percent        ",
        100.00 * yrDiff / stockQuote.getPrice().doubleValue());
  }

  /**
   * Get a feature vector from a stock.
   * @return Sparse Feature Vector in RN.
   */
  @SuppressWarnings("unchecked")
  public RealVector getFeatureVector() {
    generateFeatureMap();
    double [] featureArray = new double[featureMap.size()];
    Iterator fmIt = featureMap.entrySet().iterator();
    for ( int i =0; fmIt.hasNext() ; ++i) {
      Map.Entry<String, Double> pair = (Map.Entry<String, Double>)fmIt.next();
      double val = pair.getValue().doubleValue();
      featureArray[i] = val;
    }
    return new OpenMapRealVector(featureArray);
  }

  /**/
  @SuppressWarnings("unchecked")
  public String printFeatures() {
    StringBuilder rvSB = new StringBuilder();
    Iterator fmIt = featureMap.entrySet().iterator();
    while (fmIt.hasNext()) {
      Map.Entry<String,Double> pair = (Map.Entry<String,Double>)fmIt.next();
      double val = pair.getValue().doubleValue();
      if (val == 0) {
        rvSB.append(pair.getKey() + val + "**" + "\n");
      } else {
        rvSB.append(pair.getKey() + val + "\n");
      }
    }
    System.out.println(rvSB.toString());
    return rvSB.toString();
  }
  /**/
}