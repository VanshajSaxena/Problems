// https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/description/

import java.util.Arrays;

/**
 * **Check If Grid Can Be Cut Into Sections**:
 *
 * You are given an integer `n` representing the dimensions of an `n x n` grid,
 * with the origin at the bottom-left corner of the grid. You are also given a
 * 2D array of coordinates `rectangles`, where `rectangles[i]` is in the form
 * `[startx, starty, endx, endy]`, representing a rectangle on the grid. Each
 * rectangle is defined as follows:
 *
 * - `(startx, starty)`: The bottom-left corner of the rectangle.
 * - `(endx, endy)`: The top-right corner of the rectangle.
 *
 * **Note** that the rectangles do not overlap. Your task is to determine if it
 * is possible to make **either two horizontal or two vertical cuts** on the
 * grid such that:
 *
 * - Each of the three resulting sections formed by the cuts contains **at
 * least** one rectangle.
 * - Every rectangle belongs to **exactly** one section.
 *
 * Return `true` if such cuts can be made; otherwise, return `false`.
 */

class CheckIfGridCanBeCutIntoSectionsI {
  public boolean checkValidCuts(int n, int[][] rectangles) {
    int[][] X = new int[rectangles.length][2];
    int[][] Y = new int[rectangles.length][2];

    for (int i = 0; i < rectangles.length; i++) {
      X[i][0] = rectangles[i][0];
      X[i][1] = rectangles[i][2];
      Y[i][0] = rectangles[i][1];
      Y[i][1] = rectangles[i][3];
    }

    return check(X) || check(Y);
  }

  private boolean check(int[][] A) {
    // Sorting them does not change the range. It allows processing.
    Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
    int res = 0;

    int pre = A[0][1];
    for (int[] range : A) {
      int a = range[0], b = range[1];
      if (pre <= a) {
        res++;
      }
      pre = Math.max(pre, b);
    }
    return res > 1; // If there are at least two gaps, means three partitions.
  }
}
