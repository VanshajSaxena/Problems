// https://leetcode.com/problems/first-completely-painted-row-or-column/description/

import java.util.HashMap;
import java.util.Map;

public class FirstCompletelyPaintedRowOrColumn {
  class Position {
    int row;
    int col;

    /**
     * @return the x
     */
    public int getRow() {
      return row;
    }

    /**
     * @return the y
     */
    public int getCol() {
      return col;
    }

    Position(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  public int firstCompleteIndex(int[] arr, int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    Map<Integer, Position> map = new HashMap<>();

    int[] rowCount = new int[rows];
    int[] colCount = new int[cols];

    for (int row = 0; row < mat.length; row++) {
      for (int col = 0; col < mat[0].length; col++) {
        map.put(mat[row][col], new Position(row, col));
      }
    }

    for (int i = 0; i < arr.length; i++) {
      int num = arr[i];
      Position pos = map.get(num);
      int row = pos.getRow();
      int col = pos.getCol();

      rowCount[row]++;
      colCount[col]++;
      if (rowCount[row] == cols || colCount[col] == rows) {
        return i;
      }
    }
    return -1;
  }
}
