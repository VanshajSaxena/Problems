// https://leetcode.com/problems/max-consecutive-ones-iii/description/

/**
 * **Max Consecutive Ones III**:
 *
 * Given a binary array `nums` and an integer `k`, return _the maximum number of
 * consecutive `1`'s in the array if you can flip at most `k` `0`'s_.
 */

public class MaxConsecutiveOnesIII {
  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/max-consecutive-ones-iii/solutions/247564/java-c-python-sliding-window)
   */
  public int longestOnes(int[] nums, int k) {
    int start = 0, end = 0;
    for (end = 0; end < nums.length; ++end) {
      if (nums[end] == 0)
        k--;
      if (k < 0 && nums[start++] == 0)
        k++;
    }
    return end - start;
  }
}
