package com.dt.evosim.simulation.selection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.dt.evosim.domain.SimObj;

public class YoungestSelectionStrategyTest {

  @Test
  public void test() {
    // GIVEN
    YoungestSelectionStrategy strategy = new YoungestSelectionStrategy(3);
    SimObj simObj1 = createObjWithAge(1, 5);
    SimObj simObj2 = createObjWithAge(2, 2);
    SimObj simObj3 = createObjWithAge(3, 1);
    SimObj simObj4 = createObjWithAge(4, 4);
    SimObj simObj5 = createObjWithAge(5, 3);
    List<SimObj> simObjects = Arrays.asList(simObj1, simObj2, simObj3, simObj4, simObj5);
    // WHEN
    Stream<SimObj> selected = strategy.selectBestObjects(simObjects.stream());
    // THEN
    List<SimObj> selectedList = selected.collect(Collectors.toList());
    Assert.assertEquals(5, simObjects.size());
    Assert.assertEquals(3, selectedList.size());
    Assert.assertFalse(selectedList.contains(simObj1));
    Assert.assertTrue(selectedList.contains(simObj2));
    Assert.assertTrue(selectedList.contains(simObj3));
    Assert.assertFalse(selectedList.contains(simObj4));
    Assert.assertTrue(selectedList.contains(simObj5));
  }

  private SimObj createObjWithAge(int id, int age) {
    SimObj simObj1 = new SimObj(id);
    IntStream.range(0, age).forEach(i -> simObj1.incrementAge());
    return simObj1;
  }
}
