package com.dt.evosim.simulation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    SimulationState simulationState = builder.build(12, Stream.of(simObj0, simObj1, simObj2));
    // THEN
    Assert.assertEquals(12, simulationState.getSimulationAge());
    List<SimObj> simObjects = simulationState.getPopulationParallelStream().collect(Collectors.toList());
    Assert.assertTrue(simObjects.contains(simObj0));
    Assert.assertTrue(simObjects.contains(simObj1));
    Assert.assertTrue(simObjects.contains(simObj2));
  }
}
