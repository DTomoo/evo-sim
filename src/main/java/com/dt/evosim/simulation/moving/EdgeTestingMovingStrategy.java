package com.dt.evosim.simulation.moving;

import com.dt.evosim.domain.SimObj;
import com.dt.evosim.simulation.Environment;
import com.dt.physics.common.Position;
import com.dt.physics.common.Vector;

public class EdgeTestingMovingStrategy implements MovingStrategy {

  private Environment environment;

  public EdgeTestingMovingStrategy(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void accept(SimObj simObj) {
    changePosition(simObj);
    changeMovingDirection(simObj);
  }

  private void changeMovingDirection(SimObj simObj) {
    double xDir = simObj.getDirection().getX();
    double yDir = simObj.getDirection().getY();
    boolean changeDirection = false;
    if (environment.isOnWidthEdge(simObj)) {
      xDir *= -1;
      changeDirection = true;
    }
    if (environment.isOnHeightEdge(simObj)) {
      yDir *= -1;
      changeDirection = true;
    }
    if (changeDirection) {
      simObj.setPosition(environment.limitedPosition(simObj));
      Vector direction = new Vector(xDir, yDir);
      simObj.setDirection(direction);
    }
  }

  private void changePosition(SimObj simObj) {
    Position oldPos = simObj.getPosition();
    Position afterCalculated = oldPos.add(simObj.getDirection());
    Position limitedPosition = environment.limitedPosition(afterCalculated, simObj.getSize());
    simObj.setPosition(limitedPosition);
  }

  @Override
  public void move(SimObj simObj) {
    accept(simObj);
  }
}
