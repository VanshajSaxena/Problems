// https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/description/

import java.util.HashMap;

/**
 * **Find Longest Special Substring That Occurs Thrice I**
 *
 * You are given a string `s` that consists of lowercase English letters.
 *
 * A string is called **special** if it is made up of only a single character.
 * For example, the string `"abc"` is not special, whereas the strings `"ddd"`,
 * `"zz"`, and `"f"` are special.
 *
 * Return _the length of the **longest special substring** of `s` which occurs
 * **at least thrice**, or `-1` if no special substring occurs at least thrice_.
 *
 * A **substring** is a contiguous **non-empty sequence** of characters within a
 * string.
 */

public class FindLongestSpecialSubstringThatOccursThriceI {
  public int maximumLengthI(String s) {
    int idx, end = 0, maxLenght = -1;

    for (idx = 0; idx < s.length(); idx++) {
      for (end = idx + 1; end <= s.length(); end++) {
        String sub = s.substring(idx, end);
        int count = 0;
        for (int i = 0; i <= s.length() - sub.length(); i++) {
          if (s.substring(i, i + sub.length()).equals(sub)) {
            count++;
          }
        }
        if (count >= 3) {
          maxLenght = Math.max(maxLenght, sub.length());
        }
      }
    }
    return maxLenght;
  }

  public int maximumLengthII(String s) {
    // Create a HashMap to store the count of all substrings
    HashMap<String, Integer> count = new HashMap<>();

    // Iterate through the string to create substrings
    for (int start = 0; start < s.length(); start++) {
      StringBuilder currString = new StringBuilder();

      for (int end = start; end < s.length(); end++) {
        // If the string is empty, or the current character is equal to
        // the previously added character, then add it to the HashMap.
        // Otherwise, break the iteration.
        if (currString.length() == 0 ||
            currString.charAt(currString.length() - 1) == s.charAt(end)) {
          currString.append(s.charAt(end)); // Use append instead of concatenation
          count.put(
              currString.toString(),
              count.getOrDefault(currString.toString(), 0) + 1);
        } else {
          break;
        }
      }
    }

    // Create a variable ans to store the longest length of substring with
    // frequency at least 3.
    int ans = 0;
    for (String str : count.keySet()) {
      if (count.get(str) >= 3 && str.length() > ans)
        ans = str.length();
    }

    if (ans == 0)
      return -1;
    return ans;
  }
}
