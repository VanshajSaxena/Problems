// https://leetcode.com/problems/reverse-words-in-a-string/description/

/**
 * **Reverse Words In A String**:
 *
 * Given an input string `s`, reverse the order of the **words**.
 *
 * A **word** is defined as a sequence of non-space characters. The **words** in
 * `s` will be separated by at least one space.
 *
 * Return _a string of the words in reverse order concatenated by a single
 * space_.
 *
 * **Note** that `s` may contain leading or trailing spaces or multiple spaces
 * between two words. The returned string should only have a single space
 * separating the words. Do not include any extra spaces.
 */

public class ReverseWordsInAString {

  public String reverseWords(String s) {
    StringBuilder ans = new StringBuilder();
    StringBuilder temp = new StringBuilder();
    // This loop identifies words and appends them in `ans`.
    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (c != ' ') {
        temp.append(c);
      } else {
        if (temp.length() > 0) {
          if (ans.length() > 0) { // If this is not the first word identified.
            ans.append(" ");
          }
          ans.append(temp.reverse()); // Reverse word and append to `ans`.
          temp.setLength(0); // Empty `temp`.
        }
      }
    }

    // If `temp` still has something left to append do this now.
    if (temp.length() > 0) {
      if (ans.length() > 0) {
        ans.append(" ");
      }
      ans.append(temp.reverse());
    }

    // Return String for `ans`.
    return ans.toString();
  }
}
