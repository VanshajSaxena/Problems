// https://leetcode.com/problems/count-servers-that-communicate/description/

import java.util.LinkedList;
import java.util.Queue;

/**
 * **Count Servers That Communicate**:
 *
 * You are given a map of a server center, represented as a `m * n` integer
 * matrix `grid`, where 1 means that on that cell there is a server and 0 means
 * that it is no server. Two servers are said to communicate if they are on the
 * same row or on the same column.
 *
 * Return the number of servers that communicate with any other server.
 */

public class CountServersThatCommunicate {
  /**
   * Novice Way
   */
  public int countServersI(int[][] grid) {
    int count = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == -1 || grid[row][col] == 0) {
          continue;
        }
        int currCount = countConnected(grid, row, col);
        if (currCount != -1) {
          count += currCount + 1;
        }
        grid[row][col] = -1;
      }
    }
    return count;
  }

  private int countConnected(int[][] grid, int row, int col) {
    int currCount = 0;
    int m = grid.length;
    int n = grid[0].length;
    int r = row; // row pointer
    int c = col; // row pointer
    boolean connected = false;
    // Up
    while (--r >= 0) {
      if (grid[r][col] == 1) {
        currCount++;
        grid[r][col] = -1;
      }
      connected = connected || grid[r][col] == 1 || grid[r][col] == -1;
    }
    // Down
    r = row;
    while (++r < m) {
      if (grid[r][col] == 1) {
        currCount++;
        grid[r][col] = -1;
      }
      connected = connected || grid[r][col] == 1 || grid[r][col] == -1;
    }
    // Left
    while (--c >= 0) {
      if (grid[row][c] == 1) {
        currCount++;
        grid[row][c] = -1;
      }
      connected = connected || grid[row][c] == 1 || grid[row][c] == -1;
    }
    // Right
    c = col;
    while (++c < n) {
      if (grid[row][c] == 1) {
        currCount++;
        grid[row][c] = -1;
      }
      connected = connected || grid[row][c] == 1 || grid[row][c] == -1;
    }
    return !connected ? -1 : currCount;
  }

  /**
   * [Better
   * Way](https://leetcode.com/problems/count-servers-that-communicate/solutions/6317649/python-solution-brute-force-but-efficien-s91c)
   */
  public int countServersII(int[][] grid) {
    Queue<int[]> queue = new LinkedList<>();
    int[] rowCount = new int[grid.length];
    int[] colCount = new int[grid[0].length];
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == 1) {
          rowCount[row]++;
          colCount[col]++;
          queue.offer(new int[] { row, col });
        }
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {
      int[] server = queue.poll();
      int row = server[0];
      int col = server[1];
      if (rowCount[row] > 1 || colCount[col] > 1) {
        count++;
      }
    }
    return count;
  }
}
