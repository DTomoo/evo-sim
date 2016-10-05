package com.dt.evosim.simulation;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.simulation.breeding.BreedingStrategy;
import com.dt.evosim.simulation.breeding.DummyBreedingStrategy;
import com.dt.evosim.simulation.moving.EdgeTestingMovingStrategy;
import com.dt.evosim.simulation.moving.MovingStrategy;
import com.dt.evosim.simulation.selection.SelectionStrategy;
import com.dt.evosim.simulation.selection.YoungestSelectionStrategy;

public class Simulation {

  private final Environment environment;
  // services
  private SimulationStateBuilder simulationStateBuilder = new SimulationStateBuilder();
  private SelectionStrategy selectionStrategy = new YoungestSelectionStrategy();
  private BreedingStrategy breedingStrategy = new DummyBreedingStrategy();
  private MovingStrategy movingStrategy;
  // inner state
  private int selectionNumber = 10;
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
    List<SimObj> bestSimObjects = selection();
    List<SimObj> nextGeneration = breeding(bestSimObjects);
    settingNextState(nextGeneration);
  }

  public void moveObjects() {
    collisionHandling();
    simulationState.getSimulationObjects().forEach(movingStrategy);
  }

  public double getDistance(SimObj o1, SimObj o2) {
    return 0.0d;
  }

  public void collisionHandling() {
    // simulationState.getSimulationObjects()
    // .forEach(x -> simulationState.getSimulationObjects()
    // .forEach(y -> System.out.println(y)));
    simulationState.getSimulationObjects().forEach(
        x -> simulationState.getSimulationObjects().filter(y -> areColliding(x, y)).forEach(y -> collide(x, y)));
  }

  private boolean areColliding(SimObj obj1, SimObj obj2) {
    return obj1.getId() < obj2.getId() && getDistance(obj1, obj2) <= obj1.getSize() + obj2.getSize();
  }

  private void collide(SimObj obj1, SimObj obj2) {
//    System.out.println(obj1.getId() + "->" + obj2.getId());
  }

  private List<SimObj> selection() {
    return selectionStrategy.selectN(selectionNumber,
        simulationState.getSimulationObjects().collect(Collectors.toList()));
  }

  private List<SimObj> breeding(List<SimObj> bestSimObjects) {
    return breedingStrategy.calculateNextGeneration(bestSimObjects);
  }

  private void settingNextState(List<SimObj> nextGeneration) {
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
