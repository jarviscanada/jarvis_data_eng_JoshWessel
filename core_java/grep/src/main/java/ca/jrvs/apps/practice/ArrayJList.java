package ca.jrvs.apps.practice;

import java.util.Arrays;

public class ArrayJList<E> implements JList<E> {

  /**
   * Default initial capacity.
   */
  private static final int DEFAULT_CAPACITY = 10;

  /**
   * The array buffer into which the elements of the ArrayList are stored.
   * The capacity of the ArrayList is the length of this array buffer.
   */
  transient Object[] elementData; // Non-private to simplify nested class access

  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  private int size;

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param  initialCapacity  the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity
   *         is negative
   */
  public ArrayJList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public ArrayJList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Appends the specified element to the end of this list (optional
   * operation).
   *
   * Double elementData size if elementData is full.
   */
  @Override
  public boolean add(E e) {

    if (elementData[size] != null) {
      int oldCapacity = size;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      elementData = Arrays.copyOf(elementData, newCapacity);
    }

    elementData[size++] = e;
    return true;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    if (size <= 0)
      return true;
    else
      return false;
  }

  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < size; i++) {
      if (elementData[i].equals(o)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    for (int i = 0; i < size; i++) {
      if (elementData[i].equals(o))
        return true;
    }
    return false;
  }

  @Override
  public E get(int index) {
    if (index > size)
      throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    return (E) elementData[index];
  }

  /**
   * Removes the element at the specified position in this list.
   * Shifts any subsequent elements to the left (subtracts one from their
   * indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
    }
    else {
      E result = (E) elementData[index];
      elementData[index] = null;
      size--;
      return result;
    }
  }

  @Override
  public void clear() {
    Arrays.fill(elementData, null);
  }
}
