// https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/

/**
 * **Sortest Subarray With OR at Least K II**:
 *
 * You are given an array `nums` of **non-negative** integers and an integer
 * `k`.
 *
 * An array is called **special** if the bitwise `OR` of all of its elements is
 * **at least** k.
 *
 * Return _the length of the **shortest special non-empty** subarray of `nums`,
 * or return `-1` if no special subarray exists_.
 */

public class SortestSubarrayWithORatLeastKII {
  /**
   * <a href=
   * "https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/editorial/">LeetCode
   * Editorial</a>
   */
  public int minimumSubarrayLength(int[] nums, int k) {
    int minLenght = Integer.MAX_VALUE;
    int windowStart = 0;
    int windowEnd = 0;
    int[] bitCounts = new int[32];

    while (windowEnd < nums.length) {
      updateBitCounts(bitCounts, nums[windowEnd], 1);
      while (windowStart <= windowEnd && convertBitCountToNumber(bitCounts) >= k) {
        minLenght = Math.min(minLenght, windowEnd - windowStart + 1);
        updateBitCounts(bitCounts, nums[windowStart], -1);
        windowStart++;
      }
      windowEnd++;
    }
    return minLenght == Integer.MAX_VALUE ? -1 : minLenght;
  }

  private void updateBitCounts(int[] bitCount, int num, int delta) {
    for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
      if (((num >> bitPosition) & 1) != 0) {
        bitCount[bitPosition] += delta;
      }
    }
  }

  private int convertBitCountToNumber(int[] bitCount) {
    int result = 0;
    for (int bitPosition = 0; bitPosition < bitCount.length; bitPosition++) {
      if (bitCount[bitPosition] != 0) {
        result |= 1 << bitPosition;
      }
    }
    return result;
  }
}
