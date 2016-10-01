package com.dt.evosim.simulation;

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

  public boolean isWidthLimit(Position pos) {
    return pos.getX() <= minWidth || maxWidth <= pos.getX();
  }

  public boolean isHeightLimit(Position pos) {
    return pos.getY() <= minHeight || maxHeight <= pos.getY();
  }

  public Position limitedPosition(Position pos) {
    return new Position(limitedWidth(pos.getX()), limitedHeight(pos.getY()));
  }

  public int limitedWidth(int width) {
    return between(width, minWidth, this.maxWidth);
  }

  public int limitedHeight(int height) {
    return between(height, minHeight, this.maxHeight);
  }

  private static int between(int number, int min, int max) {
    return Math.min(max, Math.max(0, number));
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
