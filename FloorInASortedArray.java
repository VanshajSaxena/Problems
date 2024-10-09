// https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1

/**
 * **Floor In A Sorted Array**:
 *
 * Given a sorted array `arr[]` of size `n` without duplicates, and given a
 * value `x.` Floor of `x` is defined as the largest element `k` in `arr[]` such
 * that `k` is smaller than or equal to `x.` Find the index of k(0-based
 * indexing).
 */

public class FloorInASortedArray {
  static int findFloor(long arr[], int n, long x) {
    int floorIdx = -1;
    int high = n - 1, low = 0;
    while (high >= low) {
      int mid = low + (high - low) / 2;

      // If arr[mid] is less than or equal to x, update floor and search right.
      if (arr[mid] <= x) {
        floorIdx = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return floorIdx;
  }
}
