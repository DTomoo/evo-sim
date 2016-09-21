package com.dt.evosim.simulation.breeding;

import java.util.List;

import com.dt.evosim.domain.SimObj;

public interface BreedingStrategy {

  List<SimObj> calculateNextGeneration(List<SimObj> bestSimObjects);
}
