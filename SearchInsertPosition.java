// https://leetcode.com/problems/search-insert-position/description/

/**
 * **Search Insert Position**:
 *
 * Given a sorted array of distinct integers and a target value, return the
 * index if the target is found. If not, return the index where it would be if
 * it were inserted in order.
 *
 * You must write an algorithm with `O(log n)` runtime complexity.
 */

public class SearchInsertPosition {
  public int searchInsert(int[] nums, int target) {
    int high = nums.length - 1, low = 0;
    int mid = -1;
    while (high >= low) {
      mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid; // return mid if `target` is found.
      } else if (target < nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    // This will point to the smallest number greater than the `target`. This is
    // where the target should be placed if order is to be maintained.
    return low;

  }
}
