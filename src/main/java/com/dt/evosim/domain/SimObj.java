package com.dt.evosim.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dt.physics.common.Position;

public class SimObj {

  private static final AtomicInteger COUNTER = new AtomicInteger(0);
  // simple properties
  private int id = COUNTER.getAndIncrement();
  private Position position;
  private AtomicInteger age = new AtomicInteger(0);
  private double speed = 0.0d;
  // weighted properties
  private Map<String, Double> myProperties = new HashMap<String, Double>();
  private Map<String, Double> otherPropertyWeights = new HashMap<String, Double>();

  public SimObj(Map<String, Double> myProperties) {
    this.myProperties = myProperties;
    this.position = new Position(0, 0);
  }

  public SimObj(Map<String, Double> myProperties, Position position) {
    this.myProperties = myProperties;
    this.position = position;
  }

  public Double getMyValue(String propertyName) {
    Double val = myProperties.get(propertyName);
    return val == null ? Double.valueOf(0.0d) : val;
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

  public Map<String, Double> getMyProperties() {
    return Collections.unmodifiableMap(myProperties);
  }

  public Map<String, Double> getOtherPropertyWeights() {
    return Collections.unmodifiableMap(otherPropertyWeights);
  }

  public double getOtherPropertyWeight(String propName, double defaultValue) {
    // TODO: concurrency!
    otherPropertyWeights.putIfAbsent(propName, defaultValue);
    return otherPropertyWeights.get(propName);
  }
}
