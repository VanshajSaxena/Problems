// https://leetcode.com/problems/binary-search/description/

/**
 * **Binary Search**:
 *
 * Given an array of integers `nums` which is sorted in ascending order, and an
 * integer `target`, write a function to search `target` in `nums`. If `target`
 * exists, then return its index. Otherwise, return `-1`.
 *
 * You must write an algorithm with `O(log n)` runtime complexity.
 */

public class BinarySearch {
  public int search(int[] nums, int target) {
    int length = nums.length;

    int high = length - 1;
    int low = 0;
    while (high >= low) {
      int mid = low + (high - low) / 2;
      int value = nums[mid];
      if (value == target) {
        return mid;
      } else if (value > target) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }
}
