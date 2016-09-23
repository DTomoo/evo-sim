package com.dt.evosim.app;

import com.dt.evosim.simulation.Simulation;
import com.dt.evosim.simulation.SimulationBuilder;
import com.dt.evosim.simulation.SimulationService;

public class Main {

  private static SimulationService simulationService = new SimulationService();
  private static Simulation simulation = new SimulationBuilder().build();

  public static void main(String[] args) {
    System.out.println(simulation);
    
  }
}
