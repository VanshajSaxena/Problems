// https://leetcode.com/problems/binary-subarrays-with-sum/description/

import java.util.HashMap;

/**
 * **Binary Subarrays With Sum**
 *
 * Given a binary array `nums` and an integer `goal`, return _the number of
 * non-empty **subarrays** with a sum `goal`_.
 *
 * A **subarray** is a contiguous part of the array.
 */

public class BinarySubarraysWithSum {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/binary-subarrays-with-sum/editorial/#approach-1-prefix-sum)
   */
  public int numSubarraysWithSum(int[] nums, int goal) {
    int totalCount = 0;
    int currentSum = 0;

    // {prefix: number of occurrence}
    HashMap<Integer, Integer> freq = new HashMap<>();

    for (int num : nums) {
      currentSum += num;

      if (currentSum == goal) {
        totalCount++;
      }

      // Check the difference between `currentSum` and `goal` exists in
      // the `freq` map. If it does, it means there are subarrays ending at the
      // current element that sum to the goal, and the count of such subarrays is
      // added to `totalCount`.
      if (freq.containsKey(currentSum - goal)) {
        totalCount += freq.get(currentSum - goal);
      }

      freq.put(currentSum, freq.getOrDefault(currentSum, 0) + 1);
    }

    return totalCount;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/binary-subarrays-with-sum/editorial/#approach-2-sliding-window)
   */
  public int numSubarraysWithSumII(int[] nums, int goal) {
    return slidingWindowAtMost(nums, goal) - slidingWindowAtMost(nums, goal - 1);
  }

  private int slidingWindowAtMost(int[] nums, int goal) {
    int start = 0, currentSum = 0, totalCount = 0;

    for (int end = 0; end < nums.length; end++) {
      currentSum += nums[end];

      while (start <= end && currentSum > goal) {
        currentSum -= nums[start++];
      }

      totalCount += (end - start + 1);
    }

    return totalCount;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/binary-subarrays-with-sum/editorial/#approach-3-sliding-window-in-one-pass)
   */
  public int numSubarraysWithSumIII(int[] nums, int goal) {
    int start = 0, prefixZeros = 0, currentSum = 0, totalCount = 0;

    for (int end = 0; end < nums.length; end++) {
      currentSum += nums[end];

      while (start < end && (currentSum > goal || nums[start] == 0)) {
        if (nums[start] == 1) {
          prefixZeros = 0;
        } else {
          prefixZeros++;
        }

        currentSum -= nums[start++];
      }

      if (currentSum == goal) {
        totalCount += 1 + (prefixZeros);
      }
    }

    return totalCount;
  }
}
