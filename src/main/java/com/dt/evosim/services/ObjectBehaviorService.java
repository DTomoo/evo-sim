package com.dt.evosim.services;

import com.dt.evosim.domain.SimObj;
import com.dt.physics.common.Vector;
import com.dt.physics.service.PositionCalculatorService;
import com.dt.physics.service.PositionCalculatorServiceImpl;

public class ObjectBehaviorService {

  private RandomWeightService randomWeightService = new RandomWeightService();
  private PositionCalculatorService positionCalculatorService = new PositionCalculatorServiceImpl();

  public Vector getDirectionVector(SimObj source, SimObj target) {
    validateSimObj(source);
    validateSimObj(target);

    Vector dir = positionCalculatorService.getVector(source.getPosition(), target.getPosition());
    return dir.getNormalizedVectorToScalar(getBehaviorWeight(source, target));
  }

  public double getBehaviorWeight(SimObj source, SimObj target) {
    validateSimObj(source);
    validateSimObj(target);

    double sum = 0.0d;
    int counter = 0;
    for (String propName : target.getMyProperties().keySet()) {
      sum += source.getOtherPropertyWeight(propName, randomWeightService.getRandomWeight());
      counter++;
    }
    return counter == 0 ? 0 : sum / counter;
  }

  private void validateSimObj(SimObj simObj) {
    if (simObj == null) {
      throw new IllegalArgumentException("simulation object is null");
    }
  }
}
