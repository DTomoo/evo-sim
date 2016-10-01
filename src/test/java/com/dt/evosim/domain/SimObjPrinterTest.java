package com.dt.evosim.domain;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.dt.physics.common.Position;
import com.dt.physics.common.Vector;

public class SimObjPrinterTest {

  private static final Map<String, Double> PROP = createFixedMyProperties();

  private static HashMap<String, Double> createFixedMyProperties() {
    HashMap<String, Double> hashMap = new HashMap<String, Double>();
    hashMap.put("x", Double.valueOf(1.0d));
    hashMap.put("y", Double.valueOf(2.0d));
    hashMap.put("z", Double.valueOf(3.0d));
    return hashMap;
  }

  private static final Position POSITION = new Position(1, 2);
  private static final Vector DIRECTION = new Vector(1, 2);
  private static final SimObj SIM_OBJ = new SimObj(0, PROP, POSITION, DIRECTION);

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
    Assert.assertEquals("SimObj-0[x=1,y=2]", representation);
  }

  @Test
  public void testGetRepresentationWithMyPropertiesWithBaseObject() {
    // GIVEN
    SimObjPrinter simObjPrinter = new SimObjPrinter().withMyProperties();
    // WHEN
    String representation = simObjPrinter.getRepresentation(SIM_OBJ);
    // THEN
    Assert.assertEquals("SimObj-0\nMy properties:(x=1.0, y=2.0, z=3.0)", representation);
  }
}
