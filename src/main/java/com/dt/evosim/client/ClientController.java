package com.dt.evosim.client;

import com.dt.evosim.simulation.Simulation;

class ClientController {

  private Simulation simulation;
  private ClientView client;

  private boolean running;

  public ClientController(Simulation model, ClientView view) {
    this.simulation = model;
    this.client = view;
  }

  public void startSim() throws InterruptedException {
    if (!running) {
      running = true;
      while (running) {
        client.displaySimulation(simulation);
        simulation.moveObjects();
        Thread.sleep(50);
      }
    }
  }
}
