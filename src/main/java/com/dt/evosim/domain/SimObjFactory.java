package com.dt.evosim.domain;

import java.util.Random;

import com.dt.physics.common.Position;

public class SimObjFactory {

  private Random rnd = new Random();

  public SimObj randomObject() {
    Position pos = getRandomPosition();
    return new SimObj(pos);
  }

  private Position getRandomPosition() {
    return new Position(rnd.nextInt(10) - 10, rnd.nextInt(10) - 10);
  }
}
