package com.dt.evosim.simulation;

import java.util.function.Function;

import com.dt.evosim.domain.SimObj;
import com.dt.physics.common.Position;

public class Environment {

  private final int minWidth;
  private final int minHeight;
  private final int maxWidth;
  private final int maxHeight;

  public Environment(int maxWidth, int maxHeight) {
    this.minWidth = 0;
    this.minHeight = 0;
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
  }

  public boolean isOutOfWidth(Position position, int size) {
    return position.getX() - size < minWidth || position.getX() + size + 1 > maxWidth;
  }

  public boolean isOutOfHeight(Position position, int size) {
    return position.getY() - size < minHeight || position.getY() + size + 1 > maxHeight;
  }

  public boolean isOnWidthEdge(SimObj simObj, Function<SimObj, Position> positionSupplier) {
    int speed = (int) simObj.getSpeed();
    Position pos = positionSupplier.apply(simObj);
    boolean r = pos.getX() - simObj.getSize() <= minWidth || maxWidth <= pos.getX() + simObj.getSize();
    return r;
  }

  public boolean isOnHeightEdge(SimObj simObj, Function<SimObj, Position> positionSupplier) {
    int speed = (int) simObj.getSpeed();
    Position pos = positionSupplier.apply(simObj);
    boolean r = pos.getY() - simObj.getSize() <= minHeight || maxHeight <= pos.getY() + simObj.getSize();
    return r;
  }

  public Position limitedPosition(Position position, int objSize) {
    return new Position(limitedWidth(position.getX(), objSize), limitedHeight(position.getY(), objSize));
  }

  public Position limitedPosition(SimObj simObj) {
    return limitedPosition(simObj.getPosition(), simObj.getSize());
  }

  public int limitedWidth(int width, int objSize) {
    return between(width, minWidth + objSize, this.maxWidth - objSize);
  }

  public int limitedHeight(int height, int objSize) {
    return between(height, minHeight + objSize, this.maxHeight - objSize);
  }

  private static int between(int number, int min, int max) {
    return Math.min(max, Math.max(min, number));
  }

  public int getMaxWidth() {
    return maxWidth;
  }

  public int getMaxHeight() {
    return maxHeight;
  }

  public int getMinWidth() {
    return minWidth;
  }

  public int getMinHeight() {
    return minHeight;
  }
}
