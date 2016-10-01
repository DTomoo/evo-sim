package com.dt.evosim.random;

public class Random {

  private java.util.Random rnd = new java.util.Random();

  public boolean nextRandomBoolean() {
    return rnd.nextBoolean();
  }

  public double nextDouble() {
    return rnd.nextDouble();
  }

  public int getRandomSignum() {
    return getRandomIntInClosedRange(-1, 1);
  }

  public int getRandomIntInClosedRange(int from, int to) {
    return rnd.nextInt(to + 1 - from) + from;
  }

  public int getRandomXorY(int x, int y) {
    return rnd.nextBoolean() ? x : y;
  }
}
