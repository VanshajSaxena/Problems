// https://leetcode.com/problems/minimum-operations-to-make-columns-strictly-increasing/description

/**
 * **Minimum Operations To Make Columns Strictly Increasing**:
 *
 * You are given a `m x n` matrix `grid` consisting of **non-negative**
 * integers.
 *
 * In one operation, you can increment the value of any `grid[i][j]` by 1.
 *
 * Return the **minimum** number of operations needed to make all columns of
 * `grid` **strictly increasing**.
 */

public class MinimumOperationsToMakeColumnsStrictlyIncreasing {
  // Naive Way
  public int minimumOperationsI(int[][] grid) {
    int minop = 0;

    for (int col = 0; col < grid[0].length; col++) {
      int maxstate = grid[0][col];
      for (int row = 1; row < grid.length; row++) {
        int currElement = grid[row][col];
        if (currElement > maxstate) {
          maxstate = currElement;
        } else {
          minop += maxstate - currElement + 1;
          maxstate = currElement + (maxstate - currElement + 1);
        }
      }
    }

    return minop;
  }

  // `@lee215`'s solution
  public int minimumOperationsII(int[][] grid) {
    int res = 0;
    for (int j = 0; j < grid[0].length; ++j) {
      for (int i = 1; i < grid.length; ++i) {
        if (grid[i][j] < grid[i - 1][j] + 1) {
          res += grid[i - 1][j] + 1 - grid[i][j];
          grid[i][j] = grid[i - 1][j] + 1;
        }
      }
    }
    return res;
  }
}
