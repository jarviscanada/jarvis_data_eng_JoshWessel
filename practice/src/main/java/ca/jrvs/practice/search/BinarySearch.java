package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class BinarySearch {

  /**
   * find the target index in a sorted array
   *
   * @param arr input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {

    Comparator<E> c = new Comparator<E>() {
      @Override
      public int compare(E o1, E o2) {
        if ((Integer)o1 > (Integer)o2) {
          return -1;
        } else if ((Integer)o1 < (Integer)o2) {
          return 1;
        } else {
          return 0;
        }
      }
    };

    if (c.compare(arr[0], target) < 0 || c.compare(target, arr[arr.length - 1]) < 0) {
      System.out.println("Unable to find target");
      return Optional.empty();
    }
    else {
      int midIndex = Math.round(arr.length / 2);
      if (c.compare(arr[midIndex], target) == 0) {
        System.out.println("Found target: " + arr[midIndex]);
        return (Optional<Integer>) Optional.of(target);
      }
      else if (c.compare(arr[midIndex], target) > 0) {
        E[] newArray = (E[]) Arrays.stream(arr).filter(n -> c.compare(n, arr[midIndex]) < 0).collect(Collectors.toList()).toArray();
        return binarySearchRecursion(newArray, target);
      }
      else if (c.compare(arr[midIndex], target) < 0) {
        E[] newArray = (E[]) Arrays.stream(arr).filter(n -> c.compare(n, arr[midIndex]) > 0).collect(Collectors.toList()).toArray();
        return binarySearchRecursion(newArray, target);
      }
    }

    System.out.println("Unable to find target");
    return Optional.empty();
  }

  /**
   * find the target index in a sorted array
   *
   * @param arr input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {

    Comparator<E> c = new Comparator<E>() {
      @Override
      public int compare(E o1, E o2) {
        if ((Integer)o1 > (Integer)o2) {
          return -1;
        } else if ((Integer)o1 < (Integer)o2) {
          return 1;
        } else {
          return 0;
        }
      }
    };

    if (c.compare(arr[0], target) < 0 || c.compare(target, arr[arr.length - 1]) < 0) {
      System.out.println("Target out of bounds");
      return Optional.empty();
    }

    int high = arr.length - 1;
    int low = 0;
    while (low <= high) {
      int mid = Math.round((low + high) / 2);
      // Check match
      if (c.compare(arr[mid], target) == 0) {
        System.out.println("Found target: " + arr[mid]);
        return (Optional<Integer>) Optional.of(target);
      }
      // If arr[mid] > target
      if (c.compare(arr[mid], target) < 0) {
        high = mid - 1;
      }
      // If arr[mid] < target
      if (c.compare(arr[mid], target) > 0) {
        low = mid + 1;
      }
    }
    System.out.println("Unable to find target");
    return Optional.empty();
  }
}