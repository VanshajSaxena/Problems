// https://leetcode.com/problems/kth-largest-element-in-an-array/description/

import java.util.PriorityQueue;

/**
 * **Kth Largest Element In An Array**
 *
 * Given an integer array `nums` and an integer `k`, return the `kth` largest
 * element in the array.
 *
 * Note that it is the `kth` largest element in the sorted order, not the `kth`
 * distinct element.
 *
 * Can you solve it without sorting?
 */

public class KthLargestElementInAnArray {
  public int findKthLargestI(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int i = 0; i < nums.length; i++) {
      minHeap.add(nums[i]);
      if (minHeap.size() > k) {
        minHeap.remove();
      }
    }

    return minHeap.peek();
  }

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/kth-largest-element-in-an-array/solutions/60312/ac-clean-quickselect-java-solution-avg-o-n-time)
   */
  public int findKthLargestII(int[] nums, int k) {
    return quickSelect(nums, k, 0, nums.length - 1);
  }

  public int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, 0, nums.length - 1, k);
  }

  int quickSelect(int[] nums, int low, int high, int k) {
    int pivot = low;

    // use quick sort's idea
    // put nums that are <= pivot to the left
    // put nums that are > pivot to the right
    for (int j = low; j < high; j++) {
      if (nums[j] <= nums[high]) {
        swap(nums, pivot++, j);
      }
    }
    swap(nums, pivot, high);

    // count the nums that are > pivot from high
    int count = high - pivot + 1;
    // pivot is the one!
    if (count == k)
      return nums[pivot];
    // pivot is too small, so it must be on the right
    if (count > k)
      return quickSelect(nums, pivot + 1, high, k);
    // pivot is too big, so it must be on the left
    return quickSelect(nums, low, pivot - 1, k - count);
  }

  private void swap(int[] nums, int pivot, int start) {
    int temp = nums[pivot];
    nums[pivot] = nums[start];
    nums[start] = temp;
  }
}
