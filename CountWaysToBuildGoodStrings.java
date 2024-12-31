// https://leetcode.com/problems/count-ways-to-build-good-strings/description/

import java.util.Arrays;

/**
 * **Count Ways To Build Good Strings**:
 *
 * Given the integers `zero`, `one`, `low`, and `high`, we can construct a
 * string by starting with an empty string, and then at each step perform either
 * of the following:
 *
 * - Append the character `'0'` `zero` times.
 * - Append the character `'1'` `one` times.
 *
 * This can be performed any number of times.
 *
 * A **good** string is a string constructed by the above process having a
 * **length** between `low` and `high` (**inclusive**).
 *
 * Return _the number of **different** good strings that can be constructed
 * satisfying these properties_. Since the answer can be large, return it
 * **modulo** `10^9 + 7`.
 */

public class CountWaysToBuildGoodStrings {
  final int MOD = 1_000_000_007;
  int[] dp;

  /**
   * [LeetCode
   * Editoral](https://leetcode.com/problems/count-ways-to-build-good-strings/editorial/#approach-1-dynamic-programming-iterative)
   */
  public int countGoodStringsI(int low, int high, int zero, int one) {
    int[] dp = new int[high + 1];
    dp[0] = 1;

    for (int end = 1; end <= high; end++) {
      if (end >= zero) {
        dp[end] += dp[end - zero];
      }
      if (end >= one) {
        dp[end] += dp[end - one];
      }
      dp[end] %= MOD;
    }

    int result = 0;
    for (int i = low; i <= high; i++) {
      result += dp[i];
      result %= MOD;
    }

    return result;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/count-ways-to-build-good-strings/editorial/#approach-2-dynamic-programming-recursive)
   */
  public int countGoodStringsII(int low, int high, int zero, int one) {
    dp = new int[high + 1];
    Arrays.fill(dp, -1);

    dp[0] = 1;

    int answer = 0;

    for (int end = low; end <= high; end++) {
      answer += calculateGoodStrings(end, zero, one);
      answer %= MOD;
    }

    return answer;
  }

  private int calculateGoodStrings(int end, int zero, int one) {
    if (dp[end] != -1) {
      return dp[end];
    }
    int count = 0;
    if (end >= one) {
      count += calculateGoodStrings(end - one, zero, one);
    }
    if (end >= zero) {
      count += calculateGoodStrings(end - zero, zero, one);
    }

    dp[end] = count % MOD;

    return dp[end];
  }
}
