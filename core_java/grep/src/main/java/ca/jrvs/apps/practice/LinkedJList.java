package ca.jrvs.apps.practice;

import java.util.AbstractSequentialList;
import java.util.Collection;

public class LinkedJList<E> implements JList<E> {

  static class Node<E> {
    E item;

    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.prev = prev;
      this.next = next;
    }
  }

  transient int size = 0;

  transient Node<E> first;
  transient Node<E> last;

  @Override
  public boolean add(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
      first = newNode;
    else
      last.next = newNode;
    size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] result = new Object[size];

    int i = 0;
    for (Node<E> x = first; x != null; x = x.next)
      result[i++] = x.item;
    return result;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
      for (Node<E> x = first; x != null; x = x.next) {
        if (x.item == null)
          return index;
        index++;
      }
    } else {
        for (Node<E> x = first; x != null; x = x.next) {
          if (o.equals(x.item))
            return index;
          index++;
        }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public E get(int index) {
    return node(index).item;
  }

  E unlink(Node<E> node) {
    final E element = node.item;
    final Node<E> next = node.next;
    final Node<E> prev = node.prev;

    if (prev == null)
      first = next;
    else {
      prev.next = next;
      node.prev = null;
    }

    if (next == null)
      last = prev;
    else {
      next.prev = prev;
      node.next = null;
    }

    size--;
    node.item = null;
    return element;
  }

  Node<E> node(int index) {
    Node<E> x = first;
    for (int i = 0; i < index; i++)
      x = x.next;
    return x;
  }

  @Override
  public E remove(int index) {
    return unlink(node(index));
  }

  @Override
  public void clear() {
    for (Node<E> x = first; x != null;) {
      Node<E> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = null;
    last = null;
    size = 0;
  }
}