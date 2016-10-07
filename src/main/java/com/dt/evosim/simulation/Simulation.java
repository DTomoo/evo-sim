package com.dt.evosim.simulation;

import java.util.StringJoiner;
import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.simulation.breeding.BreedingStrategy;
import com.dt.evosim.simulation.breeding.DummyBreedingStrategy;
import com.dt.evosim.simulation.moving.EdgeTestingMovingStrategy;
import com.dt.evosim.simulation.moving.MovingStrategy;
import com.dt.evosim.simulation.mutation.DummyMutationStrategy;
import com.dt.evosim.simulation.mutation.MutationStrategy;
import com.dt.evosim.simulation.selection.SelectionStrategy;
import com.dt.evosim.simulation.selection.YoungestSelectionStrategy;

public class Simulation {

  private final Environment environment;
  // services
  private SimulationStateBuilder simulationStateBuilder = new SimulationStateBuilder();
  private SelectionStrategy selectionStrategy = new YoungestSelectionStrategy(10);
  private BreedingStrategy breedingStrategy = new DummyBreedingStrategy();
  private MutationStrategy mutationStrategy = new DummyMutationStrategy();
  private MovingStrategy movingStrategy;
  // inner state
  private SimulationState simulationState = new SimulationState(0);

  public Simulation(Environment environment) {
    this.environment = environment;
    this.movingStrategy = new EdgeTestingMovingStrategy(environment);
  }

  public void setSimulationState(SimulationState simulationState) {
    if (simulationState == null) {
      throw new RuntimeException();
    }
    this.simulationState = simulationState;
  }

  public void nextCycle() {
    Stream<SimObj> bestSimObjects = selection();
    Stream<SimObj> spawnedObjects = breeding(bestSimObjects);
    Stream<SimObj> mutations = mutating(spawnedObjects);
    settingNextState(mutations);
  }

  public void moveObjects() {
    simulationState.getPopulationParallelStream().forEach(movingStrategy);
  }

  public double getDistance(SimObj o1, SimObj o2) {
    return o1.getPosition().getVectorTo(o2.getPosition()).getScalar();
  }

  private Stream<SimObj> selection() {
    return selectionStrategy.selectBestObjects(simulationState.getPopulationParallelStream());
  }

  private Stream<SimObj> breeding(Stream<SimObj> bestSimObjects) {
    return breedingStrategy.calculateNextGeneration(bestSimObjects);
  }

  private Stream<SimObj> mutating(Stream<SimObj> simObjects) {
    return mutationStrategy.mutate(simObjects);
  }

  private void settingNextState(Stream<SimObj> nextGeneration) {
    long newAge = simulationState.getSimulationAge() + 1;
    setSimulationState(simulationStateBuilder.build(newAge, nextGeneration));
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner("\n");
    sj.add("Simulation");
    sj.add(simulationState.toString());
    return sj.toString();
  }

  public Environment getEnvironment() {
    return environment;
  }

  public SimulationState getSimulationState() {
    return simulationState;
  }
}
