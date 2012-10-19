package org.dots42.coffeebeans.shared;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ManagedList<T> extends ManagedCollection<T> implements List<T> {

  private List<T> delegate;

  public ManagedList(String propertyName, List<T> delegate, ChangeSupport changes) {
    super(propertyName, delegate, changes);
    this.delegate = delegate;
  }

  @Override
  public void add(final int index, T element) {
    delegate.add(index, element);
  }

  @Override
  public T remove(final int index) {
    final T element = get(index);
    delegate.remove(index);
    return element;
  }

  public T set(int index, T element) {
    return delegate.set(index, element);
  }
  public <T> T[] toArray(T[] a) {
    return delegate.toArray(a);
  }
  public boolean addAll(int index, Collection<? extends T> c) {
    return delegate.addAll(index, c);
  }

  public T get(int index) {
    return delegate.get(index);
  }

  public int indexOf(Object o) {
    return delegate.indexOf(o);
  }

  public int lastIndexOf(Object o) {
    return delegate.lastIndexOf(o);
  }

  public ListIterator<T> listIterator() {
    return delegate.listIterator();
  }

  public ListIterator<T> listIterator(int index) {
    return delegate.listIterator(index);
  }

  public List<T> subList(int fromIndex, int toIndex) {
    return delegate.subList(fromIndex, toIndex);
  }

  public T delete(final int index) {
    return remove(index);
  }

  public boolean delete(Object element) {
    int index = indexOf(element);
    if (index == -1)
      return false;
    remove(indexOf(element));
    return true;
  }

  public T removeQuiet(int index) {
    return delegate.remove(index);
  }

  public boolean removeQuiet(T element) {
    return delegate.remove(element);
  }

  public boolean addQuiet(T element) {
    return delegate.add(element);
  }

  public void addQuiet(int index, T element) {
    delegate.add(index, element);
  }

  public void clearQuiet() {
    delegate.clear();
  }

  public void adopt(T element, ManagedList<T> from) {
    adopt(element, from, size());
  }

  public void adopt(final T element, final ManagedList<T> from, final int index) {
    from.removeQuiet(element);
    delegate.add(index, element);
  }

}
