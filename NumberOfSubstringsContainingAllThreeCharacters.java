// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/

/**
 * **Number Of Substrings Containing All Three Characters**:
 *
 * Given a string `s` consisting only of characters _a_, _b_ and _c_.
 *
 * Return the number of substrings containing **at least** one occurrence of all
 * these characters _a_, _b_ and _c_.
 */

public class NumberOfSubstringsContainingAllThreeCharacters {

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/solutions/516977/java-c-python-easy-and-concise)
   */
  public int numberOfSubstringsI(String s) {
    int count[] = { 0, 0, 0 }, res = 0, i = 0, n = s.length();
    for (int j = 0; j < n; ++j) {
      ++count[s.charAt(j) - 'a'];
      while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
        // System.out.println("j:" + j + " " + count[0] + " " + count[1] + " " +
        // count[2]);
        --count[s.charAt(i++) - 'a'];
      }
      res += i;
    }
    return res;
  }

  public int numberOfSubstringsII(String s) {
    int last[] = { -1, -1, -1 }, res = 0, n = s.length();
    for (int i = 0; i < n; ++i) {
      last[s.charAt(i) - 'a'] = i;
      res += 1 + Math.min(last[0], Math.min(last[1], last[2]));
    }
    return res;
  }
}
