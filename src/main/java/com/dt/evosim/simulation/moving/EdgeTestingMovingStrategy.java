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
    changeMovingDirection(simObj);
    changePosition(simObj);
  }

  private void changeMovingDirection(SimObj simObj) {
    Vector movingVector = simObj.getMovingVector();
    double xDir = movingVector.getX();
    double yDir = movingVector.getY();
    boolean changeDirection = false;
    Position nextPosition = simObj.getNextPosition();
    int size = simObj.getSize();
    if (environment.isOutOfWidth(nextPosition, size)) {
      xDir *= -1;
      changeDirection = true;
    }
    if (environment.isOutOfHeight(nextPosition, size)) {
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
    Position afterCalculated = oldPos.add(simObj.getMovingVector());
    Position limitedPosition = environment.limitedPosition(afterCalculated, simObj.getSize());
    simObj.setPosition(limitedPosition);
  }

  @Override
  public void move(SimObj simObj) {
    accept(simObj);
  }
}
