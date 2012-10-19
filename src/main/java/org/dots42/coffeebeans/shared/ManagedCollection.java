package org.dots42.coffeebeans.shared;

import java.util.Collection;
import java.util.Iterator;

public class ManagedCollection<T> implements Collection<T> {

  protected ChangeSupport changeSupport;
  protected String propertyName;
  private Collection<T> delegate;
  
  public ManagedCollection(String propertyName, Collection<T> delegate, ChangeSupport changeSupport) {
    this.propertyName = propertyName;
    this.delegate = delegate;
    this.changeSupport = changeSupport;
  }

  public int size() {
    return delegate.size();
  }

  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  public boolean contains(Object o) {
    return delegate.contains(o);
  }

  public Iterator<T> iterator() {
    return delegate.iterator();
  }

  public Object[] toArray() {
    return delegate.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return delegate.toArray(a);
  }

  public boolean add(T e) {
    changeSupport.onListAdd(propertyName, e, size());
    return delegate.add(e);
  }

  public boolean remove(Object o) {
    return delegate.remove(o);
  }

  public boolean containsAll(Collection<?> c) {
    return delegate.containsAll(c);
  }

  public boolean addAll(Collection<? extends T> c) {
    return delegate.addAll(c);
  }

  public boolean removeAll(Collection<?> c) {
    return delegate.removeAll(c);
  }

  public boolean retainAll(Collection<?> c) {
    return delegate.retainAll(c);
  }

  public void clear() {
    delegate.clear();
  }

  public boolean equals(Object o) {
    return delegate.equals(o);
  }

  public int hashCode() {
    return delegate.hashCode();
  }
  
}
