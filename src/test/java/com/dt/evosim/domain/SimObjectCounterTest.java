package com.dt.evosim.domain;

import org.junit.Assert;
import org.junit.Test;

public class SimObjectCounterTest {

  @Test
  public void test() {
    // GIVEN
    SimObjCounter counter1 = new SimObjCounter();
    SimObjCounter counter2 = new SimObjCounter();
    // WHEN
    int num1 = counter1.getAndIncrease();
    int num2 = counter2.getAndIncrease();
    // THEN
    Assert.assertEquals(0, num1);
    Assert.assertEquals(1, num2);
  }
}
