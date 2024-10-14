// https://leetcode.com/problems/valid-anagram/description/

/**
 * **Valid Anagram**:
 *
 * Given two strings `s` and `t`, return `true` if `t` is an **anagram** of `s`,
 * and `false` otherwise.
 *
 * **Anagram**: An Anagram is a word or phrase formed by rearranging the letters
 * of a different word or phrase, using all the original letters exactly once.
 */

public class ValidAnagram {

  public boolean isAnagram(String s, String t) {

    // Check if string lengths do not match.
    if (s.length() != t.length()) {
      return false;
    }

    // Create an array to store character count for each character in lower english
    // alphabets.
    int[] charCount = new int[26];

    // This increments the element at `s.charAt(i) - 'a'` and decrements the element
    // at `t.charAt(i) - 'a'` both of which represents a relative code point from
    // ASCII, for each character encountered in both the strings.
    for (int i = 0; i < s.length(); i++) {
      charCount[s.charAt(i) - 'a']++;
      charCount[t.charAt(i) - 'a']--;
    }

    // If any `count` is not equal to `0`, this means there is a mismatch in the
    // corresponding frequecies for some character, hence the strings are not
    // anagram.
    for (int count : charCount) {
      if (count != 0) {
        return false;
      }
    }

    // If each count is `0`; return `true`.
    return true;
  }
}
