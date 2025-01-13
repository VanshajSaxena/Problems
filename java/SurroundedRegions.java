// https://leetcode.com/problems/surrounded-regions/description/

/**
 * **Surrounded Regions**:
 *
 * You are given an `m x n` matrix `board` containing **letters** `'X'` and
 * `'O'`, **capture regions** that are **surrounded**:
 *
 * - **Connect**: A cell is connected to adjacent cells horizontally or
 * vertically.
 * - **Region**: To form a region **connect every** `'O'` cell.
 * - **Surround**: The region is surrounded with `'X'` cells if you can
 * **connect the region** with `'X'` cells and none of the region cells are on
 * the edge of the `board`.
 *
 * A **surrounded region is captured** by replacing all `'O'`s with `'X'`s in
 * the input matrix `board`.
 */

public class SurroundedRegions {
  public void solve(char[][] board) {
    if (board.length < 2 || board[0].length < 2) {
      return;
    }

    int m = board.length, n = board[0].length;

    // Start form first column and last column and mark any connected 'O' with '*'.
    for (int r = 0; r < m; r++) {
      if (board[r][0] == 'O') {
        markNonCapturable(board, r, 0);
      }
      if (board[r][n - 1] == 'O') {
        markNonCapturable(board, r, n - 1);
      }
    }

    // Start form first row and last row and mark any connected 'O' with '*'.
    for (int c = 0; c < n; c++) {
      if (board[0][c] == 'O') {
        markNonCapturable(board, 0, c);
      }
      if (board[m - 1][c] == 'O') {
        markNonCapturable(board, m - 1, c);
      }
    }

    // Post-processing: mark all 'O's with 'X', and '*'s back to 'O'.
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (board[r][c] == 'O') {
          board[r][c] = 'X';
        } else if (board[r][c] == '*') {
          board[r][c] = 'O';
        }
      }
    }
  }

  private final int[] dirs = { 0, 1, 0, -1, 0 };

  private void markNonCapturable(char[][] board, int row, int col) {

    board[row][col] = '*';

    for (int i = 0; i < 4; i++) {
      int nr = row + dirs[i];
      int nc = col + dirs[i + 1];

      if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'O') {
        markNonCapturable(board, nr, nc);
      }
    }
  }
}
