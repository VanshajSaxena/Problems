// https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/description/

import java.util.HashSet;

/**
 * **Maximum Number Of Integers To Choose From A Range I**:
 *
 * You are given an integer array `banned` and two integers `n` and `maxSum`.
 * You are choosing some number of integers following the below rules:
 *
 * - The chosen integers have to be in the range `[1, n]`.
 * - Each integer can be chosen **at most once**.
 * - The chosen integers should not be in the array `banned`.
 * - The sum of the chosen integers should not exceed `maxSum`.
 *
 * Return _the **maximum** number of integers you can choose following the
 * mentioned rules_.
 */

public class MaximumNumberOfIntegersToChooseFromARangeI {

  public int maxCount(int[] banned, int n, int maxSum) {
    // Store banned numbers in HashSet
    HashSet<Integer> bannedSet = new HashSet<>();
    for (int num : banned) {
      bannedSet.add(num);
    }
    // Track count of valid numbers we can choose
    int count = 0;
    // Try each number from 1 to n
    for (int num = 1; num <= n; num++) {
      // Skip banned numbers
      if (bannedSet.contains(num))
        continue;
      // Return if adding current number exceeds maxSum
      if (maxSum - num < 0)
        return count;
      // Include current number
      maxSum -= num;
      count++;
    }
    return count;
  }
}
