package com.dt.evosim.simulation.breeding;

import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;

public interface BreedingStrategy {

  Stream<SimObj> calculateNextGeneration(Stream<SimObj> bestSimObjects);
}
