// https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/description/

import java.util.Arrays;

public class MaximumNumberOfDistinctElementsAfterOperations {
  public int maxDistinctElements(int[] nums, int k) {
    int minValue = Arrays.stream(nums).min().getAsInt();
    int maxValue = Arrays.stream(nums).max().getAsInt();
    int range = maxValue - minValue + 1;
    int[] bucket = new int[range];

    for (int num : nums) {
      bucket[num - minValue]++;
    }

    int distinctCount = 0;
    int lastUsed = Integer.MIN_VALUE;

    // Within the outer loop, the inner `while` loop processes each bucket until it
    // is empty. For each value, the code calculates the starting point `start` for
    // the current element. This starting point is determined by taking the maximum
    // of `num - k` and `lastUsed + 1`, ensuring that the new value is within the
    // allowed range and greater than the last used value.

    // If the calculated `start` is within the valid range (`start <= num + k`), the
    // code updates `lastUsed` to `start`, increments the `distinctCount`, and
    // decrements the count in the `bucket`. This ensures that the element is
    // counted as distinct and the bucket is processed correctly. If the `start`
    // value is not within the valid range, the inner loop breaks, and the code
    // moves on to the next value in the outer loop.
    for (int i = 0; i < range; i++) {
      while (bucket[i] > 0) {
        int num = i + minValue;
        int start = Math.max(num - k, lastUsed + 1);
        if (start <= num + k) {
          lastUsed = start;
          distinctCount++;
          bucket[i]--;
        } else {
          break;
        }
      }
    }

    return distinctCount;
  }
}
