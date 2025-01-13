// https://leetcode.com/problems/rotting-oranges/description/

import java.util.LinkedList;
import java.util.Queue;

/**
 * **Rotting Oranges**:
 *
 * You are given an `m x n` `grid` where each cell can have one of three values:
 *
 * - `0` representing an empty cell,
 * - `1` representing a fresh orange, or
 * - `2` representing a rotten orange.
 *
 * Every minute, any fresh orange that is **4-directionally adjacent** to a
 * rotten orange becomes rotten.
 *
 * Return _the minimum number of minutes that must elapse until no cell has a
 * fresh orange_. If _this is impossible, return `-1`_.
 */

public class RottingOranges {

  public int orangesRotting(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int minutesPassed = 0;
    int freshOranges = 0;

    Queue<int[]> rottenOranges = new LinkedList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 2) {
          rottenOranges.offer(new int[] { i, j });
        } else if (grid[i][j] == 1) {
          freshOranges++;
        }
      }
    }

    if (freshOranges == 0) {
      return 0;
    }

    int[] dir = { 0, 1, 0, -1, 0 };
    while (!rottenOranges.isEmpty()) {
      int size = rottenOranges.size();
      boolean rotted = false;
      for (int i = 0; i < size; i++) {
        int[] position = rottenOranges.poll();
        for (int j = 0; j < 4; j++) {
          int x = position[0] + dir[j];
          int y = position[1] + dir[j + 1];

          if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[0].length - 1 || grid[x][y] != 1) {
            continue;
          }

          grid[x][y] = 2;

          rottenOranges.offer(new int[] { x, y });

          freshOranges--;
          rotted = true;
        }
      }
      if (rotted) {
        minutesPassed++;
      }
    }

    return freshOranges == 0 ? minutesPassed : -1;
  }
}
