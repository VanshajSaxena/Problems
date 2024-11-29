// https://leetcode.com/problems/sum-of-subarray-minimums/description/

import java.util.Stack;

/**
 * **Sum Of Subarray Minimums**:
 *
 * Given an array of integers arr, find the sum of `min(b)`, where `b` ranges
 * over every (contiguous) subarray of `arr`. Since the answer may be large,
 * return the answer **modulo** `10^9 + 7`.
 */

public class SumOfSubarrayMinimums {

  public int sumSubarrayMins(int[] arr) {
    int n = arr.length;
    long sum = 0;
    final int MOD = 1000000007;

    // Stack to find Previous Smaller Element (PSE)
    Stack<Integer> stack = new Stack<>();

    int[] PSE = new int[n]; // Stores indices of the previous smaller element for each index
    int[] NSE = new int[n]; // Stores indices of the next smaller element for each index

    // Compute PSE for each element
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        stack.pop(); // Maintain increasing order in the stack
      }
      PSE[i] = stack.isEmpty() ? -1 : stack.peek(); // If no smaller element, set -1
      stack.push(i); // Push the current element's index
    }

    // Stack to find Next Smaller Element (NSE)
    stack.clear(); // Clear stack for next use

    // Compute NSE for each element
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
        stack.pop(); // Maintain increasing order in the stack
      }
      NSE[i] = stack.isEmpty() ? n : stack.peek(); // If no smaller element, set n (out of bounds)
      stack.push(i); // Push the current element's index
    }

    // Calculate the sum of subarray minimums
    for (int i = 0; i < n; i++) {
      long leftCount = i - PSE[i]; // Subarrays starting from PSE[i] to i
      long rightCount = NSE[i] - i; // Subarrays ending from i to NSE[i]
      sum += arr[i] * leftCount * rightCount; // Contribution of A[i] to the sum
      sum %= MOD; // To avoid overflow
    }

    return (int) sum; // Final result, cast to int since the sum might be large
  }
}
