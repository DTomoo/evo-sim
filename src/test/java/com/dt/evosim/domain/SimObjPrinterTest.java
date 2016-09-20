package com.dt.evosim.domain;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dt.physics.common.Position;

public class SimObjPrinterTest {

  private static final Map<String, Double> PROP = createFixedMyProperties();

  private static HashMap<String, Double> createFixedMyProperties() {
    HashMap<String, Double> hashMap = new HashMap<String, Double>();
    hashMap.put("x", 1.0d);
    hashMap.put("y", 2.0d);
    hashMap.put("z", 3.0d);
    return hashMap;
  }

  private static final Position POSITION = new Position(1, 2);
  private static final SimObj SIM_OBJ = new SimObj(0, PROP, POSITION);

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
    String representation = simObjPrinter.getRepresentation(SIM_OBJ);
    // THEN
    Assert.assertEquals("SimObj-0", representation);
  }

  @Test
  public void testGetRepresentationWithPositionWithBaseObject() {
    // GIVEN
    SimObjPrinter simObjPrinter = new SimObjPrinter().withPosition();
    // WHEN
    String representation = simObjPrinter.getRepresentation(SIM_OBJ);
    // THEN
    Assert.assertEquals("SimObj-0\n[x=1.0, y=2.0]", representation);
  }

  @Test
  public void testGetRepresentationWithMyPropertiesWithBaseObject() {
    // GIVEN
    SimObjPrinter simObjPrinter = new SimObjPrinter().withMyProperties();
    // WHEN
    String representation = simObjPrinter.getRepresentation(SIM_OBJ);
    // THEN
    Assert.assertEquals("SimObj-0\n My properties:\n* x: 1.0\n* y: 2.0\n* z: 3.0", representation);
  }
}
