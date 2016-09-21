package com.dt.evosim.simulation.selection;

import java.util.Collection;
import java.util.List;

import com.dt.evosim.domain.SimObj;

public interface SelectionStrategy {

  public List<SimObj> selectN(int sizeOfSelection, Collection<SimObj> simObjects);
}
