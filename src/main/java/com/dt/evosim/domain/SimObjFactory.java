package com.dt.evosim.domain;

import java.util.HashMap;
import java.util.Map;

import com.dt.evosim.random.Random;
import com.dt.evosim.simulation.Environment;
import com.dt.physics.common.Position;
import com.dt.physics.common.Vector;

public class SimObjFactory {

  private static final int CODE_OF_CHAR_Z = 90;
  private static final int CODE_OF_CHAR_A = 65;
  //
  private Random rnd = new Random();
  private Environment environment;

  public SimObjFactory(Environment environment) {
    this.environment = environment;
  }

  public SimObj randomObject(int id) {
    Position pos = getRandomPosition();
    Vector dir = getRandomDirection();
    Map<String, Double> myProperties = createRandomFields(1);
    SimObj simObj = new SimObj(id, myProperties, pos, dir);
    return simObj;
  }

  private Map<String, Double> createRandomFields(int numberOfFields) {
    Map<String, Double> hashMap = new HashMap<String, Double>();
    for (int i = 0; i < numberOfFields; i++) {
      hashMap.put(getRandomString(1), Double.valueOf(rnd.nextDouble()));
    }
    return hashMap;
  }

  private String getRandomString(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append((char) (rnd.getRandomIntInClosedRange(CODE_OF_CHAR_A, CODE_OF_CHAR_Z)));
    }
    return sb.toString();
  }

  private Position getRandomPosition() {
    return new Position(rnd.getRandomIntInClosedRange(0, environment.getMaxWidth()),
        rnd.getRandomIntInClosedRange(0, environment.getMaxHeight()));
  }

  private Vector getRandomDirection() {
    return new Vector(rnd.getRandomIntInClosedRange(-5, 5), rnd.getRandomIntInClosedRange(-5, 5));
  }
}
