package com.dt.evosim.domain;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {

  private HashMap<Long, SimObj> populationById = new HashMap<>();

  public void add(SimObj simObj) {
    if (simObj != null && !populationById.containsValue(simObj)) {
      populationById.put(Long.valueOf(simObj.getId()), simObj);
    } else {
      throw new RuntimeException("adding new object to population is not possible");
    }
  }

  public SimObj get(long id) {
    return populationById.get(Long.valueOf(id));
  }

  public boolean contains(long id) {
    return populationById.containsKey(Long.valueOf(id));
  }

  public Stream<SimObj> getPopulation() {
    return populationById.values().stream();
  }

  public Stream<Entry<Long, SimObj>> getEntries() {
    return populationById.entrySet().stream();
  }

  @Override
  public String toString() {
    SimObjPrinter simObjPrinter = new SimObjPrinter().withMyProperties().withPosition();
    StringJoiner sj = new StringJoiner("\n");
    sj.add("Population:");
    sj.add(getPopulation().map(simObjPrinter::getRepresentation).collect(Collectors.joining("\n")));
    return sj.toString();
  }
}
