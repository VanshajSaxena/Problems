// https://leetcode.com/problems/max-area-of-island/description/

/**
 * **Max Area Of Island**:
 *
 * You are given an `m x n` binary matrix `grid`. An island is a group of `1`'s
 * (representing land) connected **4-directionally** (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * The **area** of an island is the number of cells with a value `1` in the
 * island.
 *
 * Return _the maximum **area** of an island in `grid`_. If there is no island,
 * return
 * `0`.
 */

public class MaxAreaOfIsland {
  private int[] d = { 0, 1, 0, -1, 0 };

  public int maxAreaOfIsland(int[][] grid) {
    int maxArea = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        int currArea = addArea(grid, i, j);
        maxArea = Math.max(maxArea, currArea);
      }
    }

    return maxArea;
  }

  private int addArea(int[][] grid, int i, int j) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[i].length || grid[i][j] == 0) {
      return 0;
    }

    grid[i][j] = 0;
    int currArea = 1;

    for (int k = 0; k < 4; k++) {
      currArea += addArea(grid, i + d[k], j + d[k + 1]);
    }

    return currArea;
  }
}
