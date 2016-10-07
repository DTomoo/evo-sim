package com.dt.evosim.simulation.mutation;

import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;

public interface MutationStrategy {

  default public Stream<SimObj> mutate(Stream<SimObj> simObjects) {
    return simObjects.map(this::mutate);
  }

  SimObj mutate(SimObj simObj);
}
