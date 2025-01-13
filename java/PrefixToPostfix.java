// https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1

import java.util.Stack;

public class PrefixToPostfix {
  static String preToPost(String pre_exp) {
    Stack<String> stack = new Stack<>();
    for (int i = pre_exp.length() - 1; i >= 0; i--) {
      char chr = pre_exp.charAt(i);
      if (Character.isLetterOrDigit(chr)) {
        stack.push(Character.toString(chr));
      } else {
        String operand1 = stack.pop();
        String operand2 = stack.pop();
        String temp = operand1 + operand2 + chr;
        stack.push(temp);
      }
    }
    return stack.pop();
  }
}
