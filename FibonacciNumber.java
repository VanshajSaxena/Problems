// https://leetcode.com/problems/fibonacci-number/description/

import java.util.HashMap;

/**
 * **Fibonacci Number**:
 *
 * The **Fibonacci numbers**, commonly denoted `F(n)` form a sequence, called
 * the **Fibonacci sequence**, such that each number is the sum of the two
 * preceding ones, starting from `0` and `1`. That is,
 *
 * | F(0) = 0, F(1) = 1
 * | F(n) = F(n - 1) + F(n - 2), for n > 1.
 *
 * Given `n`, calculate `F(n)`.
 */

public class FibonacciNumber {

  /**
   * This is the naive approach for finding the Fibonacci Number.
   * Complexity:
   * - Time: O(2^n) - Due to the recalculation of values for the same `n`.
   * - Space: O(2^n) - Due to the recalculation of values for the same `n`.
   */
  public int fibRecur(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }
    return fibRecur(n - 1) + fibRecur(n - 2);
  }

  // Simple <Integer, Integer> map to store values returned from expensive
  // function calls.
  HashMap<Integer, Integer> map = new HashMap<>();

  /**
   * This is the technique of **memoization**, it stores the results of expansive
   * function calls and returns them later if they are needed from the cached
   * space.
   * Complexity:
   * - Time: O(n) - Since each fibonacci number is calculated only once.
   * - Space: O(n) - The use of a HashMap.
   */
  public int fibMemoization(int n) {
    if (n <= 1) {
      return n;
    }

    if (map.containsKey(n)) {
      return map.get(n);
    }

    int result = fibMemoization(n - 1) + fibMemoization(n - 2);

    map.put(n, result);

    return result;
  }

  /**
   * This is the optimised approach that uses constant space and O(n) time.
   * Complexity:
   * - Time: O(n) - Iteratively adds previous elements uptill n - 2.
   * - Space: O(1) - Two variables
   */
  public int fibIter(int n) {
    if (n <= 1) {
      return n;
    }
    int prev = 1;
    int prePrev = 0;

    int result = 0;
    for (int i = 0; i <= n - 2; i++) {
      result = prev + prePrev;
      prePrev = prev;
      prev = result;
    }

    return result;
  }

}
