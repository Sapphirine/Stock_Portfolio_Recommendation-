package com.stock_crawler.tarantula;

import com.stock_crawler.tarantula.learners.Perceptron;
import com.github.neuralnetworks.architecture.NeuralNetworkImpl;
import com.github.neuralnetworks.architecture.types.NNFactory;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import org.apache.commons.math3.linear.RealVector;

/**
 * Created by wakahiu on 4/27/15.
 */
public class Crawler {
    public static void main( String[] args ) {
        YahooFinanceUpdater financeUpdater = new YahooFinanceUpdater();
        FileReader fr = null;
        try {
            fr = new FileReader("/Users/wakahiu/IdeaProjects/StockCrawler/stockList.txt");
        } catch (FileNotFoundException fnfe) {
            System.err.println("File Not found. Exiting");
            System.exit(-1);
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            String line = br.readLine();

            while (line != null) {
                financeUpdater.addSymbol(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException ioe) {
            System.exit(-1);
        }
        financeUpdater.update();

        Perceptron perceptron = new Perceptron();
        //List<Map.Entry<Perceptron.Target, RealVector>> list  = Perceptron.inputParser();
        //perceptron.train(list);
        //System.out.println("Testing error :" + perceptron.test(list));

        // create multi-layer perceptron with one hidden layer and bias
        NeuralNetworkImpl mlp = NNFactory.mlpSigmoid(new int[] { 2, 8, 1 }, true);
        mlp.getProperties();
    }
}
