package ca.jrvs.practice.sorting;

public class QuickSort {

  private int partition(int[] arr, int start, int end) {

    int pivot = arr[end];
    int i = start - 1;

    for (int j = start; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    i++;
    int temp = arr[i];
    arr[i] = arr[end];
    arr[end] = temp;

    return i;
  }

  public void quickSort(int[] arr, int start, int end) {
    if (start < end) {
      int pivot = partition(arr, start, end);
      quickSort(arr, start, pivot - 1);
      quickSort(arr, pivot + 1, end);
    }
  }
}