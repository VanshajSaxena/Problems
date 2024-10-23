// https://leetcode.com/problems/power-of-three/description/

/**
 * **Power Of Three**:
 *
 * Given an integer `n`, return _`true` if it is a power of three. Otherwise,
 * return `false`_.
 *
 * An integer `n` is a power of three, if there exists an integer `x` such that
 * `n == 3^x`.
 */
public class PowerOfThree {

  /**
   * This is a recursive approach to find if `n` is a power of `3`.
   * Complexity:
   * - Time: O(log(base 3) n) - Each recursive call divides `n` by `3`, reducing
   * it logarithmically.
   * - Space: O(log(base 3) n) - Due to recursion, the space complexity
   * corresponds to the depth of recursive call stack, which is proportional to
   * the logarithmic base `3` of `n`.
   */
  public boolean isPowerOfThreeRecusive(int n) {
    return recurIsPowerOfThree(n);
  }

  private boolean recurIsPowerOfThree(int n) {
    // Base Case: if `n` reaches `1`, the number was a power of 3.
    if (n == 1) {
      return true;
    }

    // Another Base Case: if `n` < `1` or `n` not divisible by `3`, it's not a power
    // of `3`.
    if (n < 1 || n % 3 != 0) {
      return false;
    }

    // Recursive Case: divide by `3` and check again.
    return recurIsPowerOfThree(n / 3);
  }

  /**
   * This is the iterative solution to the problem.
   * Complexity:
   * - Time: O(log(base 3) n) - Each time `n` is divided by `3`, hence problem is
   * reducing logarithmically.
   * - Space: O(1) - Constant space is used.
   */
  public boolean isPowerOfThreeIter(int n) {
    // No `n < 1` can be a power of `3`
    if (n < 1) {
      return false;
    }

    // Divide until a remainder other than `0` if found.
    while (n % 3 == 0) {
      n /= 3;
    }

    // is the remainder equals to `1`?
    return n == 1;
  }

  /**
   * Works because any factor of the largest power of 3 that fits in an integer
   * must itself be a power of 3.
   * Complexity:
   * - Time: O(1) - Single operation
   * - Space: O(1) - Single operation
   */
  public boolean isPowerOfThreeOptimised(int n) {
    // 3^19 = 1162261467 is largest power of 3 under 2^31-1
    return n > 0 && 1162261467 % n == 0;
  }
}
