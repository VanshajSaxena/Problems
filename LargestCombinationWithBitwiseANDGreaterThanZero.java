
/**
 * **Largest Combination With Bitwise AND Greater Than Zero**:
 *
 * The **bitwise AND** of an array `nums` is the bitwise AND of all integers in
 * `nums`.
 *
 * - For example, for `nums = [1, 5, 3]`, the bitwise AND is equal to `1 & 5 & 3
 * = 1`.
 * - Also, for `nums = [7]`, the bitwise AND is `7`.
 *
 * You are given an array of positive integers `candidates`. Evaluate the
 * **bitwise AND** of every combination of numbers of `candidates`. Each number
 * in `candidates` may only be used **once** in each combination.
 *
 * Return _the size of the largest combination of `candidates` with a bitwise
 * AND **greater** than `0`_.
 */

public class LargestCombinationWithBitwiseANDGreaterThanZero {
  public int largestCombination(int[] candidates) {
    int[] bitCount = new int[32]; // Counts of 1s in each bit position

    // Count bits in each position for all numbers
    for (int num : candidates) {
      for (int i = 0; i < 32; i++) {
        if ((num & (1 << i)) != 0) { // Check if the i-th bit is 1
          bitCount[i]++;
        }
      }
    }

    // Find the maximum count in bitCount, which gives the largest combination
    int maxCombinationSize = 0;
    for (int count : bitCount) {
      maxCombinationSize = Math.max(maxCombinationSize, count);
    }

    return maxCombinationSize;
  }
}
