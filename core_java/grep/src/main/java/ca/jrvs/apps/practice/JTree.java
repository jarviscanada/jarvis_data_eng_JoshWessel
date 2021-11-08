package ca.jrvs.apps.practice;

/**
 * Jarvis Tree (JTree)
 *
 * @param <E> the type of the item
 */
public interface JTree<E> {

  E insert(E object);

  E search(E object);

  E remove(E object);

  E[] preOrder();

  E[] inOrder();

  E[] postOrder();

  int findHeight();

}
