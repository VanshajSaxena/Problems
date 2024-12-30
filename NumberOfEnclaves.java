// https://leetcode.com/problems/number-of-enclaves/description/

import java.util.Stack;

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
        sinkLandI(grid, i, 0);
      }
      // Last column.
      if (grid[i][grid[0].length - 1] == 1) {
        sinkLandI(grid, i, grid[0].length - 1);
      }
    }
    for (int i = 0; i < grid[0].length; i++) {
      // First Row.
      if (grid[0][i] == 1) {
        sinkLandI(grid, 0, i);
      }
      // Last Row.
      if (grid[grid.length - 1][i] == 1) {
        sinkLandI(grid, grid.length - 1, i);
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

  // Recursive DFS
  private void sinkLandI(int[][] grid, int row, int col) {
    grid[row][col] = 0;
    for (int i = 0; i < 4; i++) {
      int newRow = row + dirs[i];
      int newCol = col + dirs[i + 1];

      if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol] == 1) {
        sinkLandI(grid, newRow, newCol);
      }
    }
  }

  // Iterative DFS
  private void sinkLandII(int[][] grid, int row, int col) {
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[] { row, col });
    while (!stack.isEmpty()) {
      int[] coordinate = stack.pop();
      int currRow = coordinate[0];
      int currCol = coordinate[1];
      grid[currRow][currCol] = 0;

      for (int i = 0; i < 4; i++) {
        int newRow = currRow + dirs[i];
        int newCol = currCol + dirs[i + 1];

        if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length
            && grid[newRow][newCol] == 1) {
          stack.push(new int[] { newRow, newCol });
        }
      }
    }
  }

}
