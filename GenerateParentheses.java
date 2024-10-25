// https://leetcode.com/problems/generate-parentheses/description/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * **Generate Parantheses**:
 *
 * Given `n` pairs of parentheses, write a function to _generate all
 * combinations of well-formed parentheses_.
 */

public class GenerateParentheses {

  /**
   * Recursive Solution:
   */
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    result = iterGenerateParenthesis(n);
    return result;
  }

  private void recurGenerateParenthesis(List<String> result, int left, int right, String string, int n) {
    if (string.length() == n * 2) {
      result.add(string);
      return;
    }

    if (left < n) {
      recurGenerateParenthesis(result, left + 1, right, string + "(", n);
    }

    if (right < left) {
      recurGenerateParenthesis(result, left, right + 1, string + ")", n);
    }
  }

  private static class State {
    String current;
    int open;
    int close;

    public State(String current, int open, int close) {
      this.current = current;
      this.open = open;
      this.close = close;
    }
  }

  public List<String> iterGenerateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    Stack<State> stack = new Stack<>();

    stack.push(new State("", 0, 0));

    while (!stack.isEmpty()) {
      State state = stack.pop();

      if (state.current.length() == 2 * n) {
        result.add(state.current);
        continue;
      }

      if (state.open < n) {
        stack.push(new State(state.current + "(", state.open + 1, state.close));
      }

      if (state.close < state.open) {
        stack.push(new State(state.current + ")", state.open, state.close + 1));
      }
    }

    return result;
  }

}
