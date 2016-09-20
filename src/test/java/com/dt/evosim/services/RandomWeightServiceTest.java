package com.dt.evosim.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomWeightServiceTest {

  private RandomWeightService randomWeightService;

  @Before
  public void setUp() {
    randomWeightService = new RandomWeightService();
  }

  @Test
  public void testGetRandomWeight() {
    // GIVEN
    double weight;
    // WHEN
    for (int i = 0; i < 1000; i++) {
      weight = randomWeightService.getRandomWeight();
      // THEN
      Assert.assertTrue("Weight:" + weight + ".", -1.0 <= weight);
      Assert.assertTrue("Weight:" + weight + ".", weight <= 1.0d);
    }
  }
}
