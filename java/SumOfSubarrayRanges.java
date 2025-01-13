// https://leetcode.com/problems/sum-of-subarray-ranges/

import java.util.Stack;

/**
 * **Sum Of Subarray Ranges**:
 *
 * You are given an integer array `nums`. The **range** of a subarray of `nums`
 * is the difference between the largest and smallest element in the subarray.
 *
 * Return _the **sum of all** subarray ranges of `nums`_.
 *
 * A subarray is a contiguous **non-empty** sequence of elements within an
 * array.
 */

public class SumOfSubarrayRanges {
  public long subArrayRanges(int[] nums) {
    int n = nums.length;

    // Arrays to store Previous Smaller Element (PSE) and Next Smaller Element (NSE)
    int[] PSE = new int[n];
    int[] NSE = new int[n];

    // Arrays to store Previous Greater Element (PGE) and Next Greater Element (NGE)
    int[] PGE = new int[n];
    int[] NGE = new int[n];

    Stack<Integer> stack = new Stack<>();

    // Calculate Previous Smaller Element (PSE) for each element
    for (int i = 0; i < n; i++) {
      // Maintain a monotonic increasing stack to find smaller elements
      while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        stack.pop();
      }

      // If stack is empty, no smaller element on the left; otherwise, use top of
      // stack
      PSE[i] = stack.isEmpty() ? -1 : stack.peek();

      stack.push(i); // Push current index onto stack
    }

    stack.clear(); // Clear stack for the next computation

    // Calculate Next Smaller Element (NSE) for each element
    for (int i = n - 1; i >= 0; i--) {
      // Maintain a monotonic increasing stack to find smaller elements
      while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
      }

      // If stack is empty, no smaller element on the right; otherwise, use top of
      // stack
      NSE[i] = stack.isEmpty() ? n : stack.peek();

      stack.push(i); // Push current index onto stack
    }

    stack.clear(); // Clear stack for the next computation

    // Calculate Previous Greater Element (PGE) for each element
    for (int i = 0; i < n; i++) {
      // Maintain a monotonic decreasing stack to find greater elements
      while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
        stack.pop();
      }

      // If stack is empty, no greater element on the left; otherwise, use top of
      // stack
      PGE[i] = stack.isEmpty() ? -1 : stack.peek();

      stack.push(i); // Push current index onto stack
    }

    stack.clear(); // Clear stack for the next computation

    // Calculate Next Greater Element (NGE) for each element
    for (int i = n - 1; i >= 0; i--) {
      // Maintain a monotonic decreasing stack to find greater elements
      while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
        stack.pop();
      }

      // If stack is empty, no greater element on the right; otherwise, use top of
      // stack
      NGE[i] = stack.isEmpty() ? n : stack.peek();

      stack.push(i); // Push current index onto stack
    }

    // Variables to store total contributions of elements as minimums and maximums
    long minContributions = 0;
    long maxContributions = 0;

    // Compute contributions for each element as a minimum or maximum
    for (int i = 0; i < n; i++) {
      // Contribution as a minimum: calculate the number of subarrays where nums[i] is
      // the minimum
      long leftSmaller = i - PSE[i]; // Distance to the nearest smaller element on the left
      long rightSmaller = NSE[i] - i; // Distance to the nearest smaller element on the right
      minContributions += nums[i] * leftSmaller * rightSmaller;

      // Contribution as a maximum: calculate the number of subarrays where nums[i] is
      // the maximum
      long leftGreater = i - PGE[i]; // Distance to the nearest greater element on the left
      long rightGreater = NGE[i] - i; // Distance to the nearest greater element on the right
      maxContributions += nums[i] * leftGreater * rightGreater;
    }

    // The difference between maximum and minimum contributions gives the result
    return maxContributions - minContributions;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/sum-of-subarray-ranges/editorial)
   */
  public long subArrayRangesII(int[] nums) {
    int n = nums.length;
    long answer = 0;
    Stack<Integer> stack = new Stack<>();

    // Find the sum of all the minimum.
    for (int right = 0; right <= n; ++right) {
      while (!stack.isEmpty() && (right == n || nums[stack.peek()] >= nums[right])) {
        int mid = stack.pop();
        int left = stack.isEmpty() ? -1 : stack.peek();
        answer -= (long) nums[mid] * (right - mid) * (mid - left);
      }
      stack.add(right);
    }

    // Find the sum of all the maximum.
    stack.clear();
    for (int right = 0; right <= n; ++right) {
      while (!stack.isEmpty() && (right == n || nums[stack.peek()] <= nums[right])) {
        int mid = stack.pop();
        int left = stack.isEmpty() ? -1 : stack.peek();
        answer += (long) nums[mid] * (right - mid) * (mid - left);
      }
      stack.add(right);
    }
    return answer;
  }
}
