package com.dt.evosim.simulation;

import java.util.Arrays;
import java.util.Collections;
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
import com.dt.evosim.simulation.breeding.BreedingStrategy;
import com.dt.evosim.simulation.selection.SelectionStrategy;

public class SimulationTest {

  private Simulation simulation;
  @Mock
  private BreedingStrategy breedingStrategy;
  @Mock
  private SelectionStrategy selectionStrategy;
  @Mock
  private SimulationStateBuilder simulationStateBuilder;
  private static final List<SimObj> STUB_LIST1 = Collections.emptyList();
  private static final SimObj SIM_OBJ = new SimObj(123);
  private static final List<SimObj> STUB_LIST2 = Arrays.asList(SIM_OBJ);

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    simulation = new Simulation();
    //
    Mockito.doReturn(STUB_LIST2).when(breedingStrategy).calculateNextGeneration(STUB_LIST1);
    Whitebox.setInternalState(simulation, "breedingStrategy", breedingStrategy);
    //
    Mockito.doReturn(STUB_LIST1).when(selectionStrategy).selectN(Matchers.anyInt(),
        Matchers.anyCollectionOf(SimObj.class));
    Whitebox.setInternalState(simulation, "selectionStrategy", selectionStrategy);
    //
    SimulationState toBeReturned = new SimulationState(13L);
    toBeReturned.addSimObject(SIM_OBJ);
    Mockito.doReturn(toBeReturned).when(simulationStateBuilder).build(Matchers.anyLong(),
        Matchers.anyListOf(SimObj.class));
    Whitebox.setInternalState(simulation, "simulationStateBuilder", simulationStateBuilder);
  }

  @Test
  public void testNextCycle() {
    // GIVEN
    SimulationState simulationState = new SimulationState(12L);
    simulation.setSimulationState(simulationState);
    // WHEN
    simulation.nextCycle();
    SimulationState simulationStateActual = simulation.getSimulationState();
    // THEN
    Mockito.verify(selectionStrategy, Mockito.times(1)).selectN(Matchers.anyInt(),
        Matchers.anyCollectionOf(SimObj.class));
    Mockito.verify(breedingStrategy, Mockito.times(1)).calculateNextGeneration(Matchers.eq(STUB_LIST1));
    Mockito.verify(simulationStateBuilder, Mockito.times(1)).build(Matchers.eq(13L), Matchers.eq(STUB_LIST2));
    Assert.assertEquals(13L, simulationStateActual.getSimulationAge());
    Assert.assertEquals(1, simulationStateActual.getSimulationObjectsById().size());
    Assert.assertTrue(simulationStateActual.getSimulationObjectsById().containsValue(SIM_OBJ));
  }
}
