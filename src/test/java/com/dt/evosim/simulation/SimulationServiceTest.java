package com.dt.evosim.simulation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.domain.SimObjCounter;
import com.dt.evosim.domain.SimObjFactory;

public class SimulationServiceTest {

  private SimulationService simulationService;
  @Mock
  private SimObjCounter simObjCounter;
  @Mock
  private SimObjFactory simObjFactory;
  private static final SimObj SIM_OBJ = new SimObj(0);

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    simulationService = new SimulationService();
    //
    Mockito.doReturn(0).when(simObjCounter).getAndIncrease();
    Whitebox.setInternalState(simulationService, "simObjCounter", simObjCounter);
    //
    Mockito.doReturn(SIM_OBJ).when(simObjFactory).randomObject(Matchers.anyInt());
    Whitebox.setInternalState(simulationService, "simObjFactory", simObjFactory);
  }

  @Test
  public void testAddRandomObjectsToSimulation() {
    // GIVEN
    Simulation simulation = Mockito.mock(Simulation.class);
    int numberOfNewObjects = 2;
    // WHEN
    simulationService.addRandomObjectsToSimulation(simulation, numberOfNewObjects);
    // THEN
    Mockito.verify(simObjCounter, Mockito.times(numberOfNewObjects)).getAndIncrease();
    Mockito.verify(simObjFactory, Mockito.times(numberOfNewObjects)).randomObject(Matchers.eq(0));
    Mockito.verify(simulation, Mockito.times(numberOfNewObjects)).addSimObject(Matchers.any(SimObj.class));
  }

  @Test
  public void cleanUpSimulationObjects() {
    // GIVEN
    SimObj simObj1 = new SimObj(0);
    SimObj simObj2 = new SimObj(1);
    SimObj simObj3 = new SimObj(2);
    simObj3.die();
    SimObj simObj4 = new SimObj(3);
    //
    Simulation simulation = new Simulation();
    simulation.addSimObject(simObj1);
    simulation.addSimObject(simObj2);
    simulation.addSimObject(simObj3);
    simulation.addSimObject(simObj4);
    //
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj1));
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj2));
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj3));
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj4));
    // WHEN
    simulationService.cleanUpSimulationObjects(simulation);
    // THEN
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj1));
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj2));
    Assert.assertFalse(simulation.getSimulationObjectsById().containsValue(simObj3));
    Assert.assertTrue(simulation.getSimulationObjectsById().containsValue(simObj4));
  }
}
