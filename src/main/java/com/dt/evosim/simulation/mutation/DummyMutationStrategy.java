package com.dt.evosim.simulation.mutation;

import com.dt.evosim.domain.SimObj;

public class DummyMutationStrategy implements MutationStrategy {

  public SimObj mutate(SimObj simObj) {
    return simObj;
  };
}
