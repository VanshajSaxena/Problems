// https://leetcode.com/problems/longest-repeating-character-replacement/description/

import java.util.HashMap;

/**
 * **Longest Repeating Character Replacement**
 *
 * You are given a string `s` and an integer `k`. You can choose any character
 * of the string and change it to any other uppercase English character. You can
 * perform this operation at most `k` times.
 *
 * Return _the length of the longest substring containing the same letter you
 * can get after performing the above operations_.
 */

public class LongestRepeatingCharacterReplacement {

  /**
   * [LeetCode
   * Soluton](https://leetcode.com/problems/longest-repeating-character-replacement/solutions/91271/java-12-lines-o-n-sliding-window-solution-with-explanation)
   */
  public int characterReplacementI(String str, int k) {
    int left = 0, right;
    int maxCount = 0, maxLenght = 0;
    HashMap<Character, Integer> map = new HashMap<>();

    for (right = 0; right < str.length(); right++) {
      Character chr = str.charAt(right);
      map.put(chr, map.getOrDefault(chr, 0) + 1);
      maxCount = Math.max(maxCount, map.get(chr));

      int windowLenght = right - left + 1;

      if (windowLenght - maxCount > k) {
        map.put(str.charAt(left), map.get(str.charAt(left)) - 1);
        left++;
      }

      maxLenght = Math.max(maxLenght, windowLenght);
    }

    return maxLenght;
  }

  public int characterReplacementII(String s, int k) {
    int max = 0, res = 0;
    int[] map = new int[26];
    int i = 0;
    for (int j = 0; j < s.length(); j++) {
      char ch = s.charAt(j);
      max = Math.max(max, ++map[ch - 'A']);
      while (j - i + 1 - max > k) {
        map[s.charAt(i) - 'A']--;
        i++;
      }
      res = Math.max(res, j - i + 1);
    }
    return res;
  }
}
