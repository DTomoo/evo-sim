package com.dt.evosim.domain;

import java.util.Map;

public class SimObjPrinter {

  private boolean withPosition;
  private boolean withMyProperties;

  public SimObjPrinter reset() {
    this.withPosition = false;
    this.withMyProperties = false;
    return this;
  }

  public SimObjPrinter withPosition() {
    this.withPosition = true;
    return this;
  }
  public SimObjPrinter withMyProperties() {
    this.withMyProperties = true;
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
      if (withMyProperties) {
        sb.append("\n My properties:");
        Map<String, Double> myProperties = simObj.getMyProperties();
        for (String key : myProperties.keySet()) {
          sb.append("\n* ");
          sb.append(key);
          sb.append(": ");
          sb.append(myProperties.get(key));
        }
      }

    } else {
      sb.append("<null object>");
    }
    return sb.toString();
  }

}
