// https://leetcode.com/problems/valid-parentheses/description/

import java.util.Stack;

/**
 * **Valid Parentheses**:
 *
 * Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`,
 * `'['` and `']'`, determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * ~ Open brackets must be closed by the same type of brackets.
 * ~ Open brackets must be closed in the correct order.
 * ~ Every close bracket has a corresponding open bracket of the same type.
 */

public class ValidParentheses {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (Character chr : s.toCharArray()) {
      if (chr == '(' || chr == '{' || chr == '[') {
        stack.push(chr);
      } else {
        if (stack.isEmpty()) {
          return false;
        }
        Character popedChr = stack.pop();
        if (!(popedChr == '(' && chr == ')' || popedChr == '{' && chr == '}' || popedChr == '[' && chr == ']')) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
