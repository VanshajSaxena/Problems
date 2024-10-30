// https://leetcode.com/problems/subsets-ii/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * **Subsets II**:
 *
 * Given an integer array `nums` that may contain duplicates, _return all
 * possible subsets (the power set)_.
 *
 * The solution set **must not** contain duplicate subsets. Return the solution
 * in **any order**.
 */

public class SubsetsII {

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Arrays.sort(nums);
    recurSubsetsWithDup(result, nums, new ArrayList<>(), 0);
    return result;
  }

  private void recurSubsetsWithDup(List<List<Integer>> result, int[] nums, ArrayList<Integer> combination, int idx) {
    // This will ensure that all combinations are captured.
    result.add(new ArrayList<>(combination));

    // This is an implicit base case: when `idx == nums.length` the for-loop is
    // skipped, and the function stops to recur itself.
    for (int i = idx; i < nums.length; i++) {
      if (i > idx && nums[i - 1] == nums[i]) {
        continue;
      }

      // Recursive Case
      combination.add(nums[i]);
      recurSubsetsWithDup(result, nums, combination, i + 1);
      // Backtrack
      combination.remove(combination.size() - 1);
    }
  }
}
