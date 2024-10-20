// https://www.geeksforgeeks.org/problems/second-largest3735/1

/**
 * **Second Largest**:
 *
 * Given an array `arr`, return the **second largest** element from an array. If
 * the
 * second largest element doesn't exist then return `-1`.
 *
 * Note: The second largest element should not be equal to the first largest.
 */

public class SecondLargest {

  public int getSecondLargest(int[] arr) {

    if (arr.length < 2) { // Edge case: only has one element or no element.
      return -1;
    }

    int largest = Integer.MIN_VALUE;
    int secLargest = Integer.MIN_VALUE;

    for (int num : arr) {
      if (num > largest) {
        secLargest = largest;
        largest = num;
      } else if (secLargest < num && num < largest) {
        secLargest = num;
      }
    }

    if (secLargest == Integer.MIN_VALUE) { // Edge case: doesn't have a second largest element.
      return -1;
    }

    return secLargest;
  }
}
