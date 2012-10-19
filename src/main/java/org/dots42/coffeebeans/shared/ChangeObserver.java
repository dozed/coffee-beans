package org.dots42.coffeebeans.shared;

public interface ChangeObserver {

  public void onSet(CoffeeBean<?> bean, String property, Object value, Object old);

  public void onListAdd(CoffeeBean<?> bean, String property, Object value, int position);
  
}
