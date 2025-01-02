// https://leetcode.com/problems/maximum-score-after-splitting-a-string/description/

/**
 * **Maximum Score After Splitting A String**:
 *
 * Given a string `s` of zeros and ones, _return the maximum score after
 * splitting the string into two **non-empty** substrings_ (i.e. **left**
 * substring and **right** substring).
 *
 * The score after splitting a string is the number of **zeros** in the **left**
 * substring plus the number of **ones** in the **right** substring.
 */

public class MaximumScoreAfterSplittingAString {
  // Novice Approach
  public int maxScoreI(String s) {
    int[] zScores = new int[s.length()];
    int[] oScores = new int[s.length()];

    int zCount = 0, oCount = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt((s.length() - 1) - i) == '1') {
        oCount++;
      }
      oScores[(s.length() - 1) - i] = oCount;

      if (s.charAt(i) == '0') {
        zCount++;
      }
      zScores[i] = zCount;
    }

    int maxScore = 0;
    for (int i = 0; i < s.length() - 1; i++) {
      maxScore = Math.max(maxScore, zScores[i] + oScores[i + 1]);
    }

    return maxScore;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/maximum-score-after-splitting-a-string/editorial/#approach-3-one-pass)
   * Single Pass
   */
  public int maxScoreII(String s) {
    int ones = 0, zeros = 0;
    int best = Integer.MIN_VALUE;

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '0') {
        zeros++;
      } else {
        ones++;
      }

      best = Math.max(best, zeros - ones);
    }

    if (s.charAt(s.length() - 1) == '1') {
      ones++;
    }

    return best + ones;
  }
}
