// https://leetcode.com/problems/powx-n/description/

/**
 * **Pow(x,n)**:
 *
 * Implement `pow(x, n)`, which calculates `x` raised to the power `n` (i.e.,
 * `x^n`).
 */

public class PowXN {

  /**
   * This method of exponentiation is called Exponentiation by Squaring.
   * This approach reduces the number of recursive calls by leveraging the fact
   * that:
   * 1. `x^n=(x^2)n/2`, when n is even.
   * 2. `x^n=x×x^n−1`, when n is odd.
   * This method reduces the time complexity to `O(log n)`.
   */
  public double myPow(double x, int n) {
    if (x == 1) {
      return 1;
    }
    return recurPower(x, n);
  }

  private double recurPower(double x, int n) {
    if (n == 0) {
      return 1;
    }

    if (n < 0) {
      return 1 / recurPower(x, -n);
    }

    double half = recurPower(x, n / 2);

    if (n % 2 == 0) {
      return half * half;
    } else {
      return x * half * half;
    }
  }

  /*
   * Iterative function using binary exponentiation
   */
  public double iterPower(double x, int n) {
    // Handle edge cases
    if (n == 0 || x == 1) {
      return 1;
    }

    long N = n; // Cast to long to prevent overflow.

    // If `N` is negative, flip sign, inverse base.
    if (N < 0) {
      N = -N;
      x = 1 / x;
    }

    double result = 1; // Final return value.
    double currentProduct = x; // Holds the current product.

    // Using binary exponentiation.
    while (N > 0) {
      // If `N` is odd also multiply `currentProduct` to `result`.
      if ((N & 1) == 1) {
        result *= currentProduct;
      }
      // Square `currentProduct` each time.
      currentProduct *= currentProduct;
      // Divide `N` by `2` each time.
      N >>= 1;
    }

    // Return `result`.
    return result;
  }
}
