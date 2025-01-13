// https://leetcode.com/problems/sliding-puzzle/description/

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * **Sliding Puzzle**:
 *
 * On an `2 x 3` board, there are five tiles labeled from `1` to `5`, and an
 * empty square represented by `0`. A **move** consists of choosing `0` and a
 * 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is
 * `[[1,2,3],[4,5,0]]`.
 *
 * Given the puzzle board `board`, return _the least number of moves required so
 * that the state of the board is solved_. If it is impossible for the state of
 * the board to be solved, return `-1`.
 */

public class SlidingPuzzle {
  private final int[][] directions = {
      { 1, 3 }, // right, down
      { 0, 4, 2 }, // left, down, right
      { 1, 5 }, // left, down
      { 0, 4 }, // up, right
      { 3, 1, 5 }, // left, up, right
      { 2, 4 }, // left, up
  };

  /**
   * **DFS Solution**
   */
  public int slidingPuzzleDFS(int[][] board) {

    // Given state of the board.
    StringBuilder startState = new StringBuilder();

    // Create a 1D array to represent 2D board.
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        startState.append(board[i][j]);
      }
    }

    // Position of zero in the `startState`.
    int zeroPos = startState.indexOf("0");

    // map to store minimum moves to reach at each state.
    HashMap<String, Integer> map = new HashMap<>();

    // recursive helper function using DFS.
    dfs(map, startState.toString(), zeroPos, 0);

    // return the value of the target key or return `-1`.
    return map.getOrDefault("123450", -1);
  }

  private void dfs(HashMap<String, Integer> map, String state, int zeroPos, int moves) {
    // Base Case: Return if map already contains the same key with a value less than
    // the current moves.
    if (map.containsKey(state) && map.get(state) <= moves) {
      return;
    }

    // Add the current state to the map along with the number of moves it requires
    // to reach it.
    map.put(state, moves);

    // Recursive Case
    for (int nextPos : directions[zeroPos]) { // For each possible next position.
      // Swap the two numbers.
      String nextState = swap(state, zeroPos, nextPos); // Helper swap function.

      // Check for the next state, with new zero position, increment the moves.
      dfs(map, nextState, nextPos, moves + 1);
    }
  }

  /**
   * **BFS Solution**
   */
  public int slidingPuzzleBFS(int[][] board) {

    // Given state of the board.
    StringBuilder startState = new StringBuilder();

    // Create a 1D array to represent 2D board.
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
        startState.append(board[i][j]);
      }
    }

    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.offer(startState.toString());
    visited.add(startState.toString());

    String target = "123450";
    int moves = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        String state = queue.poll();

        if (state.equals(target)) {
          return moves;
        }

        visited.add(state);

        int zeroPos = state.indexOf("0");

        for (int nextPos : directions[zeroPos]) {

          String nextState = swap(state, zeroPos, nextPos);

          if (visited.contains(nextState)) {
            continue;
          }

          visited.add(nextState);
          queue.offer(nextState);
        }
      }
      moves++;
    }
    return -1;
  }

  // Swap two characters.
  private String swap(String str, int i, int j) {
    StringBuilder sbr = new StringBuilder(str);
    sbr.setCharAt(i, str.charAt(j));
    sbr.setCharAt(j, str.charAt(i));
    return sbr.toString();
  }
}
