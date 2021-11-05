package ca.jrvs.apps.practice;

import java.util.Map;
import java.util.Set;

/**
 * A simplified map version of java.util.Map
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface JMap<K,V> {

  /**
   * Returns the value to which the specified key is mapped,
   * or {@code null} if this map contains no mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to which the specified key is mapped, or
   *         {@code null} if this map contains no mapping for the key
   * @throws NullPointerException if the specified key is null and this map
   *         does not permit null keys
   * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  V get(Object key);

  /**
   * Returns the number of key-value mappings in this map.  If the
   * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of key-value mappings in this map
   */
  int size();

  /**
   * Returns <tt>true</tt> if this map contains a mapping for the specified
   * key.  More formally, returns <tt>true</tt> if and only if
   * this map contains a mapping for a key <tt>k</tt> such that
   * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
   * at most one such mapping.)
   *
   * @param key key whose presence in this map is to be tested
   * @return <tt>true</tt> if this map contains a mapping for the specified
   *         key
   * @throws NullPointerException if the specified key is null and this map
   *         does not permit null keys
   * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  boolean containsKey(Object key);

  /**
   * Associates the specified value with the specified key in this map
   * (optional operation).  If the map previously contained a mapping for
   * the key, the old value is replaced by the specified value.  (A map
   * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
   * if {@link #containsKey(Object) m.containsKey(k)} would return
   * <tt>true</tt>.)
   *
   * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return the previous value associated with <tt>key</tt>, or
   *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
   *         (A <tt>null</tt> return can also indicate that the map
   *         previously associated <tt>null</tt> with <tt>key</tt>,
   *         if the implementation supports <tt>null</tt> values.)
   * @throws NullPointerException if the specified key or value is null
   *         and this map does not permit null keys or values
   */
  V put(K key, V value);

  /**
   * Returns a {@link Set} view of the mappings contained in this map.
   * The set is backed by the map, so changes to the map are
   * reflected in the set, and vice-versa.
   *
   * @return a set view of the mappings contained in this map
   */
  Set<Map.Entry<K,V>> entrySet();
}
