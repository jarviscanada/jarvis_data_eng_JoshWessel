package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {

  BinarySearch binarySearch;

  @Before
  public void setUp() throws Exception {
    binarySearch = new BinarySearch();
  }

  @Test
  public void binarySearchRecursion() {

    Integer[] arr = { 0, 1, 4, 5, 7, 9 };
    binarySearch.binarySearchRecursion(arr, 2);
    binarySearch.binarySearchRecursion(arr, 5);
    binarySearch.binarySearchRecursion(arr, 9);
    binarySearch.binarySearchRecursion(arr, 10);
    binarySearch.binarySearchRecursion(arr, 20);
  }

  @Test
  public void binarySearchIteration() {

    Integer[] arr = { 0, 1, 4, 5, 7, 9 };
    binarySearch.binarySearchIteration(arr, 2);
    binarySearch.binarySearchIteration(arr, 5);
    binarySearch.binarySearchIteration(arr, 9);
    binarySearch.binarySearchIteration(arr, 10);
    binarySearch.binarySearchIteration(arr, 20);
  }
}