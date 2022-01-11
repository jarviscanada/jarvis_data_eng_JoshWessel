package ca.jrvs.practice.resources;

import java.util.Collection;

public interface JSet<E> {

  int size();

  boolean contains(Object o);

  boolean add(E e);

  boolean remove(Object o);

  void clear();

}
