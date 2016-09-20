package com.dt.evosim.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dt.evosim.domain.SimObj;
import com.dt.physics.common.Position;
import com.dt.physics.common.Vector;
import com.dt.physics.service.PositionCalculatorService;

public class ObjectBehaviorServiceTest {

  private ObjectBehaviorService objectBehaviorService;

  @Before
  public void setUp() {
    objectBehaviorService = new ObjectBehaviorService();
    //
    mockRandomWeightService();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBehaviorWeightWhenFirstParamIsNull() {
    // GIVEN
    SimObj source = null;
    SimObj target = new SimObj(0);
    // WHEN
    objectBehaviorService.getBehaviorWeight(source, target);
    // THEN
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBehaviorWeightWhenSecondParamIsNull() {
    // GIVEN
    SimObj source = new SimObj(0);
    SimObj target = null;
    // WHEN
    objectBehaviorService.getBehaviorWeight(source, target);
    // THEN
    Assert.fail();
  }

  @Test
  public void testGetBehaviorWeightWhenEverythingIsEmpty() {
    // GIVEN
    SimObj source = new SimObj(0);
    SimObj target = new SimObj(0);
    // WHEN
    double behaviorWeight = objectBehaviorService.getBehaviorWeight(source, target);
    // THEN
    Assert.assertEquals(0.0d, behaviorWeight, 0.00001d);
  }

  @Test
  public void testGetBehaviorWeight() {
    // GIVEN
    Map<String, Double> myProps = new HashMap<String, Double>();
    myProps.put("myA", 0.1d);
    myProps.put("myB", 0.1d);
    myProps.put("myC", 0.1d);
    SimObj source = new SimObj(0, myProps);
    SimObj target = new SimObj(0, myProps);
    // WHEN
    double behaviorWeight = objectBehaviorService.getBehaviorWeight(source, target);
    // THEN
    Assert.assertEquals(0.5d, behaviorWeight, 0.00001d);
  }

  @Test
  public void testGetBehaviorWeightIsInProperInterval() {
    // GIVEN
    Map<String, Double> myProps = new HashMap<String, Double>();
    myProps.put("myA", 0.1d);
    myProps.put("myB", 0.1d);
    myProps.put("myC", 0.1d);
    SimObj source = new SimObj(0, myProps);
    SimObj target = new SimObj(0, myProps);
    // WHEN
    double behaviorWeight;
    for (int i = 0; i < 1000; i++) {
      behaviorWeight = objectBehaviorService.getBehaviorWeight(source, target);
      // THEN
      Assert.assertTrue(-1.0d <= behaviorWeight);
      Assert.assertTrue(behaviorWeight <= 1.0d);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDirectionVectorWhenFirstParamIsNull() {
    // GIVEN
    SimObj source = null;
    SimObj target = new SimObj(0);
    // WHEN
    objectBehaviorService.getDirectionVector(source, target);
    // THEN
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDirectionVectorWhenSecondParamIsNull() {
    // GIVEN
    SimObj source = new SimObj(0);
    SimObj target = null;
    // WHEN
    objectBehaviorService.getDirectionVector(source, target);
    // THEN
    Assert.fail();
  }

  @Test
  public void testGetDirectionVector() {
    // GIVEN
    mockPositionCalculatorService(new Vector(3, 4));
    SimObj source = new SimObj(0);
    SimObj target = new SimObj(0);
    objectBehaviorService = Mockito.spy(objectBehaviorService);
    Mockito.doReturn(10.0d).when(objectBehaviorService).getBehaviorWeight(Matchers.any(SimObj.class),
        Matchers.any(SimObj.class));
    // WHEN
    Vector directionVector = objectBehaviorService.getDirectionVector(source, target);
    // THEN
    Assert.assertEquals(new Vector(6, 8), directionVector);
  }

  private void mockPositionCalculatorService(Vector returnValue) {
    PositionCalculatorService positionCalculatorService = Mockito.mock(PositionCalculatorService.class);
    Mockito.when(positionCalculatorService.getVector(Matchers.any(Position.class), Matchers.any(Position.class)))
        .thenReturn(returnValue);
    Whitebox.setInternalState(objectBehaviorService, "positionCalculatorService", positionCalculatorService);
  }

  private void mockRandomWeightService() {
    RandomWeightService randomWeightService = Mockito.mock(RandomWeightService.class);
    Mockito.when(randomWeightService.getRandomWeight()).thenReturn(0.2d).thenReturn(0.7d).thenReturn(0.6d);
    Whitebox.setInternalState(objectBehaviorService, "randomWeightService", randomWeightService);
  }
}
