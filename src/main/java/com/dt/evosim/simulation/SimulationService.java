package com.dt.evosim.simulation;

import java.util.stream.IntStream;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.domain.SimObjCounter;
import com.dt.evosim.domain.SimObjFactory;

public class SimulationService {

  private SimObjCounter simObjCounter = new SimObjCounter();
  private SimObjFactory simObjFactory = new SimObjFactory();

  public void addRandomObjectsToSimulation(Simulation simulation, int numberOfNewObjects) {
    IntStream.range(0, numberOfNewObjects).forEach(i -> {
      int id = simObjCounter.getAndIncrease();
      SimObj simObj = simObjFactory.randomObject(id);
      simulation.addSimObject(simObj);
    });
  }

  public void cleanUpSimulationObjects(Simulation simulation) {
    simulation.getSimulationObjectsById().entrySet().removeIf(e -> !e.getValue().isLiving());
  }
}
