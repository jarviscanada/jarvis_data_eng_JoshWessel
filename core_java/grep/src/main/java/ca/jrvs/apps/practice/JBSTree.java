package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.Objects;

public class JBSTree<E extends Comparable<E>> implements JTree<E> {

  private Node<E> root;
  private int size = 0;

  // Array Class adapted from:
  // https://stackoverflow.com/questions/34827626/cannot-be-cast-to-ljava-lang-comparable
  // https://www.techiedelight.com/creating-generic-array-java/
  public class Array<E> {
    private final E[] arr;
    public final int length;

    public Array() {
      arr = (E[]) new Comparable[size];
      this.length = size;
    }

    E get(int index) {
      return (E) arr[index];
    }

    void set(int index, E e) {
      arr[index] = e;
    }

    void empty() {
      int index = 0;
      for (Object object : arr) {
        arr[index] = null;
        index++;
      }
    }
    
    public E[] toArray() {
      return arr;
    }

    @Override
    public String toString() {
      return Arrays.toString(arr);
    }
  }

  @Override
  public E insert(E object) {
    root = insertRecursive(root, null, object);
    size++;
    return object;
  }

  // Code adapted from: https://github.com/jnethery/CS2/blob/master/Generic%20Binary%20Search%20Tree/GenericBST.java
  public Node<E> insertRecursive(Node<E> root, Node<E> parent, E object) {

    if (root == null) {
      return new Node<E>(object, parent);
    }
    else if (object.compareTo(root.getValue()) < 0) {
      parent = root;
      root.setLeft(insertRecursive(root.getLeft(), parent, object));
    }
    else if (object.compareTo(root.getValue()) > 0) {
      parent = root;
      root.setRight(insertRecursive(root.getRight(), parent, object));
    }
    return root;
  }

  @Override
  public E search(E object) {
    return searchRecursive(root, object);
  }

  public E searchRecursive(Node<E> root, E object) {

    if (root == null) {
      return null;
    }
    else if (object.compareTo(root.getValue()) < 0) {
      return searchRecursive(root.getLeft(), object);
    }
    else if (object.compareTo(root.getValue()) > 0) {
      return searchRecursive(root.getRight(), object);
    }
    else {
      return root.getValue();
    }
  }

  @Override
  public E remove(E object) {
    root = removeRecursive(root, object);
    return root.getValue();
  }

  private Node<E> removeRecursive(Node<E> root, E object) {
    if (root == null) {
      return null;
    }
    else if (object.compareTo(root.getValue()) < 0) {
      return removeRecursive(root.getLeft(), object);
    }
    else if (object.compareTo(root.getValue()) > 0) {
      return removeRecursive(root.getRight(), object);
    }
    else {
      return null;
    }
  }

  int indexPre = 0;

  @Override
  public E[] preOrder() {
    Array<E> arr = new Array<>();
    preOrder(root, arr);
    return arr.toArray();
  }

  public void preOrder(Node<E> root, Array<E> arr) {
    if (root != null) {

      arr.set(indexPre, root.getValue());
      System.out.println("Pre Order " + indexPre + ": " + root.getValue());
      indexPre++;

      preOrder(root.getLeft(), arr);
      preOrder(root.getRight(), arr);
    }
  }

  int indexIn = 0;

  @Override
  public E[] inOrder() {
    Array<E> arr =  new Array<>();
    inOrder(root, arr);
    return arr.toArray();
  }

  public void inOrder(Node<E> root, Array<E> arr) {
    if (root != null) {
      inOrder(root.getLeft(), arr);

      arr.set(indexIn, root.getValue());
      System.out.println("In Order " + indexIn + ": " + root.getValue());
      indexIn++;

      inOrder(root.getRight(), arr);
    }
  }

  int indexPost = 0;

  @Override
  public E[] postOrder() {
    Array<E> arr =  new Array<>();
    postOrder(root, arr);
    return arr.toArray();
  }

  public void postOrder(Node<E> root, Array<E> arr) {
    if (root != null) {
      postOrder(root.getLeft(), arr);
      postOrder(root.getRight(), arr);

      arr.set(indexPost, root.getValue());
      System.out.println("Post Order " + indexPost + ": " + root.getValue());
      indexPost++;
    }
  }

  @Override
  public int findHeight() {
    return findHeight(root) + 1;
  }

  public int findHeight(Node<E> root) {
    if (root == null)
      return -1;

    int leftHeight = findHeight(root.getLeft());
    int rightHeight = findHeight(root.getRight());

    if (leftHeight > rightHeight)
      return leftHeight + 1;
    else
      return rightHeight + 1;
  } // Source: https://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree

  static final class Node<E> {
    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof Node))
        return false;
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()) &&
          Objects.equals(getLeft(), node.getLeft()) &&
          Objects.equals(getRight(), node.getRight()) &&
          getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }

  public static void main(String[] args) {

    JBSTree<Integer> myTree = new JBSTree<Integer>();

    myTree.insert(2);
    myTree.insert(5);
    myTree.insert(3);
    myTree.insert(8);

    myTree.findHeight();
    myTree.search(3);

    myTree.preOrder();

    myTree.inOrder();

    myTree.postOrder();

  }
}
