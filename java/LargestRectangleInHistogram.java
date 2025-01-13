// https://leetcode.com/problems/largest-rectangle-in-histogram/description/

/**
 * **Largest Rectangle In Histogram**:
 *
 * Given an array of integers `heights` representing the histogram's bar height
 * where the width of each bar is `1`, return _the area of the largest rectangle
 * in the histogram_.
 */

public class LargestRectangleInHistogram {

  /**
   * This solution uses an implicit stack to perform the calculation of NSE and
   * PSE.
   */
  public int largestRectangleArea(int[] heights) {
    int length = heights.length;

    if (heights == null || length == 0) {
      return 0;
    }

    int[] smallerFromLeft = new int[length];
    int[] smallerFromRight = new int[length];

    smallerFromLeft[0] = -1;
    smallerFromRight[length - 1] = length;

    for (int i = 1; i < length; i++) {
      int top = i - 1;

      while (top >= 0 && heights[top] >= heights[i]) {
        top = smallerFromLeft[top];
      }

      smallerFromLeft[i] = top;
    }

    for (int i = length - 2; i >= 0; i--) {
      int top = i + 1;

      while (top < length && heights[top] >= heights[i]) {
        top = smallerFromRight[top];
      }

      smallerFromRight[i] = top;
    }

    int maxArea = 0;

    for (int i = 0; i < length; i++) {
      maxArea = Math.max(maxArea, heights[i] * (smallerFromRight[i] - smallerFromLeft[i] - 1));
    }

    return maxArea;
  }
}
