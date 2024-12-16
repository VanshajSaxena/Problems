// https://leetcode.com/problems/number-of-islands/description/

/**
 * **Number Of Islands**:
 *
 * Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s
 * (land) and `'0'`s (water), return _the number of islands_.
 *
 * An **island** is surrounded by water and is formed by connecting adjacent
 * lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 */

public class NumberOfIslands {
  private int[] d = { 0, 1, 0, -1, 0 };

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/number-of-islands/solutions/56349/7-lines-python-14-lines-java)
   */
  public int numIslands(char[][] grid) {
    int numOfLands = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        numOfLands += sink(grid, i, j);
      }
    }

    return numOfLands;
  }

  private int sink(char[][] grid, int i, int j) {
    // Check for out of bounds indices.
    if (i < 0 || i == grid.length || j < 0 || j == grid[i].length || grid[i][j] == '0') {
      return 0;
    }
    grid[i][j] = '0';
    // Sink all land in four directions.
    for (int k = 0; k < 4; k++) {
      sink(grid, i + d[k], j + d[k + 1]);
    }
    return 1;
  }
}
