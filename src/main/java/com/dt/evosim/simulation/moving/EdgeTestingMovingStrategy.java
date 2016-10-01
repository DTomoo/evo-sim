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
    if (environment.isWidthLimit(simObj.getPosition())) {
      xDir *= -1;
      changeDirection = true;
    }
    if (environment.isHeightLimit(simObj.getPosition())) {
      yDir *= -1;
      changeDirection = true;
    }
    if (changeDirection) {
      simObj.setDirection(new Vector(xDir, yDir));
    }
  }

  private void changePosition(SimObj simObj) {
    Position oldPos = simObj.getPosition();
    Position afterCalculated = oldPos.add(simObj.getDirection());
    Position limitedPosition = environment.limitedPosition(afterCalculated);
    simObj.setPosition(limitedPosition);
  }

  @Override
  public void move(SimObj simObj) {
    accept(simObj);
  }
}
