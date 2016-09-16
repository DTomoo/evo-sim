package com.dt.evosim.domain;

import java.util.concurrent.atomic.AtomicInteger;

import com.dt.physics.common.Position;

public class SimObj {

  private Position position;
  private AtomicInteger age = new AtomicInteger(0);
  private double speed = 0.0d;

  public SimObj(Position position) {
    this.position = position;
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
