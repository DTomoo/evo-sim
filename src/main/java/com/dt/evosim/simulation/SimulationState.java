package com.dt.evosim.simulation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dt.evosim.domain.SimObj;

public class SimulationState implements Serializable {

  private static final long serialVersionUID = 1L;
  //
  private final long simulationAge;
  private final Map<Integer, SimObj> simulationObjectsById = new HashMap<>();

  public SimulationState(long simulationAge) {
    this.simulationAge = simulationAge;
  }

  public long getSimulationAge() {
    return simulationAge;
  }

  public Map<Integer, SimObj> getSimulationObjectsById() {
    return simulationObjectsById;
  }

  public void addSimObject(SimObj simObj) {
    Integer id = Integer.valueOf(simObj.getId());
    simulationObjectsById.put(id, simObj);
  }
}
