package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void mergeSort() {
    int[] arr = { 0, 8, 2, 10, 11, 5, 4, 7, 12, 1, 15, 9, 3, 28, 21};
    MergeSort.mergeSort(arr, arr.length);
    System.out.println(Arrays.toString(arr));
  }
}