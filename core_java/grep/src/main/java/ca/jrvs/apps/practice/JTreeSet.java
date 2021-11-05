package ca.jrvs.apps.practice;

import java.util.NavigableMap;

public class JTreeSet<E> implements JSet<E> {

  private transient NavigableMap<E,Object> map;

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }

  @Override
  public boolean add(E e) {
    map.put(e, new Object());
    return true;
  }

  @Override
  public boolean remove(Object o) {
    map.remove(o);
    return true;
  }

  @Override
  public void clear() {
    map.clear();
  }
}
