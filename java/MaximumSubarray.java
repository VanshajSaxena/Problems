// https://leetcode.com/problems/maximum-subarray/description/

/**
 * **Maximum Subarray**:
 *
 * Given an integer array `nums`, find the subarray with the largest sum, and
 * _return its sum_.
 */

public class MaximumSubarray {
  // The solution is based on Kadane's Algorithm: If a subarray has a negative
  // sum, continuing to add more elements to it will only decrease the overall
  // sum. Hence, we reset the sum to 0 whenever it becomes negative.
  public int maxSubArray(int[] nums) {
    int maxSum = Integer.MIN_VALUE;
    int cuSum = 0; // cumulative sum
    for (int i = 0; i < nums.length; i++) {
      if (cuSum < 0) {
        cuSum = 0;
      }
      cuSum += nums[i];
      if (cuSum > maxSum) {
        maxSum = cuSum;
      }
    }
    return maxSum;
  }
}
