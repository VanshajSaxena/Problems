// https://leetcode.com/problems/number-of-ways-to-split-array/description/

/**
 * **Number Of Ways To Split Array**:
 *
 * You are given a **0-indexed** integer array `nums` of length `n`.
 *
 * `nums` contains a **valid split** at index `i` if the following are true:
 *
 * - The sum of the first `i + 1` elements is **greater than or equal to** the
 * sum of the last `n - i - 1` elements.
 * - There is **at least one** element to the right of `i`. That is, `0 <= i < n
 * 1`.
 *
 * Return _the number of **valid splits** in `nums`_.
 */

public class NumberOfWaysToSplitArray {
  public int waysToSplitArrayI(int[] nums) {
    int length = nums.length, validSplits = 0;
    long[] prefixSum = new long[length];
    prefixSum[0] = nums[0];
    for (int i = 1; i < length; i++) {
      prefixSum[i] = prefixSum[i - 1] + nums[i];
    }

    for (int i = 0; i < length - 1; i++) {
      long left = prefixSum[i];
      long right = prefixSum[length - 1] - prefixSum[i];
      if (left >= right) {
        validSplits++;
      }
    }
    return validSplits;
  }

  public int waysToSplitArrayII(int[] nums) {
    long prefixSum = 0;
    long suffixSum = 0;

    for (int num : nums) {
      suffixSum += num;
    }

    int validSplits = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      prefixSum += nums[i];
      suffixSum -= nums[i];

      if (prefixSum >= suffixSum) {
        validSplits++;
      }
    }
    return validSplits;
  }
}
