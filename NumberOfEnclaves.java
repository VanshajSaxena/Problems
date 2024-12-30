// https://leetcode.com/problems/number-of-enclaves/description/

/**
 * **Number Of Enclaves**:
 *
 * You are given an `m x n` binary matrix `grid`, where `0` represents a sea
 * cell and `1` represents a land cell.
 *
 * A **move** consists of walking from one land cell to another adjacent
 * (**4-directionally**) land cell or walking off the boundary of the `grid`.
 *
 * Return _the number of land cells in `grid` for which we cannot walk off the
 * boundary of the grid in any number of **moves**_.
 */

public class NumberOfEnclaves {
  public int numEnclavesI(int[][] grid) {
    int result = 0;
    for (int i = 0; i < grid.length; i++) {
      // First column.
      if (grid[i][0] == 1) {
        sinkLand(grid, i, 0);
      }
      // Last column.
      if (grid[i][grid[0].length - 1] == 1) {
        sinkLand(grid, i, grid[0].length - 1);
      }
    }
    for (int i = 0; i < grid[0].length; i++) {
      // First Row.
      if (grid[0][i] == 1) {
        sinkLand(grid, 0, i);
      }
      // Last Row.
      if (grid[grid.length - 1][i] == 1) {
        sinkLand(grid, grid.length - 1, i);
      }
    }

    for (int[] row : grid) {
      for (int land : row) {
        if (land == 1) {
          result++;
        }
      }
    }

    return result;
  }

  private final int[] dirs = { 0, 1, 0, -1, 0 };

  private void sinkLand(int[][] grid, int row, int col) {
    grid[row][col] = 0;
    for (int i = 0; i < 4; i++) {
      int newRow = row + dirs[i];
      int newCol = col + dirs[i + 1];

      if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol] == 1) {
        sinkLand(grid, newRow, newCol);
      }
    }
  }
}
