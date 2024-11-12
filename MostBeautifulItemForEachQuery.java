// https://leetcode.com/problems/most-beautiful-item-for-each-query/description/

import java.util.Arrays;

/**
 * **Most Beautiful Item For Each Query**:
 *
 * You are given a 2D integer array `items` where `items[i] = [pricei, beautyi]`
 * denotes the **price** and **beauty** of an item respectively.
 *
 * You are also given a **0-indexed** integer array `queries`. For each
 * `queries[j]`, you want to determine the **maximum beauty** of an item whose
 * **price** is **less than or equal** to `queries[j]`. If no such item exists,
 * then the answer to this query is `0`.
 *
 * Return _an array `answer` of the same length as `queries` where `answer[j]`
 * is the answer to the `jth` query_.
 */

public class MostBeautifulItemForEachQuery {

  /**
   * Approach One:
   * [LeetCode
   * Editorial](https://leetcode.com/problems/most-beautiful-item-for-each-query/editorial)
   */
  public int[] maximumBeauty(int[][] items, int[] queries) {
    int[] ans = new int[queries.length];

    // Sort and store max beauty
    Arrays.sort(items, (a, b) -> a[0] - b[0]);
    int max = items[0][1];
    for (int i = 0; i < items.length; i++) {
      max = Math.max(max, items[i][1]);
      items[i][1] = max;
    }

    for (int i = 0; i < queries.length; i++) {
      // answer i-th query
      ans[i] = binarySearch(items, queries[i]);
    }

    return ans;
  }

  private int binarySearch(int[][] items, int targetPrice) {
    int l = 0;
    int r = items.length - 1;
    int maxBeauty = 0;
    while (l <= r) {
      int mid = (l + r) / 2;
      if (items[mid][0] > targetPrice) {
        r = mid - 1;
      } else {
        // Found viable price. Keep moving to right
        maxBeauty = Math.max(maxBeauty, items[mid][1]);
        l = mid + 1;
      }
    }
    return maxBeauty;
  }

  /**
   * Approach Two: This should perform better for long `items` list, but
   * performs slightly worse than the method above for short `items`.
   */
  public int[] maximumBeautyBetter(int[][] items, int[] queries) {
    int[] ans = new int[queries.length];

    // sort both items and queries in ascending order
    Arrays.sort(items, (a, b) -> a[0] - b[0]);

    int[][] queriesWithIndices = new int[queries.length][2];
    for (int i = 0; i < queries.length; i++) {
      queriesWithIndices[i][0] = queries[i];
      queriesWithIndices[i][1] = i;
    }

    Arrays.sort(queriesWithIndices, (a, b) -> a[0] - b[0]);

    int itemIndex = 0;
    int maxBeauty = 0;

    for (int i = 0; i < queries.length; i++) {
      int query = queriesWithIndices[i][0];
      int originalIndex = queriesWithIndices[i][1];

      while (itemIndex < items.length && items[itemIndex][0] <= query) {
        maxBeauty = Math.max(maxBeauty, items[itemIndex][1]);
        itemIndex++;
      }

      ans[originalIndex] = maxBeauty;
    }

    return ans;
  }
}
