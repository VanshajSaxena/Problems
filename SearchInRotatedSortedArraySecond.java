// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/

/**
 * **Sorted In Rotated Sorted Array Second**:
 *
 * There is an integer array `nums` sorted in non-decreasing order (not
 * necessarily with **distinct** values).
 *
 * Before being passed to your function, `nums` is rotated at an unknown pivot
 * index `k` (`0 <= k < nums.length`) such that the resulting array is
 * `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`
 * (**0-indexed**). For example, `[0,1,2,4,4,4,5,6,6,7]` might be rotated at
 * pivot index `5` and become `[4,5,6,6,7,0,1,2,4,4]`.
 *
 * Given the array `nums` **after** the rotation and an integer
 * `target`, return `true` _if `target` is in `nums`, or `false` if it is not in
 * `nums`_.
 *
 * You must decrease the overall operation steps as much as possible.
 */

public class SearchInRotatedSortedArraySecond {

  // See SearchInRotatedSortedArray.java
  public boolean search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return true;
      }
      // This is the check for edge case when: `nums[low] == nums[mid] && nums[mid] ==
      // nums[high]`.
      else if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
        low = low + 1;
        high = high - 1;
        continue;
      } else if (nums[low] <= nums[mid]) {
        if (nums[low] <= target && target <= nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (nums[mid] <= target && target <= nums[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return false;
  }
}
