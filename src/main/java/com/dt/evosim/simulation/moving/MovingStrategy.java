package com.dt.evosim.simulation.moving;

import java.util.function.Consumer;

import com.dt.evosim.domain.SimObj;

public interface MovingStrategy extends Consumer<SimObj> {

  void move(SimObj simObj);
}
