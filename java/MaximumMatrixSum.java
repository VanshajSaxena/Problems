// https://leetcode.com/problems/maximum-matrix-sum/description/

public class MaximumMatrixSum {
  public long maxMatrixSum(int[][] matrix) {
    // Sum will store the total of absolute values of all elements
    long sum = 0;
    // Count tracks number of negative elements to determine if we need one negative
    // sign
    int count = 0;
    // Track minimum absolute value in entire matrix - we'll put negative sign on
    // this if needed
    long minAbsElement = Integer.MAX_VALUE;

    // Iterate through the matrix
    for (int[] row : matrix) {
      for (int element : row) {
        // Count negative numbers to know if we'll have odd/even negative signs
        if (element < 0) {
          count++;
        }

        // Track minimum absolute value across ALL numbers
        // This will be the number we'll make negative if we need one negative sign
        if (Math.abs(element) < minAbsElement) {
          minAbsElement = Math.abs(element);
        }

        // Add absolute value to sum - initially assume all numbers can be positive
        sum += Math.abs(element);
      }
    }

    // If count is odd, we must keep one negative sign
    // We put this negative sign on the smallest absolute value to minimize
    // reduction
    // So we subtract 2 times the minimum (once to remove the positive, once to add
    // negative)
    // If count is even, we can make all numbers positive, so return the sum as is
    return (count & 1) == 1 ? sum - minAbsElement * 2 : sum;
  }
}
