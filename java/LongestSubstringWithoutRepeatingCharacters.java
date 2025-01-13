// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

import java.util.HashMap;

/**
 ** Longest Substring Without Repeating Characters**:
 *
 * Given a string `s`, find the length of the **longest substring** without
 * repeating characters.
 */

public class LongestSubstringWithoutRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    HashMap<Character, Integer> map = new HashMap<>();

    int start = 0, end = 0, maxLen = 0;

    for (Character chr : s.toCharArray()) {

      if (map.containsKey(chr) && map.get(chr) >= start) {
        start = map.get(chr) + 1;
      }

      map.put(chr, end);

      maxLen = Math.max(maxLen, end - start + 1);

      end++;
    }

    return maxLen;
  }
}
