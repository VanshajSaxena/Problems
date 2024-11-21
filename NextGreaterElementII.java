// https://leetcode.com/problems/next-greater-element-ii/description/

import java.util.Arrays;
import java.util.Stack;

/**
 * **Next Greater Element II**:
 *
 * Given a circular integer array `nums` (i.e., the next element of
 * `nums[nums.length - 1]` is `nums[0]`), return _the **next greater number**
 * for every element in `nums`_.
 *
 * The **next greater number** of a number `x` is the first greater number to
 * its traversing-order next in the array, which means you could search
 * circularly to find its next greater number. If it doesn't exist, return `-1`
 * for this number.
 */

public class NextGreaterElementII {
  public int[] nextGreaterElementsII(int[] nums) {
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[nums.length];
    Arrays.fill(result, -1);

    for (int i = 0; i < n * 2; i++) {
      int currentIndex = i % n;

      while (!stack.isEmpty() && nums[stack.peek()] < nums[currentIndex]) {
        result[stack.pop()] = nums[currentIndex];
      }

      if (i < n) {
        stack.push(currentIndex);
      }

    }

    return result;
  }
}
