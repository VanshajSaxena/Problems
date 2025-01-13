// https://leetcode.com/problems/different-ways-to-add-parentheses/description/

import java.util.ArrayList;
import java.util.List;

/**
 * **Different Ways to Add Parentheses**:
 *
 * Given a string `expression` of numbers and operators, return _all possible
 * results from computing all the different possible ways to group numbers and
 * operators_. You may return the answer in **any order**.
 *
 * The test cases are generated such that the output values fit in a 32-bit
 * integer and the number of different results does not exceed `10^4`.
 *
 */

public class DifferentWaystoAddParentheses {
  public List<Integer> diffWaysToCompute(String expression) {
    List<Integer> result = new ArrayList<Integer>();

    int length = expression.length();
    if (length == 0) {
      return result;
    } else if (length <= 2) {
      // Base case: expression is a single number, add it as an integer result
      result.add(Integer.valueOf(expression));
      return result;
    }

    // Loop through each character in the expression
    for (int i = 0; i < length; i++) {
      char ch = expression.charAt(i);
      if (Character.isDigit(ch)) {
        continue; // Skip if character is a digit
      }

      // Divide expression into left and right parts at the operator
      List<Integer> left = diffWaysToCompute(expression.substring(0, i));
      List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

      // Combine results from left and right based on the operator
      for (Integer l : left) {
        for (Integer r : right) {
          if (ch == '+') {
            result.add(l + r);
          } else if (ch == '-') {
            result.add(l - r);
          } else { // ch == '*'
            result.add(l * r);
          }
        }
      }
    }
    return result;
  }
}
