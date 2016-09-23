package com.dt.evosim.simulation;

public class SimulationBuilder {

  private SimulationService simulationService = new SimulationService();
  private Simulation simulation = new Simulation();

  public Simulation build() {
    
    
    SimulationState simulationState = simulation.getSimulationState();
    simulationService.addRandomObjectsToSimulationState(simulationState, 10);
    return simulation;
  }
  
}
