// https://leetcode.com/problems/subsets/description/

import java.util.ArrayList;
import java.util.List;

/**
 * **Subsets**:
 *
 * Given an integer array `nums` of unique elements, _return all possible
 * subsets (the power set)_.
 *
 * The solution set **must not** contain duplicate subsets. Return the solution
 * in **any order**.
 */

public class Subsets {

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    recurSubsets(result, nums, new ArrayList<>(), 0);
    return result;
  }

  private void recurSubsets(List<List<Integer>> result, int[] nums, ArrayList<Integer> subset, int n) {
    if (n == nums.length) {
      // This creates a new ArrayList at every base case to avoid referential issues.
      result.add(new ArrayList<>(subset));
      return;
    }

    subset.add(nums[n]);
    recurSubsets(result, nums, subset, n + 1);

    subset.remove(subset.size() - 1);
    recurSubsets(result, nums, subset, n + 1);
  }

}
