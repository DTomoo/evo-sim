package com.dt.evosim.simulation;

import java.util.HashMap;
import java.util.Map;

import com.dt.evosim.domain.SimObj;

public class Simulation {

  private long simulationAge = 0L;
  private Map<Integer, SimObj> simulationObjectsById = new HashMap<Integer, SimObj>();

  public Map<Integer, SimObj> getSimulationObjectsById() {
    return simulationObjectsById;
  }

  public void addSimObject(SimObj simObj) {
    Integer id = Integer.valueOf(simObj.getId());
    simulationObjectsById.put(id, simObj);
  }
}
