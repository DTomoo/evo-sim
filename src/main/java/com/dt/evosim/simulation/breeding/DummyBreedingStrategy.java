package com.dt.evosim.simulation.breeding;

import java.util.List;

import com.dt.evosim.domain.SimObj;

public class DummyBreedingStrategy implements BreedingStrategy {

  public List<SimObj> calculateNextGeneration(List<SimObj> source) {
    return source;
  }
}
