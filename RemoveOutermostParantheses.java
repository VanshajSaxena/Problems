// https://leetcode.com/problems/remove-outermost-parentheses/description/

/**
 * **Remove Outermost Parantheses**:
 *
 * A valid parentheses string is either empty `""`, `"(" + A + ")"`, or `A + B`,
 * where `A` and `B` are valid parentheses strings, and `+` represents string
 * concatenation.
 *
 * For example, `""`, `"()"`, `"(())()"`, and `"(()(()))"` are all valid
 * parentheses strings.
 *
 * A valid parentheses string `s` is primitive if it is nonempty, and there does
 * not exist a way to split it into `s = A + B`, with `A` and `B` nonempty valid
 * parentheses strings.
 *
 * Given a valid parentheses string `s`, consider its primitive decomposition:
 * `s = P1 + P2 + ... + Pk`, where `Pi` are primitive valid parentheses strings.
 *
 * Return _`s` after removing the outermost parentheses of every primitive
 * string in the primitive decomposition of `s`_.
 */

public class RemoveOutermostParantheses {

  // The below function linearly processes each element at least once, checking
  // the counter `count` each time if it is grater than `0`.
  public String removeOutermostParantheses(String s) {
    int count = 0;
    StringBuilder ans = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == '(') {
        // Append before incrementing the `count`.
        if (count > 0) {
          ans.append('(');
        }
        count++;
      } else if (c == ')') {
        // Decrement `count` before appending.
        count--;
        if (count > 0) {
          ans.append(')');
        }
      }
    }
    return ans.toString();
  }
}
