package ca.jrvs.apps.practice;

import java.util.HashMap;

public class HashJSet<E> implements JSet<E> {

  private transient HashJMap<E, Object> map;

  @Override
  public int size() {
    return map.size;
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
    for (int i = 0; i < map.size; i++) {
      if (map.table[i].equals(o)) {
        map.table[i] = null;
        return true;
      }
    }
    return false;
  }

  @Override
  public void clear() {
    for (int i = 0; i < map.size; i++) {
      map.table[i] = null;
    }
  }
}
