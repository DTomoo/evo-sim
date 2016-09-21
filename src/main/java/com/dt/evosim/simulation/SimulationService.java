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
  private SimObjFactory simObjFactory = new SimObjFactory();

  public void addRandomObjectsToSimulationState(SimulationState simulationState, int numberOfNewObjects) {
    IntStream.range(0, numberOfNewObjects).forEach(i -> {
      int id = simObjCounter.getAndIncrease();
      SimObj simObj = simObjFactory.randomObject(id);
      simulationState.addSimObject(simObj);
    });
  }

  public List<SimObj> filterOutDiedSimObjects(Collection<SimObj> simObjectsById) {
    return simObjectsById.stream().filter(SimObj::isLiving).collect(Collectors.toList());
  }
}
