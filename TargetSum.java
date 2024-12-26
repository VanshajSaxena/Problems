// https://leetcode.com/problems/target-sum/description/

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
    findTarget(nums, target, 0, 0, ways);
    return ways;
  }

  private void findTarget(int[] nums, int target, int currSum, int currIdx, int totalWays) {
    if (currIdx == nums.length) {
      if (currSum == target) {
        totalWays++;
      }
      return;
    }

    findTarget(nums, target, currSum + nums[currIdx], currIdx + 1, totalWays);

    findTarget(nums, target, currSum - nums[currIdx], currIdx + 1, totalWays);
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/target-sum/editorial/?envType=daily-question&envId=2024-12-26#approach-2-recursion-with-memoization)
   */
  // MARK: Pending.
  public int findTargetSumWaysII(int[] nums, int target) {
  }

}
