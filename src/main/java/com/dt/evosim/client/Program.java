package com.dt.evosim.client;

import java.io.IOException;
import java.net.UnknownHostException;

import com.dt.evosim.simulation.Simulation;
import com.dt.evosim.simulation.SimulationBuilder;

public class Program {

  public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
    final int width = 200;
    final int height = 200;
    //
    Simulation clientModel = createModel(width, height);
    ClientView clientView = createView(width + 50, height + 50, 5);
    //
    //
    ClientController controller = new ClientController(clientModel, clientView);
    controller.startSim();
  }

  private static ClientView createView(final int width, final int height, final int objSize) {
    ClientView clientView = new ClientView(width, height, objSize);
    clientView.init();
    return clientView;
  }

  private static Simulation createModel(int width, int height) {
    SimulationBuilder sb = new SimulationBuilder(width, height);
    Simulation clientModel = sb.build(5);
    return clientModel;
  }
}
