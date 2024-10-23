// https://leetcode.com/problems/power-of-four/description/

/**
 * **Power Of Four**:
 *
 * Given an integer `n`, return _`true` if it is a power of four. Otherwise,
 * return `false`_.
 *
 * An integer `n` is a power of four, if there exists an integer `x` such that
 * `n == 4^x`.
 */

public class PowerOfFour {

  /**
   * @see PowerOfThree
   */
  public boolean isPowerOfFour(int n) {
    // Check if n > 0, is power of 2, and has 1 in odd position
    return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
  }
}
