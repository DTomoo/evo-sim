package com.dt.evosim.simulation;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.dt.evosim.domain.SimObj;

public class SimulationStateBuilderTest {

  @Test
  public void testBuild() {
    // GIVEN
    SimulationStateBuilder builder = new SimulationStateBuilder();
    SimObj simObj0 = new SimObj(0);
    SimObj simObj1 = new SimObj(1);
    SimObj simObj2 = new SimObj(2);
    // WHEN
    SimulationState simulationState = builder.build(12, Arrays.asList(simObj0, simObj1, simObj2));
    // THEN
    Assert.assertEquals(12, simulationState.getSimulationAge());
    Assert.assertTrue(simulationState.getSimulationObjectsById().containsValue(simObj0));
    Assert.assertTrue(simulationState.getSimulationObjectsById().containsValue(simObj1));
    Assert.assertTrue(simulationState.getSimulationObjectsById().containsValue(simObj2));
  }
}
