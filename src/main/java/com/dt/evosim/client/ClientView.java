package com.dt.evosim.client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.simulation.Environment;
import com.dt.evosim.simulation.Simulation;
import com.dt.evosim.simulation.SimulationState;
import com.dt.physics.common.Position;

public class ClientView extends JFrame {

  private static final int TOP_BOTTOM_BORDER = 30;
  private static final int SIDE_BORDER = 7;
  private static final long serialVersionUID = 1L;

  /** width of the window */
  private final int frameWidth;
  /** height of the window */
  private final int frameHeight;

  /** width of the drawing view space */
  private final int viewWidth;
  /** height of the drawing view space */
  private final int viewHeight;

  /** border size out of the drawing view space */
  private final int borderSize;
  /** canvas on the window */
  private Canvas canvas;
  private Graphics graphics;
  private BufferStrategy bufferStrategy;

  public ClientView(int w, int h, int borderSize) {
    this.viewWidth = w;
    this.viewHeight = h;
    this.frameWidth = w + 2 * borderSize;
    this.frameHeight = h + 2 * borderSize;
    this.borderSize = borderSize;
  }

  public void displaySimulation(Simulation simulation) {
    resetGraphics(simulation);
    drawObjects(simulation.getSimulationState());
    simulation.getSimulationState().getSimulationObjects().forEach(this::drawCircle);
    flushDrawing();
  }

  private void initJFrame() {
    final String title = "Test Window";
    this.setTitle(title);
    this.setSize(frameWidth + SIDE_BORDER, frameHeight + TOP_BOTTOM_BORDER);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setVisible(true);
  }

  private Canvas createCanvas() {
    Canvas canvas = new Canvas();
    canvas.setSize(frameWidth, frameHeight);
    canvas.setBackground(Color.GRAY);
    canvas.setVisible(true);
    canvas.setFocusable(false);
    return canvas;
  }

  public void init() {
    initJFrame();
    canvas = createCanvas();
    canvas.createBufferStrategy(1);
    bufferStrategy = canvas.getBufferStrategy();

    this.add(canvas);
  }

  private Position getViewPosition(Position pos) {
    return new Position(pos.getX() + borderSize, pos.getY() + borderSize);
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public int getBorderSize() {
    return borderSize;
  }

  public int getViewHeight() {
    return viewHeight;
  }

  public int getViewWidth() {
    return viewWidth;
  }

  private void resetGraphics(Simulation simulation) {
    graphics = bufferStrategy.getDrawGraphics();
    Environment env = simulation.getEnvironment();
    int x = env.getMinWidth() + borderSize;
    int y = env.getMinHeight() + borderSize;
    int w = env.getMaxWidth() - env.getMinWidth() + 1;
    int h = env.getMaxHeight() - env.getMinHeight() + 1;
    graphics.clearRect(x, y, w, h);
    graphics.setColor(Color.GREEN);
    graphics.drawRect(borderSize, borderSize, viewWidth, viewHeight);
  }

  private void drawCircle(SimObj simObj) {
    int size = simObj.getSize();
    Position drawPos = getViewPosition(simObj.getPosition());
    graphics.drawOval(drawPos.getX() - size, drawPos.getY() - size, 2 * size, 2 * size);
  }

  private void flushDrawing() {
    bufferStrategy.show();
    graphics.dispose();
  }

  private void drawObjects(SimulationState simulationState) {
    simulationState.getSimulationObjects().forEach(this::drawCircle);
  }
}
