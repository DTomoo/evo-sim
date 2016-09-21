package com.dt.evosim.simulation;

import java.util.List;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.simulation.breeding.BreedingStrategy;
import com.dt.evosim.simulation.breeding.DummyBreedingStrategy;
import com.dt.evosim.simulation.selection.SelectionStrategy;
import com.dt.evosim.simulation.selection.YoungestSelectionStrategy;

public class Simulation {

  // services
  private SimulationStateBuilder simulationStateBuilder = new SimulationStateBuilder();
  private SelectionStrategy selectionStrategy = new YoungestSelectionStrategy();
  private BreedingStrategy breedingStrategy = new DummyBreedingStrategy();
  // inner state
  private int selectionNumber = 10;
  private SimulationState simulationState = new SimulationState(0);

  public SimulationState getSimulationState() {
    return simulationState;
  }

  public void setSimulationState(SimulationState simulationState) {
    if (simulationState == null) {
      throw new RuntimeException();
    }
    this.simulationState = simulationState;
  }

  public void nextCycle() {
    List<SimObj> bestSimObjects = selectionStrategy.selectN(selectionNumber,
        simulationState.getSimulationObjectsById().values());
    //
    List<SimObj> nextGeneration = breedingStrategy.calculateNextGeneration(bestSimObjects);
    long newAge = simulationState.getSimulationAge() + 1;
    //
    setSimulationState(simulationStateBuilder.build(newAge, nextGeneration));
  }
}
