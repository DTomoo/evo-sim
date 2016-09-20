package com.dt.evosim.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.dt.physics.common.Position;

public class SimObjFactory {

  private static final int CODE_OF_CHAR_Z = 90;
  private static final int CODE_OF_CHAR_A = 65;
  //
  private Random rnd = new Random();

  public SimObj randomObject(int id) {
    Position pos = getRandomPosition();
    Map<String, Double> myProperties = createRandomFields(1);
    SimObj simObj = new SimObj(id, myProperties, pos);
    return simObj;
  }

  private Map<String, Double> createRandomFields(int numberOfFields) {
    Map<String, Double> hashMap = new HashMap<String, Double>();
    for (int i = 0; i < numberOfFields; i++) {
      hashMap.put(getRandomString(1), rnd.nextDouble());
    }
    return hashMap;
  }

  private String getRandomString(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append((char) (getRandomInt(CODE_OF_CHAR_A, CODE_OF_CHAR_Z)));
    }
    return sb.toString();
  }

  private int getRandomInt(int min, int max) {
    return min + rnd.nextInt(max - min);
  }

  private Position getRandomPosition() {
    return new Position(rnd.nextInt(10) - 10, rnd.nextInt(10) - 10);
  }
}
