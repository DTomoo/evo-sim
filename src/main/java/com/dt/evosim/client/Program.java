package com.dt.evosim.client;

import java.io.IOException;
import java.net.UnknownHostException;

import com.dt.evosim.simulation.Simulation;
import com.dt.evosim.simulation.SimulationBuilder;

public class Program {

  public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
    final int width = 200;
    final int height = 500;
    final int border = 30;

    Simulation clientModel = createModel(width, height);
    ClientView clientView = createView(width, height, border);

    ClientController controller = new ClientController(clientModel, clientView);
    controller.startSim();
  }

  private static ClientView createView(final int width, final int height, int borderSize) {
    ClientView clientView = new ClientView(width, height, borderSize);
    clientView.init();
    return clientView;
  }

  private static Simulation createModel(int width, int height) {
    SimulationBuilder sb = new SimulationBuilder(width, height);
    Simulation clientModel = sb.build(1);
    return clientModel;
  }
}
