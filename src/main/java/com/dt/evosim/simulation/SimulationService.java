package com.dt.evosim.simulation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.domain.SimObjCounter;
import com.dt.evosim.domain.SimObjFactory;

public class SimulationService {

  private SimObjCounter simObjCounter = new SimObjCounter();
  private SimObjFactory simObjFactory;
  private Simulation simulation;

  public SimulationService(Simulation simulation) {
    this.simulation = simulation;
    simObjFactory = new SimObjFactory(simulation.getEnvironment());
  }

  public void addRandomObjectsToSimulationState(int numberOfNewObjects) {
    IntStream.range(0, numberOfNewObjects)
        .map(simObjCounter::getAndIncrease)
        .mapToObj(simObjFactory::randomObject)
        .forEach(simulation.getSimulationState()::addSimObject);
  }

  public List<SimObj> filterOutDiedSimObjects(Collection<SimObj> simObjectsById) {
    return simObjectsById.parallelStream().//
        filter(SimObj::isLiving).//
        collect(Collectors.toList());
  }
}
