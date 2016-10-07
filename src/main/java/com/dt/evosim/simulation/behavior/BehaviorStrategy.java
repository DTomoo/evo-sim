package com.dt.evosim.simulation.behavior;

import java.util.Collection;

import com.dt.evosim.domain.SimObj;

public interface BehaviorStrategy {

  public void behave(Collection<SimObj> population);
}
