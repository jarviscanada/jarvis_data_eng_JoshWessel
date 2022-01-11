package ca.jrvs.practice.resources;

public class JTreeSet<E> implements JSet<E> {

  private transient HashJMap<E,Object> map;

  JTreeSet() {
    this.map = new HashJMap<>();
  }

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
    if (map.containsKey(e)) {
      return false;
    }
    map.put(e, new Object());
    return true;
  }

  @Override
  public boolean remove(Object o) {
    //map.remove(o);
    return true;
  }

  @Override
  public void clear() {
    //map.clear();
  }
}
