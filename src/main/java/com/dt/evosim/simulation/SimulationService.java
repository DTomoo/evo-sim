package com.dt.evosim.simulation;

import java.util.Iterator;
import java.util.Map.Entry;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.domain.SimObjCounter;
import com.dt.evosim.domain.SimObjFactory;

public class SimulationService {

  private SimObjCounter simObjCounter = new SimObjCounter();
  private SimObjFactory simObjFactory = new SimObjFactory();

  public void addRandomObjectsToSimulation(Simulation simulation, int numberOfNewObjects) {
    for (int i = 0; i < numberOfNewObjects; i++) {
      int id = simObjCounter.getAndIncrease();
      SimObj simObj = simObjFactory.randomObject(id);
      simulation.addSimObject(simObj);
    }
  }

  public void cleanUpSimulationObjects(Simulation simulation) {
    Iterator<Entry<Integer, SimObj>> iterator = simulation.getSimulationObjectsById().entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<Integer, SimObj> entry = iterator.next();
      if (!entry.getValue().isLiving()) {
        iterator.remove();
      }
    }
  }
}
