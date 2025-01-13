// https://leetcode.com/problems/isomorphic-strings/description/

import java.util.HashMap;

/**
 * **Isomorphic Strings**:
 *
 * Given two strings `s` and `t`, _determine if they are isomorphic_.
 *
 * Two strings `s` and `t` are isomorphic if the characters in `s` can be
 * replaced to get `t`.
 *
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character, but a character may map to itself.
 */

public class IsomorphicStrings {

  public boolean isIsomorphic(String s, String t) {
    // Maintaining two HashMaps to contain mapping from `s` to `t` and `t` to `s`.
    HashMap<Character, Character> mapST = new HashMap<>();
    HashMap<Character, Character> mapTS = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char sChar = s.charAt(i);
      char tChar = t.charAt(i);

      // If already has a mapping in `mapST`.
      if (mapST.containsKey(sChar)) {
        if (mapST.get(sChar) != tChar) { // Is the mapping earlier made consistent? If not consistent return `false`.
          return false;
        }
      } else { // If mapping not already made, make it now.
        mapST.put(sChar, tChar);
      }

      // If already has a mapping in `mapTS`.
      if (mapTS.containsKey(tChar)) {
        if (mapTS.get(tChar) != sChar) { // Is the mapping earlier made consistent? If not consistent return `false`.
          return false;
        }
      } else { // If mapping not already made, make it now.
        mapTS.put(tChar, sChar);
      }
    }

    // If reached end of the string without returning `false`, the strings are
    // isomorphic, hence return `true`.
    return true;
  }
}
