// https://leetcode.com/problems/two-sum/description/

import java.util.HashMap;

/**
 * **Two Sum Problem**:
 *
 * Given an array of integers `nums` and an integer target, return indices of
 * the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * You can return the answer in any order.
 */

public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[] { i, map.get(target - nums[i]) };
      }
      map.put(nums[i], i);
    }
    // This will never be reached, the problem guarantees a solution.
    return null;
  }
}
