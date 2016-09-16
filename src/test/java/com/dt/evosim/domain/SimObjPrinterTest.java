package com.dt.evosim.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dt.physics.common.Position;

public class SimObjPrinterTest {

  private static final SimObj simObj = new SimObj(new Position(1, 2));

  @Before
  public void setUp() {}

  @Test
  public void testGetRepresentationWhenObjectIsEmpty() {
    // GIVEN
    SimObjPrinter simObjPrinter = new SimObjPrinter();
    // WHEN
    String representation = simObjPrinter.getRepresentation(null);
    // THEN
    Assert.assertEquals("<null object>", representation);
  }

  @Test
  public void testGetRepresentationWithBaseObject() {
    // GIVEN
    SimObjPrinter simObjPrinter = new SimObjPrinter();
    // WHEN
    String representation = simObjPrinter.getRepresentation(simObj);
    // THEN
    Assert.assertEquals("SimObj-0", representation);
  }
  

  @Test
  public void testGetRepresentationWithPositionWithBaseObject() {
    // GIVEN
    SimObjPrinter simObjPrinter = new SimObjPrinter().withPosition();
    // WHEN
    String representation = simObjPrinter.getRepresentation(simObj);
    // THEN
    Assert.assertEquals("SimObj-0\n[x=1.0, y=2.0]", representation);
  }
}
