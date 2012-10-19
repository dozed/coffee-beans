package org.dots42.coffeebeans.shared;

import java.util.List;

public class ChangeWrapper {

  public static <T> T wrap(String propertyName, T o, ChangeSupport changeSupport) {
    if (o instanceof List) {
      return (T) new ManagedList(propertyName, (List) o, changeSupport);
    }
    return o;
  }
  
}
