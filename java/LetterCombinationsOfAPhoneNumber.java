// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

import java.util.ArrayList;
import java.util.List;

/**
 * **Letter Combinations Of A Phone Number**:
 *
 * Given a string containing digits from `2-9` inclusive, return all possible
 * letter combinations that the number could represent. Return the answer in
 * **any order**.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 */

public class LetterCombinationsOfAPhoneNumber {
  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) {
      return new ArrayList<>();
    }
    List<String> result = new ArrayList<String>();
    String[] letters = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    recurLetterCombinations(result, digits, new StringBuilder(), 0, letters);
    return result;
  }

  private void recurLetterCombinations(List<String> result, String digits, StringBuilder sb, int idx,
      String[] letters) {

    // Base Case: If the lenght of the digit equals `sb.length`.
    if (sb.length() == digits.length()) {
      result.add(sb.toString());
      return;
    }

    // Current letter string to parse.
    String letterString = letters[digits.charAt(idx) - '2'];

    // Recurse for each character for the letterString, using backtracking.
    for (int i = 0; i < letterString.length(); i++) {
      sb.append(letterString.charAt(i));
      recurLetterCombinations(result, digits, sb, idx + 1, letters);
      sb.deleteCharAt(sb.length() - 1); // Backtracking.
    }
  }
}
