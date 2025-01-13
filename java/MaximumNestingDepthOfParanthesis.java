// https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/

/**
 * **Maximum Nesting Depth Of Paranthesis**:
 *
 * Given a **valid parentheses** string `s`, return the nesting depth of `s`.
 * The **nesting depth** is the **maximum** number of nested parentheses.
 */

public class MaximumNestingDepthOfParanthesis {
  public int maxDepth(String s) {
    int depth = 0;
    int j = 0;
    for (char chr : s.toCharArray()) {
      if (chr == '(') {
        j++;
        depth = Math.max(depth, j);
      } else if (chr == ')') {
        j--;
      }
    }
    return depth;
  }
}
