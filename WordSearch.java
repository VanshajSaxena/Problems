// https://leetcode.com/problems/word-search/description/

/**
 * **Word Search**:
 *
 * Given an `m x n` grid of characters `board` and a string `word`, return
 * `true` _if `word` exists in the grid_.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 */

public class WordSearch {

  public boolean exist(char[][] board, String word) {

    // Search for the first character of the `word` in `board`.
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == word.charAt(0)) { // If character found.
          // Check if the whole word exists.
          if (recurWordSearch(board, word, i, j, 0)) {
            return true; // Return `true` if `word` exists.
          }
        }
      }
    }

    // Else return `false`.
    return false;
  }

  private boolean recurWordSearch(char[][] board, String word, int row, int col, int idx) {

    // Base Case: If all below cases fail to pass, we found our string.
    if (idx == word.length()) {
      return true;
    }

    // Another Base Case: No need to search further, if:
    // - Reached a boundary (left, right, top, bottom).
    // - The character doesn't match.
    // - The character is already visited.
    if (row < 0 || col < 0 || row == board.length || col == board[0].length || board[row][col] != word.charAt(idx)
        || board[row][col] == '!') {
      return false;
    }

    char c = board[row][col];
    // Mark character as visited.
    board[row][col] = '!';

    boolean top = recurWordSearch(board, word, row - 1, col, idx + 1);

    boolean right = recurWordSearch(board, word, row, col + 1, idx + 1);

    boolean bottom = recurWordSearch(board, word, row + 1, col, idx + 1);

    boolean left = recurWordSearch(board, word, row, col - 1, idx + 1);

    // Restore the character.
    board[row][col] = c;

    return top || bottom || left || right;
  }
}
