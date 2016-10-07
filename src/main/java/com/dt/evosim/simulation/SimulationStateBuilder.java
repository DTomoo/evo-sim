package com.dt.evosim.simulation;

import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;

public class SimulationStateBuilder {

  public SimulationState build(long newAge, Stream<SimObj> population) {
    SimulationState state = new SimulationState(newAge);
    population.forEach(state::addSimObject);
    return state;
  }
}
