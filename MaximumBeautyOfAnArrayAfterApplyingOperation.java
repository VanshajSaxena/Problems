// https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/

import java.util.Arrays;

/**
 * **Maximum Beauty Of An Array After Applying Operation**:
 *
 * You are given a **0-indexed** array `nums` and a **non-negative** integer
 * `k`.
 *
 * In one operation, you can do the following:
 *
 * Choose an index `i` that **hasn't been chosen before** from the range `[0,
 * nums.length - 1]`. Replace `nums[i]` with any integer from the range
 * `[nums[i] - k, nums[i] + k]`.
 *
 * The **beauty** of the array is the length of the longest subsequence
 * consisting of equal elements.
 *
 * Return _the **maximum** possible beauty of the array `nums` after applying
 * the operation any number of times_.
 *
 * **Note** that you can apply the operation to each index **only once**.
 *
 * A **subsequence** of an array is a new array generated from the original
 * array by deleting some elements (possibly none) without changing the order of
 * the remaining elements.
 */

public class MaximumBeautyOfAnArrayAfterApplyingOperation {

  public int maximumBeautyI(int[] nums, int k) {
    Arrays.sort(nums);

    int start = 0, end, maxLength = 0;

    for (end = 0; end < nums.length; end++) {
      while (start < end && nums[end] - nums[start] > 2 * k) {
        start++;
      }
      maxLength = Math.max(maxLength, end - start + 1);
    }

    return maxLength;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/editorial/#approach-1-binary-search)
   */
  public int maximumBeautyII(int[] nums, int k) {
    Arrays.sort(nums);
    int maxLength = 0;
    for (int i = 0; i < nums.length; i++) {
      int upperBound = findUpperbound(nums, nums[i] + 2 * k);

      maxLength = Math.max(maxLength, upperBound - i + 1);
    }

    return maxLength;
  }

  private int findUpperbound(int[] nums, int val) {
    int low = 0, high = nums.length - 1, result = 0;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] <= val) {
        result = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return result;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/editorial/#approach-3-line-sweep)
   * | [Hint: Line
   * Sweep](https://leetcode.com/discuss/study-guide/2166045/line-sweep-algorithms)
   */
  // REVISION: This needs revision.
  public int maximumBeautyIII(int[] nums, int k) {
    // If there's only one element, the maximum beauty is 1
    if (nums.length == 1)
      return 1;

    int maxBeauty = 0;
    int maxValue = 0;

    // Find the maximum value in the array
    for (int num : nums)
      maxValue = Math.max(maxValue, num);

    // Create an array to keep track of the count changes
    int[] count = new int[maxValue + 1];

    // Update the count array for each value's range [val - k, val + k]
    for (int num : nums) {
      count[Math.max(num - k, 0)]++; // Increment at the start of the range
      count[Math.min(num + k + 1, maxValue)]--; // Decrement after the range
    }

    int currentSum = 0; // Tracks the running sum of counts
    // Calculate the prefix sum and find the maximum value
    for (int val : count) {
      currentSum += val;
      maxBeauty = Math.max(maxBeauty, currentSum);
    }

    return maxBeauty;
  }

}
