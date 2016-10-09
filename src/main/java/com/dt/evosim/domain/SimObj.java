package com.dt.evosim.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.dt.physics.common.Position;
import com.dt.physics.common.Vector;

public class SimObj implements Serializable {

  private static final long serialVersionUID = 1L;
  // simple properties
  private long id;
  private Position position;
  private Vector direction;
  private AtomicInteger age = new AtomicInteger(0);
  private double speed = 1.0d;
  private int energy = 100;
  private boolean living = true;
  private int size;
  // weighted properties
  private Map<String, Double> myProperties;
  private Map<String, Double> otherPropertyWeights = new HashMap<String, Double>();

  public SimObj(long id) {
    this(id, new HashMap<String, Double>(), new Position(0, 0), new Vector(0, 0));
  }

  public SimObj(long id, Map<String, Double> myProperties) {
    this(id, myProperties, new Position(0, 0), new Vector(0, 0));
  }

  public SimObj(long id, Map<String, Double> myProperties, Position position, Vector direction) {
    this.id = id;
    this.myProperties = myProperties == null ? new HashMap<String, Double>() : myProperties;
    this.position = position == null ? new Position(0, 0) : position;
    this.direction = direction == null ? new Vector(0, 0) : direction;
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

  public void setSize(int size) {
    this.size = size;
  }

  public int getSize() {
    return size;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Vector getMovingVector() {
    return direction.getNormalizedVectorToScalar(speed).round();
  }

  public Vector getDirection() {
    return direction;
  }

  public void setDirection(Vector direction) {
    this.direction = direction;
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

  public double distTo(SimObj otherObj) {
    return otherObj.getPosition().getVectorTo(this.getPosition()).getScalar() - 1;
  }

  public boolean isCollidingWith(SimObj otherObj) {
    boolean bb = distTo(otherObj) <= this.getSize() + otherObj.getSize();
    return bb;
  }

  public Position getNextPosition() {
    Vector rounded = getDirection().getNormalizedVectorToScalar(speed).round();
    return position.add(rounded);
  }

  public Map<String, Double> getMyProperties() {
    return Collections.unmodifiableMap(myProperties);
  }

  public Map<String, Double> getOtherPropertyWeights() {
    return Collections.unmodifiableMap(otherPropertyWeights);
  }

  public double getOtherPropertyWeight(String propName, double defaultValue) {
    // TODO: concurrency!
    otherPropertyWeights.putIfAbsent(propName, Double.valueOf(defaultValue));
    return otherPropertyWeights.get(propName).doubleValue();
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
