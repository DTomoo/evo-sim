package com.dt.evosim.domain;

import java.util.concurrent.atomic.AtomicInteger;

import com.dt.physics.common.Position;

public class SimObj {

  private static final AtomicInteger COUNTER = new AtomicInteger(0);
  private int id = COUNTER.getAndIncrement();
  private Position position;
  private AtomicInteger age = new AtomicInteger(0);
  private double speed = 0.0d;

  public SimObj(Position position) {
    this.position = position;
  }

  public int getId() {
    return id;
  }

  public int getAge() {
    return age.get();
  }

  public int incrementAge() {
    return age.incrementAndGet();
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getSpeed() {
    return speed;
  }

  public Position getPosition() {
    return position;
  }
}
