package com.dt.evosim.simulation;

import java.util.List;

import com.dt.evosim.domain.SimObj;

public class SimulationStateBuilder {

  public SimulationState build(long newAge, List<SimObj> population) {
    SimulationState state = new SimulationState(newAge);
    population.forEach(state::addSimObject);
    return state;
  }
}
