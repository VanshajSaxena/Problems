// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/

/**
 * **Find Minimum In Rotated Sorted Array**:
 *
 * Suppose an array of length `n` sorted in ascending order is rotated between
 * `1` and `n` times. For example, the array `nums = [0,1,2,4,5,6,7]` might
 * become:
 *
 * - `[4,5,6,7,0,1,2]` if it was rotated `4` times.
 * - `[0,1,2,4,5,6,7]` if it was rotated `7` times.
 *
 * Notice that rotating an array `[a[0], a[1], a[2], ..., a[n-1]]` 1 time
 * results in the array `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]`.
 *
 * Given the sorted rotated array `nums` of unique elements, return _the minimum
 * element of this array_.
 *
 * You must write an algorithm that runs in `O(log n)` time.
 */

public class FindMinimumInRotatedSortedArray {

  // The solution employs binary search to reduce the search space at each step.
  // It identifies a sorted segment, then updates `lowest` with the value at the
  // pointer that indicates the minimum value within that sorted segment.
  public int findMin(int[] nums) {
    int low = 0, high = nums.length - 1;
    int lowest = Integer.MAX_VALUE;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[low] <= nums[mid]) {
        if (nums[low] < lowest) {
          lowest = nums[low];
        }
        low = mid + 1;
      } else if (nums[mid] <= nums[high]) {
        if (nums[mid] < lowest) {
          lowest = nums[mid];
        }
        high = mid - 1;
      }
    }
    return lowest;
  }
}
