// https://leetcode.com/problems/continuous-subarrays/description/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * **Continuous Subarrays**:
 *
 * You are given a **0-indexed** integer array `nums`. A subarray of `nums` is
 * called **continuous** if:
 *
 * - Let `i`, `i + 1`, ..., `j` be the indices in the subarray. Then, for each
 * pair of indices `i <= i1, i2 <= j`, `0 <= |nums[i1] - nums[i2]| <= 2`.
 *
 * Return _the total number of **continuous** subarrays_.
 *
 * A subarray is a contiguous **non-empty** sequence of elements within an
 * array.
 */

public class ContinuousSubarrays {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/continuous-subarrays/editorial/#approach-1-sorted-map)
   */
  public long continuousSubarraysI(int[] nums) {
    long count = 0;
    int left = 0, right = 0;
    // sorted map [element(nums[ left | right]) : frequency)]
    TreeMap<Integer, Integer> freq = new TreeMap<>();

    while (right < nums.length) {
      freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

      while (left < right && freq.lastKey() - freq.firstKey() > 2) {
        freq.put(nums[left], freq.get(nums[left]) - 1);
        if (freq.get(nums[left]) == 0) {
          freq.remove(nums[left]);
        }
        left++;
      }

      count += right - left + 1;
      right++;
    }

    return count;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/continuous-subarrays/editorial/#approach-2-priority-queue)
   */
  public long continuousSubarraysII(int[] nums) {
    long count = 0;
    int left = 0, right = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(nums[a], nums[b]));
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(nums[b], nums[a]));

    while (right < nums.length) {
      maxHeap.add(right);
      minHeap.add(right);
      while (left < right && nums[maxHeap.peek()] - nums[minHeap.peek()] > 2) {
        left++;

        // Remove indices that are outside the window.
        while (!maxHeap.isEmpty() && maxHeap.peek() < left) {
          maxHeap.poll();
        }
        while (!minHeap.isEmpty() && minHeap.peek() < left) {
          minHeap.poll();
        }
      }

      count += right - left + 1;
      right++;
    }
    return count;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/continuous-subarrays/editorial/#approach-3-monotonic-deque)
   */
  // REVISION: This needs revision.
  public long continuousSubarraysIII(int[] nums) {
    Deque<Integer> maxQue = new ArrayDeque<>();
    Deque<Integer> minQue = new ArrayDeque<>();

    int left = 0;
    long count = 0;

    for (int right = 0; right < nums.length; right++) {

      while (!maxQue.isEmpty() && nums[maxQue.peekLast()] < nums[right]) {
        maxQue.pollLast();
      }
      maxQue.offer(right);

      while (!minQue.isEmpty() && nums[minQue.peekLast()] > nums[right]) {
        minQue.pollLast();
      }
      minQue.offer(right);

      while (!maxQue.isEmpty() && !minQue.isEmpty() && nums[maxQue.peekFirst()] - nums[minQue.peekFirst()] > 2) {
        if (maxQue.peekFirst() < minQue.peekFirst()) {
          left = maxQue.peekFirst() + 1;
          maxQue.pollFirst();
        } else {
          left = minQue.peekFirst() + 1;
          minQue.pollFirst();
        }
      }

      count += right - left + 1;
    }

    return count;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/continuous-subarrays/editorial/#approach-4-optimized-two-pointer)
   */
  // MARK: Pending.
  public long continuousSubarraysIV(int[] nums) {
    return 0;
  }
}
