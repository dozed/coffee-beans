package org.dots42.coffeebeans.shared;

import java.util.ArrayList;
import java.util.List;

public class ChangeSupport {

  private static List<ChangeObserver> observers = new ArrayList<ChangeObserver>();
  
  public static void addObserver(ChangeObserver o) {
    observers.add(o);
  }

  private CoffeeBean<?> bean;

  public ChangeSupport(CoffeeBean<?> bean) {
    this.bean = bean;
  }
  
  public void handleSet(String property, Object value, Object old) {
    for (ChangeObserver observer : observers) {
      observer.onSet(bean, property, value, old);
    }
  }
  
  public void onListAdd(String property, Object value, int position) {
    for (ChangeObserver observer : observers) {
      observer.onListAdd(bean, property, value, position);
    }
  }
  
}