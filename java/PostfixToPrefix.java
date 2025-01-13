// https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/1

import java.util.Stack;

public class PostfixToPrefix {
  static String postToPre(String post_exp) {
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < post_exp.length(); i++) {
      char ch = post_exp.charAt(i);

      if (Character.isLetterOrDigit(ch)) {
        stack.push(ch + "");
      } else {
        String operand1 = stack.pop();
        String operand2 = stack.pop();
        String prefix = ch + operand2 + operand1;
        stack.push(prefix);
      }
    }

    return stack.peek();
  }
}
