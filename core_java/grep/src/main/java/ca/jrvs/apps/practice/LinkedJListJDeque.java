package ca.jrvs.apps.practice;

import java.util.NoSuchElementException;
import java.util.AbstractSequentialList;
import ca.jrvs.apps.practice.LinkedJList.Node;
import java.util.Collection;

public class LinkedJListJDeque<E> implements JDeque<E> {

  private static class Node<E> {
    E item;

    Node<E> next;
    Node<E> prev;

    public Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.prev = prev;
      this.next = next;
    }
  }

  transient int size = 0;

  transient Node<E> first;
  transient Node<E> last;



  /**
   * This is equivalent enqueue operation in Queue ADT
   * <p>
   * Inserts the specified element into the queue represented by this deque (in other words, at the
   * tail of this deque) if it is possible to do so immediately without violating capacity
   * restrictions, returning {@code true} upon success and throwing an {@code IllegalStateException}
   * if no space is currently available.
   *
   * @param e the element to add
   * @return {@code true} (as specified by {@link Collection#add})
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public boolean add(E e) {
    if (e == null)
      throw new NullPointerException();

    final Node<E> newNode = new Node(null, e, last);
    last = newNode;
    size++;

    return true;
  }

  /**
   * This is equivalent dequeue operation in Queue ADT
   * <p>
   * Retrieves and removes the head of the queue represented by this deque (in other words, the
   * first element of this deque).
   *
   * @return the head of the queue represented by this deque
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E remove() {
    if (first == null)
      throw new NoSuchElementException();

    E item = first.item;

    first = first.prev;

    return item;
  }

  /**
   * Pops an element from the stack represented by this deque. In other words, removes and returns
   * the first element of this deque.
   *
   * @return the element at the front of this deque (which is the top of the stack represented by
   * this deque)
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E pop() {
    return remove();
  }

  /**
   * Pushes an element onto the stack represented by this deque (in other words, at the head of this
   * deque) if it is possible to do so immediately without violating capacity restrictions
   *
   * @param e the element to push
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public void push(E e) {
    Node<E> newNode = new Node(first, e, null);
    first.next = newNode;
    first = newNode;
  }

  /**
   * Retrieves, but does not remove, the head of the queue represented by this deque (in other
   * words, the first element of this deque), or returns {@code null} if this deque is empty.
   *
   * @return the head of the queue represented by this deque, or {@code null} if this deque is empty
   */
  @Override
  public E peek() {
    return first.item;
  }
}
