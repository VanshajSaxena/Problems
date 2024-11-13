// https://leetcode.com/problems/count-the-number-of-fair-pairs/description/

import java.util.Arrays;

/**
 * **Count The Number Of Fair Pairs**:
 *
 * Given a **0-indexed** integer array `nums` of size `n` and two integers
 * `lower` and `upper,` return _the number of fair pairs_.
 *
 * A pair `(i, j)` is *fair* if:
 *
 * - `0 <= i < j < n`, and
 * - `lower <= nums[i] + nums[j] <= upper`
 */

public class CountTheNumberOfFairPairs {

  /**
   * Approach One:
   * [LeetCode
   * Editorial](https://leetcode.com/problems/count-the-number-of-fair-pairs/editorial/#approach-1-binary-search)
   */
  public long countFairPairs(int[] nums, int lower, int upper) {
    long ans = 0;

    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {
      long low = lowerBound(nums, i + 1, nums.length - 1, lower - nums[i]);

      long high = lowerBound(nums, i + 1, nums.length - 1, upper - nums[i] + 1);

      ans += (high - low);
    }

    return ans;
  }

  private long lowerBound(int[] nums, int low, int high, int element) {

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] >= element) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }

  /**
   * Approach Two:
   * [LeetCode
   * Editorial](https://leetcode.com/problems/count-the-number-of-fair-pairs/editorial/#approach-2-two-pointers)
   */
  public long countFairPairsBetter(int[] nums, int lower, int upper) {
    Arrays.sort(nums);
    return lower_bound(nums, upper + 1) - lower_bound(nums, lower);
  }

  // Calculate the number of pairs with sum less than `value`.
  private long lower_bound(int[] nums, int value) {
    int left = 0, right = nums.length - 1;
    long result = 0;
    while (left < right) {
      int sum = nums[left] + nums[right];
      // If sum is less than value, add the size of window to result and move to the
      // next index.
      if (sum < value) {
        result += (right - left);
        left++;
      } else {
        // Otherwise, shift the right pointer backwards, until we get a valid window.
        right--;
      }
    }

    return result;
  }
}
