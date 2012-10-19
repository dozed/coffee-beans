package org.dots42.coffeebeans.shared;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ManagedMap<K, V> extends HashMap<K, V> {

  private ChangeSupport changes;

  private String name;

  public ManagedMap() {

  }

  public ManagedMap(String name, ChangeSupport changes) {
    super();
    this.name = name;
    this.changes = changes;
  }

  @Override
  public void clear() {
    for (K key : keySet()) {
      super.remove(key);
    }
  }

  @Override
  public V put(final K key, V value) {
    V old = null;
    final boolean contained = super.containsKey(key);
    if (contained) {
      old = super.get(key);
    }
    V newVal = super.put(key, value);
    final V finalOld = old;

    return newVal;
  }

  public void removeQuiet(K key) {
    super.remove(key);
  }

  public void putQuiet(K key, V value) {
    super.put(key, value);
  }

  public void putAllQuiet(Map<K, V> map) {
    for (Map.Entry<K, V> e : map.entrySet()) {
      super.put(e.getKey(), e.getValue());
    }
  }

  public void clearQuiet() {
    super.clear();
  }

  @Override
  public V remove(final Object key) {
    if (!super.containsKey(key))
      return null;

    final V old = super.remove(key);

    return old;
  }

}
