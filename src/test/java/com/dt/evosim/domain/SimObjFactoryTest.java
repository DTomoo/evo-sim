package com.dt.evosim.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimObjFactoryTest {

  private SimObjFactory simObjFactory;

  @Before
  public void setUp() {
    simObjFactory = new SimObjFactory();
  }

  @Test
  public void testCreation() {
    // GIVEN
    // WHEN
    SimObj simObj = simObjFactory.randomObject();
    // THEN
    Assert.assertNotNull(simObj);
    Assert.assertEquals(0, simObj.getAge());
    Assert.assertEquals(0d, simObj.getSpeed(), 0.00001d);
    Assert.assertNotNull(simObj.getPosition());
  }

  @Test
  public void testRandomness() {
    // GIVEN
    // WHEN
    SimObj simObj1 = simObjFactory.randomObject();
    SimObj simObj2 = simObjFactory.randomObject();
    // THEN
    Assert.assertNotSame(simObj1, simObj2);
    Assert.assertNotEquals(simObj1.getPosition(), simObj2.getPosition());
    Assert.assertEquals(simObj1.getSpeed(), simObj2.getSpeed(), 0.00001d);
    Assert.assertEquals(simObj1.getAge(), simObj2.getAge());
  }
}
