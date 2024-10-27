// https://leetcode.com/problems/combination-sum/description/

import java.util.ArrayList;
import java.util.List;

/**
 * **Combination Sum**:
 *
 *
 * Given an array of **distinct** integers `candidates` and a target integer
 * `target`, return a list of all **_unique combinations_** of `candidates`
 * where the chosen numbers sum to `target`. You may return the combinations in
 * **any order**.
 *
 * The **same** number may be chosen from `candidates` an **unlimited number of
 * times**. Two combinations are unique if the frequency of at least one of the
 * chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that
 * sum up to `target` is less than 150 combinations for the given input.
 */

public class CombinationSum {

  /**
   * Recursive Solution: This apporach uses backtracking.
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    recurCombinationSum(candidates, target, result, 0, new ArrayList<>(), 0);
    return result;
  }

  private void recurCombinationSum(int[] candidates, int target, List<List<Integer>> result, int idx,
      ArrayList<Integer> combination, int sum) {
    // Base Case: if `sum > target` or `i` reaches end of the array.
    if (sum > target || idx == candidates.length) {
      return;
    }
    // Another Base Case: if `sum == target` that is our answer.
    if (sum == target) {
      result.add(new ArrayList<>(combination));
      return;
    }

    // Add the current `candidate` to the `combination` and calculate `sum`.
    combination.add(candidates[idx]);
    sum += candidates[idx];
    // Don't move past the `idx` as we need to process same element more than once.
    recurCombinationSum(candidates, target, result, idx, combination, sum);

    // Remove the current `candidate` from the `combination` and calculate `sum`.
    combination.remove(combination.size() - 1);
    sum -= candidates[idx];
    // Move past the `idx` if no above conditions are met.
    recurCombinationSum(candidates, target, result, idx + 1, combination, sum);

  }

  /**
   * Iterative Solution: AI generated.
   *
   * Highly in-efficient code:
   * https://leetcode.com/problems/combination-sum/submissions/1435553257/
   */
  public List<List<Integer>> iterCombinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    findCombinations(candidates, target, result, 0, new ArrayList<>());
    return result;
  }

  private void findCombinations(int[] candidates, int target, List<List<Integer>> result,
      int index, ArrayList<Integer> combination) {
    // Base case: sum is equal to target
    int currentSum = combination.stream().mapToInt(Integer::intValue).sum();
    if (currentSum == target) {
      result.add(new ArrayList<>(combination));
      return;
    }

    // If current sum exceeds target, no need to proceed
    if (currentSum > target) {
      return;
    }

    // Explore further combinations
    for (int i = index; i < candidates.length; i++) {
      combination.add(candidates[i]);
      findCombinations(candidates, target, result, i, combination); // not incrementing index to allow repeated use
      combination.remove(combination.size() - 1); // backtrack
    }
  }
}
