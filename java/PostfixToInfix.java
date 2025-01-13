// https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1

import java.util.Stack;

public class PostfixToInfix {
  static String postToInfix(String exp) {
    Stack<String> stack = new Stack<>();
    for (Character chr : exp.toCharArray()) {
      if (Character.isLetterOrDigit(chr)) {
        stack.push(chr.toString());
      } else {
        String operator1 = stack.pop();
        String operator2 = stack.pop();
        String result = '(' + operator2 + chr + operator1 + ')';
        stack.push(result);
      }
    }
    return stack.peek();
  }
}
