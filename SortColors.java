// https://leetcode.com/problems/sort-colors/description/

/**
 * **Sort an array of 0s, 1s and 2s**:
 *
 * Given an array `nums` with `n` objects colored red, white, or blue, sort them
 * **in-place** so that objects of the same color are adjacent, with the
 * colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and
 * blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 */

public class SortColors {
  public void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;
    while (mid <= high) {
      switch (nums[mid]) {
        case 0 -> { // swap low and mid increment both
          int temp = nums[low];
          nums[low] = nums[mid];
          nums[mid] = temp;
          low++;
          mid++;
        }
        case 1 -> // increment mid
          mid++;
        case 2 -> { // swap high and mid decrement high
          int temp2 = nums[high];
          nums[high] = nums[mid];
          nums[mid] = temp2;
          high--;
        }
      }
    }
  }
}
