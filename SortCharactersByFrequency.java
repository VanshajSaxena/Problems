// https://leetcode.com/problems/sort-characters-by-frequency/description/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * **Sort Characters By Frequency**:
 *
 * Given a string `s`, sort it in **decreasing order** based on the
 * **frequency** of the characters. The **frequency** of a character is the
 * number of times it appears in the string.
 *
 * Return _the sorted string. If there are multiple answers, return any of
 * them_.
 */

public class SortCharactersByFrequency {
  public String frequencySort(String s) {
    HashMap<Character, Integer> map = new HashMap<>();

    for (Character c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    List<Character> chars = new ArrayList<>(map.keySet());

    Collections.sort(chars, (a, b) -> map.get(b) - map.get(a));

    StringBuilder result = new StringBuilder();
    for (Character character : chars) {
      int freq = map.get(character);
      for (int i = 0; i < freq; i++) {
        result.append(character);
      }
    }
    return result.toString();
  }
}
