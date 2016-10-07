package com.dt.evosim.simulation.behavior;

import java.util.Collection;

import com.dt.evosim.domain.SimObj;
import com.dt.physics.common.Vector;

public class CollidingBehaviorStrategy implements BehaviorStrategy {

  @Override
  public void behave(Collection<SimObj> population) {

    population.parallelStream().forEach(
        p1 -> population.parallelStream()
                        .filter(p2 -> p1.getId() < p2.getId())
                        .filter(p1::isCollidingWith)
                        .forEach(p2 -> pushingEachOther(p1, p2)));
  }

  private void pushingEachOther(SimObj o1, SimObj o2) {
    Vector v1 = o2.getPosition().getVectorTo(o1.getPosition());
    Vector v2 = o1.getPosition().getVectorTo(o2.getPosition());
    o1.setDirection(v1);
    o2.setDirection(v2);
  }
}
