package com.dt.evosim.simulation.selection;

import java.util.stream.Stream;

import com.dt.evosim.domain.SimObj;

public interface SelectionStrategy {

  Stream<SimObj> selectBestObjects(Stream<SimObj> simObjects);
}
