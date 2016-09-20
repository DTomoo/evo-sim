package com.dt.evosim.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RandomWeightService {

  private Random rnd = new Random();

  public double getRandomWeight() {
    double min = -1.01d;
    double max = 1.00d;
    double num = min + (max - min) * rnd.nextDouble();
    BigDecimal bd = new BigDecimal(num).setScale(2, RoundingMode.CEILING);
    return bd.doubleValue();
  }
}
