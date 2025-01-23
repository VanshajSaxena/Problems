// https://leetcode.com/problems/map-of-highest-peak/description/

import java.util.LinkedList;
import java.util.Queue;

/**
 * **Map Of Highest Peak**:
 *
 * You are given an integer matrix `isWater` of size `m x n` that represents a
 * map of land and water cells.
 *
 * - If `isWater[i][j] == 0`, cell `(i, j)` is a land cell.
 * - If `isWater[i][j] == 1`, cell `(i, j)` is a water cell.
 *
 * You must assign each cell a height in a way that follows these rules:
 *
 * - The height of each cell must be non-negative.
 * - If the cell is a water cell, its height must be `0`.
 * - Any two adjacent cells must have an absolute height difference of at most
 * `1`. A cell is adjacent to another cell if the former is directly north,
 * east, south, or west of the latter (i.e., their sides are touching).
 *
 * Find an assignment of heights such that the maximum height in the matrix is
 * maximized.
 *
 * Return _an integer matrix `height` of size `m x n` where `height[i][j]` is
 * cell `(i, j)`'s height. If there are multiple solutions, return any of them_.
 */

public class MapOfHighestPeak {
  /**
   * Multi Source BFS
   */
  public int[][] highestPeakI(int[][] isWater) {
    Queue<int[]> queue = new LinkedList<>();
    int[][] heights = new int[isWater.length][isWater[0].length];

    for (int row = 0; row < isWater.length; row++) {
      for (int col = 0; col < isWater[0].length; col++) {
        if (isWater[row][col] == 1) {
          queue.add(new int[] { row, col });
          heights[row][col] = 0;
        } else {
          heights[row][col] = -1;
        }
      }
    }

    final int[] dirs = { 0, 1, 0, -1, 0 };
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int row = curr[0];
      int col = curr[1];

      for (int k = 0; k < 4; k++) {
        int nr = row + dirs[k];
        int nc = col + dirs[k + 1];

        if (nr < 0 || nc < 0 || nr >= isWater.length || nc >= isWater[0].length || isWater[nr][nc] == 1
            || heights[nr][nc] != -1) {
          continue;
        }
        heights[nr][nc] = heights[row][col] + 1;
        queue.add(new int[] { nr, nc });
      }
    }

    return heights;
  }
}
