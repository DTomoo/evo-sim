package com.dt.evosim.simulation;

import java.util.stream.Stream;

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
import com.dt.evosim.simulation.mutation.MutationStrategy;
import com.dt.evosim.simulation.selection.SelectionStrategy;

public class SimulationTest {

  private Environment environment = new Environment(100, 100);
  private Simulation simulation;
  @Mock
  private MutationStrategy mutationStrategy;
  @Mock
  private BreedingStrategy breedingStrategy;
  @Mock
  private SelectionStrategy selectionStrategy;
  @Mock
  private SimulationStateBuilder simulationStateBuilder;
  private static final SimObj SIM_OBJ = new SimObj(123);
  private static final Stream<SimObj> STUB_STREAM1 = Stream.of();
  private static final Stream<SimObj> STUB_STREAM2 = Stream.of(SIM_OBJ);
  private static final Stream<SimObj> STUB_STREAM3 = Stream.of(SIM_OBJ);

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    simulation = new Simulation(environment);

    Mockito.doReturn(STUB_STREAM3).when(mutationStrategy).mutate(STUB_STREAM2);
    Whitebox.setInternalState(simulation, "mutationStrategy", mutationStrategy);


    Mockito.doReturn(STUB_STREAM2).when(breedingStrategy).calculateNextGeneration(STUB_STREAM1);
    Whitebox.setInternalState(simulation, "breedingStrategy", breedingStrategy);


    Mockito.doReturn(STUB_STREAM1).when(selectionStrategy).selectBestObjects(Matchers.any(Stream.class));
    Whitebox.setInternalState(simulation, "selectionStrategy", selectionStrategy);


    SimulationState toBeReturned = new SimulationState(13L);
    toBeReturned.addSimObject(SIM_OBJ);
    Mockito.doReturn(toBeReturned).when(simulationStateBuilder).build(Matchers.anyLong(), Matchers.any(Stream.class));
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
    Mockito.verify(selectionStrategy, Mockito.times(1)).selectBestObjects(Matchers.any(Stream.class));
    Mockito.verify(breedingStrategy, Mockito.times(1)).calculateNextGeneration(Matchers.eq(STUB_STREAM1));
    Mockito.verify(mutationStrategy, Mockito.times(1)).mutate(STUB_STREAM2);
    Mockito.verify(simulationStateBuilder, Mockito.times(1)).build(Matchers.eq(13L), Matchers.eq(STUB_STREAM3));
    Assert.assertEquals(13L, simulationStateActual.getSimulationAge());
    Assert.assertEquals(1, simulationStateActual.getPopulationStream().count());
    Assert.assertTrue(simulationStateActual.getPopulationStream().anyMatch(SIM_OBJ::equals));
  }
}
