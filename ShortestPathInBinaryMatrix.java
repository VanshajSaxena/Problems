// https://leetcode.com/problems/shortest-path-in-binary-matrix/description/

import java.util.LinkedList;
import java.util.Queue;

/**
 * **Shortest Path In Binary Matrix**:
 *
 * Given an `n x n` binary matrix `grid`, return _the length of the shortest
 * **clear path** in the matrix_. If there is no clear path, return `-1`.
 *
 * A clear path in a binary matrix is a path from the **top-left** cell (i.e.,
 * `(0, 0)`) to the **bottom-right** cell (i.e., `(n - 1, n - 1)`) such that:
 *
 * All the visited cells of the path are `0`.
 * All the adjacent cells of the path are **8-directionally** connected (i.e.,
 * they are different and they share an edge or a corner).
 *
 * The **length of a clear path** is the number of visited cells of this path.
 */

public class ShortestPathInBinaryMatrix {
  private final int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, -1 }, { -1, 1 }, { -1, -1 },
      { 1, 1 } };

  public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;
    if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
      return -1;
    }

    boolean[][] visited = new boolean[n][n];
    Queue<int[]> queue = new LinkedList<>();

    queue.add(new int[] { 0, 0 });
    visited[0][0] = true;

    int pathLen = 0;
    // BFS
    while (!queue.isEmpty()) {
      int size = queue.size();
      pathLen++;
      for (int i = 0; i < size; i++) {
        int[] currNode = queue.poll();
        // If reached the bottom right corner.
        if (currNode[0] == n - 1 && currNode[1] == n - 1) {
          return pathLen;
        }
        for (int k = 0; k < 8; k++) {
          int x = currNode[0] + dir[k][0];
          int y = currNode[1] + dir[k][1];

          if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 0) {
            queue.add(new int[] { x, y });
            visited[x][y] = true;
          }
        }
      }
    }
    return -1;
  }
}
