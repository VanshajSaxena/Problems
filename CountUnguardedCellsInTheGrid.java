// https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/

/**
 * **Count Unguarded Cells In The Grid**:
 *
 * You are given two integers `m` and `n` representing a **0-indexed** `m x n`
 * grid. You are also given two 2D integer arrays `guards` and `walls` where
 * `guards[i] = [rowi, coli]` and `walls[j] = [rowj, colj]` represent the
 * positions of the `ith` guard and `jth` wall respectively.
 *
 * A guard can see **every** cell in the four cardinal directions (north, east,
 * south, or west) starting from their position unless **obstructed** by a wall
 * or another guard. A cell is **guarded** if there is **at least** one guard
 * that can see it.
 *
 * Return _the number of unoccupied cells that are **not guarded**_.
 */

public class CountUnguardedCellsInTheGrid {

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/count-unguarded-cells-in-the-grid/editorial)
   */
  public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
    int[][] field = new int[m][n];

    for (int[] guard : guards) {
      field[guard[0]][guard[1]] = -1;
    }

    for (int[] wall : walls) {
      field[wall[0]][wall[1]] = -1;
    }

    for (int[] guard : guards) {
      markAsGuarded(guard[0], guard[1], field);
    }

    int count = 0;

    for (int[] row : field) {
      for (int cell : row) {
        if (cell == 0) {
          count++;
        }
      }
    }
    return count;
  }

  private void markAsGuarded(int row, int col, int[][] field) {

    // Up
    for (int r = row - 1; r >= 0; r--) {
      if (field[r][col] == -1) {
        break;
      }
      field[r][col] = -2;
    }

    // Down
    for (int r = row + 1; r < field.length; r++) {
      if (field[r][col] == -1) {
        break;
      }
      field[r][col] = -2;
    }

    // Left
    for (int c = col - 1; c >= 0; c--) {
      if (field[row][c] == -1) {
        break;
      }
      field[row][c] = -2;
    }

    // Right
    for (int c = col + 1; c < field[0].length; c++) {
      if (field[row][c] == -1) {
        break;
      }
      field[row][c] = -2;
    }
  }
}
