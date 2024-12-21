// https://leetcode.com/problems/flood-fill/description/

/**
 * **Flood Fill**:
 *
 * You are given an image represented by an `m x n` grid of integers `image`,
 * where `image[i][j]` represents the pixel value of the image. You are also
 * given three integers `sr`, `sc`, and `color`. Your task is to perform a
 * **flood fill** on the image starting from the pixel `image[sr][sc]`.
 *
 * To perform a **flood fill**:
 *
 * 1. Begin with the starting pixel and change its color to `color`.
 * 2. Perform the same process for each pixel that is **directly adjacent**
 * (pixels that share a side with the original pixel, either horizontally or
 * vertically) and shares the **same color** as the starting pixel.
 * 3. Keep **repeating** this process by checking neighboring pixels of the
 * updated pixels and modifying their color if it matches the original color of
 * the starting pixel.
 * 4. The process stops when there are **no more** adjacent pixels of the
 * original color to update.
 *
 * Return the **modified** image after performing the flood fill.
 */

public class FloodFill {
  private final int[] dirs = { 0, 1, 0, -1, 0 };

  public int[][] floodFillI(int[][] image, int sr, int sc, int color) {
    return recurFloodFill(image, sr, sc, color, image[sr][sc]);
  }

  private int[][] recurFloodFill(int[][] image, int sr, int sc, int color, int firstColor) {
    if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != firstColor
        || image[sr][sc] == color) {
      return image;
    }
    image[sr][sc] = color;
    for (int i = 0; i < 4; i++) {
      int newSr = sr + dirs[i];
      int newSc = sc + dirs[i + 1];

      recurFloodFill(image, newSr, newSc, color, firstColor);
    }

    return image;
  }

  /**
   * [LeetCode Editorial](https://leetcode.com/problems/flood-fill/editorial)
   */
  public int[][] floodFillII(int[][] image, int sr, int sc, int newColor) {
    int color = image[sr][sc];
    if (color != newColor) {
      dfs(image, sr, sc, color, newColor);
    }
    return image;
  }

  public void dfs(int[][] image, int r, int c, int color, int newColor) {
    if (image[r][c] == color) {
      image[r][c] = newColor;
      if (r >= 1) {
        dfs(image, r - 1, c, color, newColor);
      }
      if (c >= 1) {
        dfs(image, r, c - 1, color, newColor);
      }
      if (r + 1 < image.length) {
        dfs(image, r + 1, c, color, newColor);
      }
      if (c + 1 < image[0].length) {
        dfs(image, r, c + 1, color, newColor);
      }
    }
  }
}
