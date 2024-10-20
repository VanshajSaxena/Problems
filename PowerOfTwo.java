// https://leetcode.com/problems/power-of-two/description/

/**
 * **Power Of Two**:
 *
 * Given an integer `n`, return _`true` if it is a power of two. Otherwise,
 * return `false`_.
 *
 * An integer `n` is a power of two, if there exists an integer `x` such that `n
 * == 2x`.
 */

public class PowerOfTwo {
  public boolean isPowerOfTwo(int n) {
    return recurIsPowerOfTwo(n);
  }

  private boolean recurIsPowerOfTwo(int n) {
    if (n == 1) { // `1` is a power of `2^x` when `x = 0`
      return true;
    }

    // Return false if `n` is odd or less than 1.
    if ((n & 1) == 1 || n < 1) {
      return false;
    }

    // Divide by `2` and recall.
    return recurIsPowerOfTwo(n >> 1); // This effectively divides `n` by `2`.
  }

  /**
   * This is a clever approach that uses bitwise manipulation.
   *
   * This works because:
   *
   * - Powers of `2` have exactly one '`1`' bit.
   * - `n-1` has all `1`s after that bit.
   * - So `n & (n-1)` will be `0` only for powers of `2`.
   * Ex.
   * ```
   * 8 = 1000 |
   * 7 = 0111 |
   * 8 & 7 = 0000 (power of 2)
   * ```
   *
   */
  public boolean bitWiseIsPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }
}
