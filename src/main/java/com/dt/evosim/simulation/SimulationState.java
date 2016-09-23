package com.dt.evosim.simulation;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.stream.Stream;

import com.dt.evosim.domain.Population;
import com.dt.evosim.domain.SimObj;

public class SimulationState implements Serializable {

  private static final long serialVersionUID = 1L;
  //
  private final long simulationAge;
  private final Population population = new Population();

  public SimulationState(long simulationAge) {
    this.simulationAge = simulationAge;
  }

  public long getSimulationAge() {
    return simulationAge;
  }

  public Stream<SimObj> getSimulationObjects() {
    return population.getPopulation();
  }

  public void addSimObject(SimObj simObj) {
    population.add(simObj);
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner("\n");
    sj.add("----------------------------------------");
    sj.add("simAge=" + simulationAge);
    sj.add(population.toString());
    sj.add("========================================");
    return sj.toString();
  }
}
