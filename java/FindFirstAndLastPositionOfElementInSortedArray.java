// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

/**
 * **Find First And Last Position Of Element In Sorted Array**:
 *
 * Given an array of integers `nums` sorted in non-decreasing order, find the
 * starting and ending position of a given `target` value.
 *
 * If `target` is not found in the array, return `[-1, -1]`.
 *
 * You must write an algorithm with `O(log n)` runtime complexity.
 */

public class FindFirstAndLastPositionOfElementInSortedArray {
  // The below function searches for the `leftMost` and the `rightMost` `target`
  // within a single loop, although it could have been split into two loops, it is
  // done just for style preferences.
  public int[] searchRange(int[] nums, int target) {
    int length = nums.length;
    int highOne = length - 1, highTwo = length - 1;
    int lowOne = 0, lowTwo = 0;
    int leftMost = -1;
    int rightMost = -1;
    while (highOne >= lowOne || highTwo >= lowTwo) {
      if (highOne >= lowOne) {
        int midOne = lowOne + (highOne - lowOne) / 2;

        if (nums[midOne] == target) {
          rightMost = midOne;
          lowOne = midOne + 1;
        } else if (nums[midOne] > target) {
          highOne = midOne - 1;
        } else {
          lowOne = midOne + 1;
        }
      }
      if (highTwo >= lowTwo) {
        int midTwo = lowTwo + (highTwo - lowTwo) / 2;

        if (nums[midTwo] == target) {
          leftMost = midTwo;
          highTwo = midTwo - 1;
        } else if (nums[midTwo] < target) {
          lowTwo = midTwo + 1;
        } else {
          highTwo = midTwo - 1;
        }
      }
    }
    return new int[] { leftMost, rightMost };
  }
}
