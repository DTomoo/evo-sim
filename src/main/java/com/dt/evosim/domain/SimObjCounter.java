package com.dt.evosim.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class SimObjCounter {

  private static final AtomicInteger COUNTER = new AtomicInteger();

  public int getAndIncrease() {
    return COUNTER.getAndIncrement();
  }
}
