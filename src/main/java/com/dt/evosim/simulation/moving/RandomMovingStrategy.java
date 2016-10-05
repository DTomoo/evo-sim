package com.dt.evosim.simulation.moving;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.random.Random;
import com.dt.evosim.simulation.Environment;
import com.dt.physics.common.Position;

public class RandomMovingStrategy implements MovingStrategy {

  private Random rnd = new Random();
  private Environment environment;

  public RandomMovingStrategy(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void accept(SimObj simObj) {
    Position oldPos = simObj.getPosition();
    int sign = rnd.getRandomSignum();
    int x, y;
    if (sign == 0) {
      x = oldPos.getX();
      y = environment.limitedHeight(oldPos.getY() + rnd.getRandomXorY(1, -1), simObj.getSize());
    } else {
      x = environment.limitedWidth(oldPos.getX() + sign, simObj.getSize());
      y = oldPos.getY();
    }
    simObj.setPosition(new Position(x, y));
  }

  @Override
  public void move(SimObj simObj) {
    accept(simObj);
  }
}
