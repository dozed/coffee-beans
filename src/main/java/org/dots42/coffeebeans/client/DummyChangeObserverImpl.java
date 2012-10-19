package org.dots42.coffeebeans.client;

import org.dots42.coffeebeans.shared.ChangeObserver;
import org.dots42.coffeebeans.shared.CoffeeBean;

public class DummyChangeObserverImpl implements ChangeObserver {

  @Override
  public void onSet(CoffeeBean<?> bean, String property, Object value, Object old) {
    System.out.println("set: " + property + " value: " + value);
  }

  @Override
  public void onListAdd(CoffeeBean<?> bean, String property, Object value,
      int position) {
    System.out.println("add: " + property + " value: " + value + " at: " + position);
  }

}
