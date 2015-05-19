package com.stock_crawler.tarantula.learners;

import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.OpenMapRealVector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by wakahiu on 5/11/15.
 */
public class Perceptron {
  private RealVector w;
  private double theta1;
  private double theta2;

  public enum Target {
    POS,
    NEG
  }

  public Perceptron(double t1, double t2) {
    w = new OpenMapRealVector();
    theta1 = t1;
    theta2 = t2;
  }

  // TODO make perceptron take the length of the largest vector.
  public Perceptron() {
    w = new OpenMapRealVector(50000);
    w.setEntry(0,1.0);
    theta1 = 0;
    theta2 = 0;
  }

  public  static List<Map.Entry<Target, RealVector>> inputParser() {
    List<Map.Entry<Target, RealVector>> list = new ArrayList<Map.Entry<Target, RealVector>>();
    FileReader fr = null;
    String fileName = "/Users/wakahiu/Downloads/rcv1/rcv1.train.txt";
    try {
      fr = new FileReader(fileName);
    } catch (FileNotFoundException fnfe) {
      System.err.println("File " + fileName + " Not found. Exiting");
      System.exit(-1);
    }
    BufferedReader br = new BufferedReader(fr);
    try {
      Pattern pattern = Pattern.compile("(.?)\\|(.?)");
      String line = br.readLine();
      int max = 100;

      for (int i =0; i < 12000 && line != null; ++i) {
        OpenMapRealVector vec = new OpenMapRealVector(50000);
        vec.setEntry(0, 1.0);
        String [] str = line.split(" ");
        Target target = Integer.parseInt(str[0]) == 0 ? Target.NEG: Target.POS;

        for( int j = 1; j < str.length ; ++j) {
          Matcher matcher = pattern.matcher(str[j]);
          if (matcher.matches()) {
            continue;
          }
          String [] vecComponents = str[j].split(":");
          int idx = Integer.parseInt(vecComponents[0]);
          if( idx < max) {
            max = idx;

          }
          vec.setEntry(idx, Double.parseDouble(vecComponents[1]));
        }
        list.add( new AbstractMap.SimpleEntry<Target, RealVector>(target, vec));
        line = br.readLine();
      }
      br.close();
    } catch (IOException ioe) {
      System.exit(-1);
    }
    return list;
  }



  public RealVector train(List<Map.Entry<Target, RealVector>> trainSet) {
    for (Map.Entry<Target, RealVector> m : trainSet) {
      RealVector x = m.getValue();
      Target y = w.dotProduct(x) > 0 ? Target.POS : Target.NEG;
      double tSign = m.getKey() == Target.POS ? +1 : -1;
      if (y != m.getKey()) {
        w  = w.add(x.mapMultiplyToSelf(tSign));
      }
    }
    return w;
  }

  public double test(List<Map.Entry<Target, RealVector>> testSet) {
    int err = 0;
    for (Map.Entry<Target, RealVector> m : testSet) {
      RealVector x = m.getValue();
      Target y = w.dotProduct(x) > 0 ? Target.POS : Target.NEG;
      if (y != m.getKey()) {
        err++;
      }
    }
    return 100.0 * err /testSet.size();
  }
}
