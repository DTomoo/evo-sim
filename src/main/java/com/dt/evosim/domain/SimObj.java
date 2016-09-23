package com.dt.evosim.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dt.physics.common.Position;

public class SimObj implements Serializable {

  private static final long serialVersionUID = 1L;
  // simple properties
  private long id;
  private Position position;
  private AtomicInteger age = new AtomicInteger(0);
  private double speed = 0.0d;
  private int energy = 100;
  private boolean living = true;
  // weighted properties
  private Map<String, Double> myProperties;
  private Map<String, Double> otherPropertyWeights = new HashMap<String, Double>();

  public SimObj(long id) {
    this(id, new HashMap<String, Double>(), new Position(0, 0));
  }

  public SimObj(long id, Map<String, Double> myProperties) {
    this(id, myProperties, new Position(0, 0));
  }

  public SimObj(long id, Map<String, Double> myProperties, Position position) {
    this.id = id;
    this.myProperties = myProperties == null ? new HashMap<String, Double>() : myProperties;
    this.position = position == null ? new Position(0, 0) : position;
  }

  public Double getMyValue(String propertyName) {
    Double val = myProperties.get(propertyName);
    return val == null ? Double.valueOf(0.0d) : val;
  }

  public long getId() {
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

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public boolean isLiving() {
    return living;
  }

  public void die() {
    living = false;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimObj other = (SimObj) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SimObj-" + id;
  }
}
