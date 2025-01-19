// https://leetcode.com/problems/trapping-rain-water-ii/description/

import java.util.PriorityQueue;

/**
 * **Trapping Rain Water II**:
 *
 * Given an `m x n` integer matrix `heightMap` representing the height of each
 * unit cell in a 2D elevation map, return _the volume of water it can trap
 * after raining_.
 */

public class TrappingRainWaterII {

  public class Cell implements Comparable<Cell> {
    int elevation;
    int row;
    int col;

    public Cell(int elevation, int row, int col) {
      this.elevation = elevation;
      this.row = row;
      this.col = col;
    }

    @Override
    public int compareTo(Cell other) {
      return Integer.compare(this.elevation, other.elevation);
    }
  }

  public int trapRainWater(int[][] heightMap) {
    int rows = heightMap.length;
    int cols = heightMap[0].length;
    int trappedWater = 0;

    PriorityQueue<Cell> queue = new PriorityQueue<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
          queue.add(new Cell(heightMap[i][j], i, j));
          heightMap[i][j] = -1;
        }
      }
    }

    int directions[] = { 0, 1, 0, -1, 0 };
    int maxHeight = -1;
    while (!queue.isEmpty()) {
      Cell cell = queue.poll();
      maxHeight = Integer.max(cell.elevation, maxHeight);
      trappedWater += maxHeight - cell.elevation;
      for (int k = 0; k < 4; k++) {
        int newRow = cell.row + directions[k];
        int newCol = cell.col + directions[k + 1];

        if (newRow > 0 && newCol > 0 && newRow < rows - 1 && newCol < cols - 1 && heightMap[newRow][newCol] != -1) {
          queue.add(new Cell(heightMap[newRow][newCol], newRow, newCol));
          heightMap[newRow][newCol] = -1;
        }
      }
    }

    return trappedWater;
  }
}
