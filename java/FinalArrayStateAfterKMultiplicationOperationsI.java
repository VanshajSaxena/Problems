import java.util.PriorityQueue;

/**
 * **Final Array State After K Multiplication Operations I**:
 *
 * You are given an integer array `nums`, an integer `k`, and an integer
 * `multiplier`.
 *
 * You need to perform `k` operations on `nums`. In each operation:
 *
 * - Find the **minimum** value `x` in `nums`. If there are multiple occurrences
 * of the minimum value, select the one that appears first.
 * - Replace the selected minimum value `x` with `x * multiplier`.
 *
 * Return an integer array denoting the _final state of `nums` after performing
 * all `k` operations_.
 */

public class FinalArrayStateAfterKMultiplicationOperationsI {
  public int[] getFinalStateI(int[] nums, int k, int multiplier) {
    while (k-- > 0) {
      int minIdx = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] < nums[minIdx]) {
          minIdx = i;
        }
      }
      nums[minIdx] *= multiplier;
    }
    return nums;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/final-array-state-after-k-multiplication-operations-i/editorial/#approach-2-heap-optimized-k-minimum-value-multiplication)
   */
  public int[] getFinalStateII(int[] nums, int k, int multiplier) {
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
      int valueComparison = Integer.compare(a[0], b[0]);
      if (valueComparison == 0) {
        return Integer.compare(a[1], b[1]);
      }
      return valueComparison;
    });

    for (int i = 0; i < nums.length; i++) {
      heap.offer(new int[] { nums[i], i });
    }

    while (k-- > 0) {
      int[] smallest = heap.poll();
      int index = smallest[1];

      nums[index] *= multiplier;
      heap.offer(new int[] { nums[index], index });
    }

    return nums;
  }
}
