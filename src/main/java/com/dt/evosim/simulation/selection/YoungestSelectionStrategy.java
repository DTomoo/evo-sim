package com.dt.evosim.simulation.selection;

import java.util.Comparator;
import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;

public class YoungestSelectionStrategy implements SelectionStrategy {

  private int sizeOfSelection;

  public YoungestSelectionStrategy(int sizeOfSelection) {
    this.sizeOfSelection = sizeOfSelection;
  }

  @Override
  public Stream<SimObj> selectBestObjects(Stream<SimObj> simObjects) {
    return simObjects.sorted(Comparator.comparing(SimObj::getAge)).limit(sizeOfSelection);
  }
}
