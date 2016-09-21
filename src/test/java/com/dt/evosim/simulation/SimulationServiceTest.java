package com.dt.evosim.simulation;

import java.util.ArrayList;
import java.util.List;

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
  public void testAddRandomObjectsToSimulationState() {
    // GIVEN
    SimulationState simulationState = Mockito.mock(SimulationState.class);
    int numberOfNewObjects = 2;
    // WHEN
    simulationService.addRandomObjectsToSimulationState(simulationState, numberOfNewObjects);
    // THEN
    Mockito.verify(simObjCounter, Mockito.times(numberOfNewObjects)).getAndIncrease();
    Mockito.verify(simObjFactory, Mockito.times(numberOfNewObjects)).randomObject(Matchers.eq(0));
    Mockito.verify(simulationState, Mockito.times(numberOfNewObjects)).addSimObject(Matchers.any(SimObj.class));
  }

  @Test
  public void testFilterOutDiesSimObjects() {
    // GIVEN
    SimObj simObj1 = new SimObj(0);
    SimObj simObj2 = new SimObj(1);
    SimObj simObj3 = new SimObj(2);
    simObj3.die();
    SimObj simObj4 = new SimObj(3);
    //
    List<SimObj> simulationObjects = new ArrayList<>();
    simulationObjects.add(simObj1);
    simulationObjects.add(simObj2);
    simulationObjects.add(simObj3);
    simulationObjects.add(simObj4);
    // WHEN
    List<SimObj> actualResult = simulationService.filterOutDiedSimObjects(simulationObjects);
    // THEN
    Assert.assertTrue(actualResult.contains(simObj1));
    Assert.assertTrue(actualResult.contains(simObj2));
    Assert.assertFalse(actualResult.contains(simObj3));
    Assert.assertTrue(actualResult.contains(simObj4));
  }
}
