// https://leetcode.com/problems/01-matrix/description/

import java.util.LinkedList;
import java.util.Queue;

/**
 * **Zero One Matrix**:
 *
 * Given an `m x n` binary matrix `mat`, return _the distance of the nearest `0`
 * for each cell_.
 *
 * The distance between two adjacent cells is `1`.
 */
public class ZeroOneMatrix {

  /**
   * Novice Way: TLE
   */
  public int[][] updateMatrixI(int[][] mat) {
    int[][] result = new int[mat.length][mat[0].length];

    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        if (mat[i][j] == 0) {
          result[i][j] = 0;
        } else {
          distance(mat, result, i, j);
        }
      }
    }
    return result;
  }

  private final int[] dirs = { 0, 1, 0, -1, 0 };

  private void distance(int[][] mat, int[][] result, int row, int col) {
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[result.length][result[0].length];
    queue.add(new int[] { row, col });
    visited[row][col] = true;

    int distance = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {

        int[] coordinate = queue.poll();

        int r = coordinate[0];
        int c = coordinate[1];

        if (mat[r][c] == 0) {
          result[row][col] = distance;
          return;
        }

        for (int j = 0; j < 4; j++) {
          int newRow = r + dirs[j];
          int newCol = c + dirs[j + 1];
          if (newRow >= 0 && newCol >= 0 && newRow < mat.length && newCol < mat[0].length && !visited[newRow][newCol]) {
            queue.add(new int[] { newRow, newCol });
            visited[newRow][newCol] = true;
          }
        }
      }
      distance++;
    }
  }

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/01-matrix/solutions/1369741/c-java-python-bfs-dp-solutions-with-picture-clean-concise-o-1-space)
   * BFS
   */
  public int[][] updateMatrixII(int[][] mat) {
    int m = mat.length, n = mat[0].length;

    Queue<int[]> queue = new LinkedList<>();

    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (mat[r][c] == 0) {
          queue.offer(new int[] { r, c });
        } else {
          mat[r][c] = -1;
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();

      int r = curr[0];
      int c = curr[1];

      for (int i = 0; i < 4; i++) {
        int nr = r + dirs[i], nc = c + dirs[i + 1];

        if (nr < 0 || nc < 0 || nr == m || nc == n || mat[nr][nc] != -1) {
          continue;
        }

        mat[nr][nc] = mat[r][c] + 1;
        queue.offer(new int[] { nr, nc });
      }
    }

    return mat;
  }

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/01-matrix/solutions/1369741/c-java-python-bfs-dp-solutions-with-picture-clean-concise-o-1-space)
   * DP
   */
  public int[][] updateMatrixIII(int[][] mat) {
    int m = mat.length, n = mat[0].length;

    int maxDis = m + n;

    // top-left -> bottom-right
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (mat[r][c] == 0)
          continue;
        int top = maxDis, left = maxDis;
        if (r - 1 >= 0)
          top = mat[r - 1][c];
        if (c - 1 >= 0)
          left = mat[r][c - 1];
        mat[r][c] = Math.min(top, left) + 1;
      }
    }

    // bottom-right -> top-left
    for (int r = m - 1; r >= 0; r--) {
      for (int c = n - 1; c >= 0; c--) {
        if (mat[r][c] == 0)
          continue;
        int bottom = maxDis, right = maxDis;
        if (r + 1 < m) {
          bottom = mat[r + 1][c];
        }
        if (c + 1 < n) {
          right = mat[r][c + 1];
        }
        // Don't overwrite min values.
        mat[r][c] = Math.min(mat[r][c], Math.min(bottom, right) + 1);
      }
    }

    return mat;
  }
}
