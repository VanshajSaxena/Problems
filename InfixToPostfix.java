// https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1

import java.util.Stack;

public class InfixToPostfix {

  public static String convertToPostfix(String infixExpression) {
    StringBuilder postfix = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    for (char c : infixExpression.toCharArray()) {
      if (Character.isLetterOrDigit(c)) {
        postfix.append(c);
      } else if (c == '(') {
        stack.push(c);
      } else if (c == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          postfix.append(stack.pop());
        }
        if (!stack.isEmpty() && stack.peek() == '(') {
          stack.pop();
        }
      } else {
        while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
          postfix.append(stack.pop());
        }
        stack.push(c);
      }
    }

    while (!stack.isEmpty()) {
      postfix.append(stack.pop());
    }

    return postfix.toString();
  }

  private static int precedence(char operator) {
    switch (operator) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
      default:
        return -1;
    }
  }
}
