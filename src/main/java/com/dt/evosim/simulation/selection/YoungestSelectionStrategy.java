package com.dt.evosim.simulation.selection;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.dt.evosim.domain.SimObj;

public class YoungestSelectionStrategy implements SelectionStrategy {

  @Override
  public List<SimObj> selectN(int sizeOfSelection, Collection<SimObj> simObjects) {
    return simObjects.stream().sorted(Comparator.comparing(SimObj::getAge)).limit(sizeOfSelection)
        .collect(Collectors.toList());
  }
}
