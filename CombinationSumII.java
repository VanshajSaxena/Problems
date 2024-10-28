// https://leetcode.com/problems/combination-sum-ii/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * **Combination Sum II**:
 *
 * Given a collection of candidate numbers (`candidates`) and a target number
 * (`target`), find all unique combinations in `candidates` where the candidate
 * numbers sum to `target`.
 *
 * Each number in candidates may only be used **once** in the combination.
 *
 * **Note**: The solution set must not contain duplicate combinations.
 */

public class CombinationSumII {

  /**
   * Recursive Solution: This approach uses backtracking.
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Arrays.sort(candidates);
    recurCombinationSum(candidates, target, result, 0, new ArrayList<>());
    return result;
  }

  private void recurCombinationSum(int[] candidates, int target, List<List<Integer>> result, int idx,
      ArrayList<Integer> combination) {

    if (target == 0) {
      result.add(new ArrayList<>(combination));
      return;
    }

    // Here the control is give to the for-loop which takes care for any duplicate
    // element. This also handles the backtracking of the recursive calls.
    for (int i = idx; i < candidates.length; i++) {
      // If current element equals to previous element, skip it.
      if (i > idx && candidates[i] == candidates[i - 1]) {
        continue;
      }

      // Since the `candidates` array is sorted, any element found at index `i`
      // greater than `target` means, no need to search forward, every element after
      // it will be greater than `target`.
      if (candidates[i] > target) {
        break; // Prune search.
      }

      // Add the current candidate to the `combination` list
      combination.add(candidates[idx]);
      // Update target by reducing it with the value of current candidate
      recurCombinationSum(candidates, target - candidates[i], result, idx + 1, combination);
      // remove the last candidate for backtracking.
      combination.remove(combination.size() - 1);
    }
  }
}
