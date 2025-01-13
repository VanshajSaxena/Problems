// https://takeuforward.org/arrays/implement-upper-bound/

/**
 * **Ceil In A Sorted Array**:
 *
 * Given a sorted array `arr[]` of size `n` without duplicates, and a value `x`,
 * the ceil of `x` is defined as the smallest element `k` in `arr[]` such that
 * `k` >= `x.` Find the index of k (0-based indexing).
 */

public class CeilInASortedArray {
  public int findCeil(int[] arr, int target) {
    int high = arr.length - 1;
    int low = 0;
    int ceilIdx = -1;
    while (high >= low) {
      int mid = low + (high - low) / 2;

      if (arr[mid] >= target) {
        ceilIdx = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ceilIdx;
  }
}
