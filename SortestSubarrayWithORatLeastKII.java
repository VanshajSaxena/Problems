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
 *
 * **Comments were added with AI**.
 */

public class SortestSubarrayWithORatLeastKII {

  /**
   * <a href=
   * "https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/editorial/">LeetCode
   * Editorial</a>
   * Finds the length of the shortest subarray with bitwise OR at least k.
   * This implementation uses a sliding window and bitwise manipulation.
   *
   * @param nums the array of integers
   * @param k    the target value for the bitwise OR
   * @return the length of the shortest subarray with OR at least k, or -1 if no
   *         such subarray exists.
   *
   */
  public int minimumSubarrayLength(int[] nums, int k) {
    int minLength = Integer.MAX_VALUE; // To track the minimum length of subarray found
    int windowStart = 0; // Left pointer of the sliding window
    int windowEnd = 0; // Right pointer of the sliding window
    int[] bitCounts = new int[32]; // Array to store the bit count for each bit position (32 bits for integers)

    // Iterate with the right pointer (windowEnd) to expand the window
    while (windowEnd < nums.length) {
      // Update the bit counts when adding nums[windowEnd] into the window
      updateBitCounts(bitCounts, nums[windowEnd], 1);

      // Check if the bitwise OR of the current window is at least k
      // The OR of the window is represented by the bitCounts array
      while (windowStart <= windowEnd && convertBitCountToNumber(bitCounts) >= k) {
        // Update the minimum length of the subarray if the current window is valid
        minLength = Math.min(minLength, windowEnd - windowStart + 1);

        // Shrink the window from the left by moving the windowStart pointer
        // and updating the bit counts accordingly
        updateBitCounts(bitCounts, nums[windowStart], -1);
        windowStart++;
      }

      // Expand the window by moving the windowEnd pointer
      windowEnd++;
    }

    // If no valid subarray is found, return -1, otherwise return the minimum length
    return minLength == Integer.MAX_VALUE ? -1 : minLength;
  }

  /**
   * Updates the bit counts based on whether a bit is set in the number.
   *
   * @param bitCount the bit count array (of size 32)
   * @param num      the number to update the bit count for
   * @param delta    the change in the bit count (1 for adding, -1 for removing)
   */
  private void updateBitCounts(int[] bitCount, int num, int delta) {
    // Loop through all 32 bit positions and update the bit count
    for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
      // If the current bit is set in num, update the corresponding bitCount value
      if (((num >> bitPosition) & 1) != 0) {
        bitCount[bitPosition] += delta;
      }
    }
  }

  /**
   * Converts the bit counts into the corresponding number represented by these
   * bits.
   *
   * @param bitCount the array of bit counts
   * @return the number represented by the bit counts
   */
  private int convertBitCountToNumber(int[] bitCount) {
    int result = 0;

    // Iterate through the bit count array to reconstruct the number
    for (int bitPosition = 0; bitPosition < bitCount.length; bitPosition++) {
      // If the bit count for this position is non-zero, set the bit in the result
      // number
      if (bitCount[bitPosition] != 0) {
        result |= 1 << bitPosition;
      }
    }

    return result;
  }
}
