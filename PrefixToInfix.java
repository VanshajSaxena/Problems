// https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1

import java.util.Stack;

public class PrefixToInfix {
  static String preToInfix(String pre_exp) {
    StringBuilder infix = new StringBuilder();
    Stack<String> stack = new Stack<>();
    for (int i = pre_exp.length() - 1; i >= 0; i--) {
      Character currChar = pre_exp.charAt(i);
      if (Character.isLetterOrDigit(currChar)) {
        stack.push(currChar.toString());
      } else {
        String operand1 = stack.pop();
        String operand2 = stack.pop();
        stack.push('(' + operand1 + currChar + operand2 + ')');
      }
    }
    while (!stack.isEmpty()) {
      infix.append(stack.pop());
    }
    return infix.toString();
  }
}
