package com.dt.evosim.domain;

import java.util.stream.Collectors;

public class SimObjPrinter {

  private boolean withPosition;
  private boolean withMyProperties;

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
        sb.append(simObj.getPosition());
      }
      if (withMyProperties) {
        sb.append("\nMy properties:(");
        sb.append(getPropertiesText(simObj));
        sb.append(")");
      }
    } else {
      sb.append("<null object>");
    }
    return sb.toString();
  }

  private String getPropertiesText(SimObj simObj) {
    return simObj.getMyProperties().entrySet().stream().map(Object::toString).collect(Collectors.joining(", "));
  }
}
