package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {

  QuickSort quickSort;

  @Before
  public void setUp() throws Exception {
    quickSort = new QuickSort();
  }

  @Test
  public void quickSort() {
    int[] arr = { 0, 8, 2, 10, 11, 5, 4, 7, 12, 1, 15, 9, 3, 28, 21};
    quickSort.quickSort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }
}