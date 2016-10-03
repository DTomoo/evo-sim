package com.dt.evosim.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.simulation.Environment;
import com.dt.evosim.simulation.Simulation;
import com.dt.physics.common.Position;

class ClientController {

  private Simulation simulation;
  private ClientView client;
  //
  private Graphics graphics;
  private boolean running;

  public ClientController(Simulation model, ClientView view) {
    super();
    this.simulation = model;
    this.client = view;
  }

  public void startSim() throws InterruptedException {
    if (!running) {
      running = true;
      BufferStrategy bufferStrategy = client.getCanvas().getBufferStrategy();
      while (running) {
        // reset graphics
        resetGraphics(bufferStrategy.getDrawGraphics());
        // get server data
        simulation.getSimulationState().getSimulationObjects().forEach(this::drawCircle);
        // display graphics
        bufferStrategy.show();
        dispose();
        // trigger server update
        simulation.moveObjects();
        Thread.sleep(50);
      }
    }
  }

  private void drawCircle(SimObj t) {
    Position position = t.getPosition();
    if (t.getId() == 0L) {
//      System.out.println(t.getPosition());
    }
    graphics.drawOval((int) position.getX(), (int) position.getY(), client.getObjSize(), client.getObjSize());
  }

  private void resetGraphics(Graphics drawGraphics) {
    graphics = drawGraphics;
    Environment environment = simulation.getEnvironment();
    graphics.clearRect(environment.getMinWidth(), environment.getMinHeight(),
        environment.getMaxWidth() + client.getObjSize() + 1, environment.getMaxHeight() + client.getObjSize() + 1);
    graphics.setColor(Color.GREEN);
  }

  private void dispose() {
    graphics.dispose();
  }
}
