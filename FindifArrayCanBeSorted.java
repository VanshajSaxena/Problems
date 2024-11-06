// https://leetcode.com/problems/find-if-array-can-be-sorted/description/

import java.util.ArrayList;
import java.util.Arrays;

/**
 * **Find if Array Can Be Sorted**:
 *
 * You are given a **0-indexed** array of **positive** integers nums.
 *
 * In one **operation**, you can swap any two **adjacent** elements if they have
 * the **same** number of set bits. You are allowed to do this operation **any**
 * number of times (**including zero**).
 *
 * Return _`true` if you can sort the array, else return `false`_.
 */

public class FindifArrayCanBeSorted {

  /**
   * Novice Method:
   * Complexity:
   * - Time: O(n)
   * - Space: O(n)
   */
  public boolean canSortArray(int[] nums) {
    ArrayList<Integer> partitions = new ArrayList<>();

    int pMin = nums[0];
    int pMax = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (Integer.bitCount(nums[i - 1]) == Integer.bitCount(nums[i])) {
        if (nums[i] < pMin) {
          pMin = nums[i];
        }
        if (nums[i] > pMax) {
          pMax = nums[i];
        }
      } else {
        partitions.add(pMin);
        partitions.add(pMax);
        pMax = nums[i];
        pMin = nums[i];
      }
    }

    partitions.add(pMin);
    partitions.add(pMax);

    for (int i = 1; i < partitions.size(); i++) {
      if (partitions.get(i - 1) > partitions.get(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Better Approach:
   * This would result in better performance, because of its early return nature.
   */
  public boolean canSortArrayBetter(int[] nums) {
    int n = nums.length;

    // Copy the original array to values
    int[] values = Arrays.copyOf(nums, n);

    // First Pass: Iterate from left to right
    // Goal: Move the maximum value of each segment as far right as possible
    for (int i = 0; i < n - 1; i++) {
      if (values[i] <= values[i + 1])
        continue;
      else {
        // Count the number of set bits using Integer.bitCount
        if (Integer.bitCount(values[i]) == Integer.bitCount(values[i + 1])) {
          // Swap them if they have the same number of set bits
          int temp = values[i];
          values[i] = values[i + 1];
          values[i + 1] = temp;
        } else
          return false; // Return false if they cannot be swapped
      }
    }

    // Second Pass: Iterate from right to left
    // Goal: Move the minimum value of each segment as far left as possible
    for (int i = n - 1; i >= 1; i--) {
      if (values[i] >= values[i - 1])
        continue;
      else {
        // Count the number of set bits using Integer.bitCount
        if (Integer.bitCount(values[i]) == Integer.bitCount(values[i - 1])) {
          // Swap them if they have the same number of set bits
          int temp = values[i];
          values[i] = values[i - 1];
          values[i - 1] = temp;
        } else
          return false; // Return false if they cannot be swapped
      }
    }

    // If both passes complete without returning false, the array can be sorted
    return true;
  }
}
