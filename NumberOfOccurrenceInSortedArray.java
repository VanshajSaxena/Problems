// https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1

/**
 * **Number Of Occurrence In Sorted Array**:
 *
 * Given a sorted array `arr` of size `n` and a number `x`, you need to find the
 * number of occurrences of `x` in `arr`.
 */
public class NumberOfOccurrenceInSortedArray {
  int count(int[] arr, int n, int x) {

    // See FindFirstAndLastPositionOfElementInSortedArray.java
    int length = arr.length;
    int highOne = length - 1, highTwo = length - 1;
    int lowOne = 0, lowTwo = 0;
    int leftMost = -1, rightMost = -1;
    while (highOne >= lowOne || highTwo >= lowTwo) {
      if (highOne >= lowOne) {
        int midOne = lowOne + (highOne - lowOne) / 2;
        if (arr[midOne] == x) {
          leftMost = midOne;
          highOne = midOne - 1;
        } else if (arr[midOne] > x) {
          highOne = midOne - 1;
        } else {
          lowOne = midOne + 1;
        }
      }
      if (highTwo >= lowTwo) {
        int midTwo = lowTwo + (highTwo - lowTwo) / 2;
        if (arr[midTwo] == x) {
          rightMost = midTwo;
          lowTwo = midTwo + 1;
        } else if (arr[midTwo] > x) {
          highTwo = midTwo - 1;
        } else {
          lowTwo = midTwo + 1;
        }
      }
    }

    // Check if the element does not exist.
    if (rightMost == -1 || leftMost == -1) {
      return 0;
    }

    return rightMost - leftMost + 1;
  }
}
