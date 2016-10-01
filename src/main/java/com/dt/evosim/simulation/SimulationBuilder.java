package com.dt.evosim.simulation;

public class SimulationBuilder {

  private SimulationService simulationService;
  private Simulation simulation;

  public SimulationBuilder(int width, int height) {
    simulation = new Simulation(new Environment(width, height));
    simulationService = new SimulationService(simulation);
  }

  public Simulation build(int number) {
    simulationService.addRandomObjectsToSimulationState(number);
    return simulation;
  }
}
