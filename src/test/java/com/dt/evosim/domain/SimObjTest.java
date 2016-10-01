package com.dt.evosim.domain;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class SimObjTest {

  @Test(expected = UnsupportedOperationException.class)
  public void testGetOtherPropertyWeightsIsUnmodifiable() {
    // GIVEN
    SimObj simObj = new SimObj(0);
    String key = "key";
    // WHEN
    simObj.getOtherPropertyWeights().put(key, new Double(0.5d));
    // THEN
    Assert.fail();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetMyPropertiesIsUnmodifiable() {
    // GIVEN
    SimObj simObj = new SimObj(0);
    String key = "key";
    // WHEN
    simObj.getMyProperties().put(key, new Double(0.5d));
    // THEN
    Assert.fail();
  }

  @Test
  public void testGetOtherPropertyWeight() {
    // GIVEN
    SimObj simObj = new SimObj(0);
    String key = "key";
    Assert.assertFalse(simObj.getOtherPropertyWeights().containsKey(key));
    double expected = 0.34d;
    // WHEN
    simObj.getOtherPropertyWeight(key, expected);
    // THEN
    Assert.assertEquals(expected, simObj.getOtherPropertyWeight(key, 0.0d), 0.00001d);
    Assert.assertEquals(Double.valueOf(expected), simObj.getOtherPropertyWeights().get(key));
  }

  @Test
  public void testMyProperty() {
    // GIVEN
    Map<String, Double> props = new HashMap<String, Double>();
    props.put("prop1", Double.valueOf(0.11d));
    props.put("prop2", Double.valueOf(0.23d));
    props.put("prop3", Double.valueOf(0.36d));
    SimObj simObj = new SimObj(0, props);
    // WHEN
    Double val1 = simObj.getMyValue("prop1");
    Double val2 = simObj.getMyValue("prop2");
    Double val3 = simObj.getMyValue("prop3");
    Double val0 = simObj.getMyValue("prop0");
    // THEN
    Assert.assertEquals(0.11d, val1.doubleValue(), 0.00001d);
    Assert.assertEquals(0.23d, val2.doubleValue(), 0.00001d);
    Assert.assertEquals(0.36d, val3.doubleValue(), 0.00001d);
    Assert.assertEquals(0.0d, val0.doubleValue(), 0.00001d);
  }
}
