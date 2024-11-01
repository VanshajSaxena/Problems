// https://leetcode.com/problems/combination-sum-iii/description/

import java.util.ArrayList;
import java.util.List;

/**
 * **Combination Sum III**:
 *
 * Find all valid combinations of `k` numbers that sum up to `n` such that the
 * following conditions are true:
 *
 * - Only numbers `1` through `9` are used.
 * - Each number is used **at most once**.
 *
 * Return _a list of all possible valid combinations_. The list must not
 * contain the same combination twice, and the combinations may be returned in
 * any order.
 */

public class CombinationSumIII {

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    recurCombinatioSum3(result, k, n, 1, new ArrayList<>());
    return result;
  }

  private void recurCombinatioSum3(List<List<Integer>> result, int k, int n, int start,
      ArrayList<Integer> combination) {

    // Base Case: The only time we want to capture the combination.
    if (n == 0 && combination.size() == k) {
      result.add(new ArrayList<>(combination));
      return;
    }

    // Another Base Case: Here we ignore the combination and return to avoid further
    // processing.
    if (n < 0 || combination.size() == k) {
      return;
    }

    // This loop takes care of the recursive case and backtracking.
    for (int i = start; i <= 9; i++) {
      combination.add(i);
      recurCombinatioSum3(result, k, n - i, i + 1, combination);
      combination.remove(combination.size() - 1); // Backtracking.
    }
  }
}
