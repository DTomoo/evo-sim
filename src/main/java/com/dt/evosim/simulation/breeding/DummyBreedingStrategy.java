package com.dt.evosim.simulation.breeding;

import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;

public class DummyBreedingStrategy implements BreedingStrategy {

  public Stream<SimObj> calculateNextGeneration(Stream<SimObj> source) {
    return source;
  }
}
