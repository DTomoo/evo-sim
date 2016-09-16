package com.dt.evosim.domain;

public class SimObjPrinter {

  private boolean withPosition;

  public SimObjPrinter reset() {
    this.withPosition = false;
    return this;
  }

  public SimObjPrinter withPosition() {
    this.withPosition = true;
    return this;
  }

  public String getRepresentation(SimObj simObj) {
    StringBuilder sb = new StringBuilder();
    if (simObj != null) {
      sb.append("SimObj-");
      sb.append(simObj.getId());
      if (withPosition) {
        sb.append("\n");
        sb.append(simObj.getPosition());
      }
    } else {
      sb.append("<null object>");
    }
    return sb.toString();
  }
}
