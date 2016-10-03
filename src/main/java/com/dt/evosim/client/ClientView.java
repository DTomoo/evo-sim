package com.dt.evosim.client;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

public class ClientView extends JFrame {

  private static final long serialVersionUID = 1L;
  private int objSize;
  private final int width;
  private final int height;
  private JFrame frame;
  private Canvas canvas;

  public ClientView(int w, int h, int objSize) {
    this.width = w;
    this.height = h;
    this.objSize = objSize;
  }

  private JFrame createJFrame() {
    final String title = "Test Window";
    JFrame frame = new JFrame(title);
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
    return frame;
  }

  private Canvas createCanvas() {
    Canvas canvas = new Canvas();
    canvas.setSize(width, height);
    canvas.setBackground(Color.BLACK);
    canvas.setVisible(true);
    canvas.setFocusable(false);
    return canvas;
  }

  public void init() {
    frame = createJFrame();
    canvas = createCanvas();
    frame.add(canvas);
    canvas.createBufferStrategy(1);
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public int getObjSize() {
    return objSize;
  }
}
