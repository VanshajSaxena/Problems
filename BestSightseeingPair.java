// https://leetcode.com/problems/best-sightseeing-pair/description/

/**
 * **Best Sightseeing Pair**:
 *
 * You are given an integer array `values` where `values[i]` represents the
 * value of the `ith` sightseeing spot. Two sightseeing spots `i` and `j` have a
 * **distance** `j - i` between them.
 *
 * The score of a pair (`i < j`) of sightseeing spots is `values[i] + values[j]
 * + i j`: the sum of the values of the sightseeing spots, minus the distance
 * between them.
 *
 * Return _the maximum score of a pair of sightseeing spots_.
 */

public class BestSightseeingPair {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/best-sightseeing-pair/editorial/#approach-1-dynamic-programming)
   */
  public int maxScoreSightseeingPairI(int[] values) {
    int[] maxLeftScore = new int[values.length];
    int maxScore = 0;
    maxLeftScore[0] = values[0];
    for (int i = 1; i < values.length; i++) {
      maxScore = Math.max(maxScore, maxLeftScore[i - 1] + values[i] - i);

      maxLeftScore[i] = Math.max(maxLeftScore[i - 1], values[i] + i);
    }

    return maxScore;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/best-sightseeing-pair/editorial/#approach-2-space-optimized-dp)
   */
  public int maxScoreSightseeingPairII(int[] values) {
    int maxScore = 0;
    int maxLeftScore = values[0];

    for (int i = 1; i < values.length; i++) {
      maxScore = Math.max(maxScore, maxLeftScore + values[i] - i);

      maxLeftScore = Math.max(maxLeftScore, values[i] + i);
    }

    return maxScore;
  }
}
