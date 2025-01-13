// https://leetcode.com/problems/count-good-numbers/description/

/**
 * **Count Good Numbers**:
 *
 * A digit string is **good** if the digits **(0-indexed)** at **even** indices
 * are **even** and the digits at **odd** indices are **prime** (`2`, `3`, `5`,
 * or `7`).
 *
 * - For example, `"2582"` is good because the digits (`2` and `8`) at even
 * positions are even and the digits (`5` and `2`) at odd positions are prime.
 * However, `"3245"` is not good because `3` is at an even index but is not
 * even.
 *
 * Given an integer `n`, return the total number of good digit strings of length
 * `n`. Since the answer may be large, **return it modulo** `10^9 + 7`.
 *
 * A **digit string** is a string consisting of digits `0` through `9` that may
 * contain leading zeros.
 */

public class CountGoodNumbers {

  // Special number quoted in question.
  private static final int MOD = 1000000007;

  /**
   * The procedure is to find the number of even and odd places, then calculating
   * the number of possible values for each place, take `% MOD` each time to
   * prevent overflow. The return `result` as an int.
   */
  public int countGoodNumbers(long n) {
    long ep = (n + 1) / 2; // Ceiling division for even positions.
    long op = n / 2; // Floor division for odd positions.

    // For each even position there are `5` possibilities.
    long totalEvenPossibilities = power(5, ep);
    // For each odd position there are `4` possibilities.
    long totalOddPossibilities = power(4, op);

    // Safely multiply both results
    long result = multiplyModulo(totalEvenPossibilities, totalOddPossibilities);

    return (int) result;
  }

  // This is power function using binary exponentiation.
  private long power(int num, long p) {
    long result = 1;
    long currentProduct = num;

    while (p > 0) {
      if (p % 2 == 1) {
        result = multiplyModulo(result, currentProduct);
      }
      currentProduct = multiplyModulo(currentProduct, currentProduct);
      p >>= 1;
    }

    return result;
  }

  // Safely multiplies the two number to prevent overflow.
  private long multiplyModulo(long num1, long num2) {
    return ((num1 % MOD) * (num2 % MOD) % MOD);
  }

}
