package com.dt.evosim.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Population {

  private HashMap<Long, SimObj> entitiesById = new HashMap<>();

  public void add(SimObj simObj) {
    if (simObj != null && !entitiesById.containsValue(simObj)) {
      entitiesById.put(Long.valueOf(simObj.getId()), simObj);
    } else {
      throw new RuntimeException("adding new object to population is not possible");
    }
  }

  public SimObj get(long id) {
    return entitiesById.get(Long.valueOf(id));
  }

  public boolean contains(long id) {
    return entitiesById.containsKey(Long.valueOf(id));
  }

  public Collection<SimObj> getEntities() {
    return entitiesById.values();
  }

  public Set<Entry<Long, SimObj>> getEntries() {
    return entitiesById.entrySet();
  }

  @Override
  public String toString() {
    SimObjPrinter simObjPrinter = new SimObjPrinter().withMyProperties().withPosition();
    StringJoiner sj = new StringJoiner("\n");
    sj.add("Population:");
    sj.add(getEntities().stream().map(simObjPrinter::getRepresentation).collect(Collectors.joining("\n")));
    return sj.toString();
  }
}
