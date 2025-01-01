// https://leetcode.com/problems/target-sum/description/

import java.util.Arrays;

/**
 * **Target Sum**:
 *
 * You are given an integer array `nums` and an integer `target`.
 *
 * You want to build an **expression** out of nums by adding one of the symbols
 * `'+'` and `'-'` before each integer in nums and then concatenate all the
 * integers.
 *
 * - For example, if `nums = [2, 1]`, you can add a `'+'` before 2 and a `'-'`
 * before 1 and concatenate them to build the expression `"+2-1"`.
 *
 * Return the number of different **expressions** that you can build, which
 * evaluates to `target`.
 */

public class TargetSum {
  public int findTargetSumWaysI(int[] nums, int target) {
    int ways = 0;
    calculateWaysI(nums, target, 0, 0, ways);
    return ways;
  }

  private void calculateWaysI(int[] nums, int target, int currSum, int currIdx, int totalWays) {
    if (currIdx == nums.length) {
      if (currSum == target) {
        totalWays++;
      }
      return;
    }

    calculateWaysI(nums, target, currSum + nums[currIdx], currIdx + 1, totalWays);

    calculateWaysI(nums, target, currSum - nums[currIdx], currIdx + 1, totalWays);
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/target-sum/editorial/#approach-2-recursion-with-memoization)
   * Recursion with Memoization.
   */
  public int findTargetSumWaysII(int[] nums, int target) {
    int totalSum = Arrays.stream(nums).sum();
    int[][] memo = new int[nums.length][2 * totalSum + 1];

    for (int[] row : memo) {
      Arrays.fill(row, Integer.MIN_VALUE);
    }

    return calculateWaysII(nums, target, 0, 0, memo, totalSum);
  }

  private int calculateWaysII(int[] nums, int target, int currSum, int currIdx, int[][] memo, int totalSum) {
    if (currIdx == nums.length) {
      return target == currSum ? 1 : 0;
    } else {
      if (memo[currIdx][currSum + totalSum] != Integer.MIN_VALUE) {
        return memo[currIdx][currSum + totalSum];
      }

      int add = calculateWaysII(nums, target, currSum + nums[currIdx], currIdx + 1, memo, totalSum);

      int sub = calculateWaysII(nums, target, currSum - nums[currIdx], currIdx + 1, memo, totalSum);

      memo[currIdx][currSum + totalSum] = add + sub;

      return memo[currIdx][currSum + totalSum];
    }
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/target-sum/editorial/#approach-3-2d-dynamic-programming)
   * 2D Dynamic Programming.
   */
  // MARK: Pending.
  public int findTargetSumWaysIII(int[] nums, int target) {
    return target;
  }
}
