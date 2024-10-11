// https://leetcode.com/problems/single-element-in-a-sorted-array/description/

/**
 * **Single Element In A Sorted Array**:
 *
 * You are given a sorted array consisting of only integers where every element
 * appears exactly twice, except for one element which appears exactly once.
 *
 * Return _the single element that appears only once_.
 *
 * Your solution must run in `O(log n)` time and `O(1)` space.
 *
 */

public class SingleElementInASortedArray {
  public int singleNonDuplicate(int[] nums) {
    int length = nums.length;
    int low = 0, high = length - 1;

    if (nums.length == 1) { // Edge case when the length of array is `1`.
      return nums[0];
    } else if (nums[0] != nums[1]) { // Boundary check to early return if the unique element is the first element.
      return nums[0];
    } else if (nums[length - 1] != nums[length - 2]) { // Check to return last element is unique.
      return nums[length - 1];
    }

    low++; // increment `low` as its already checked.
    high--; // decrement `high` as its already checked.
    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid - 1] != nums[mid] && nums[mid] != nums[mid + 1]) { // This checks if `mid` is unique, if yes, return;
        return nums[mid];
      } else if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || (mid % 2 == 1 && nums[mid] == nums[mid - 1])) { // left
        low = mid + 1;
      } else { // right half
        high = mid - 1;
      }
    }
    return -1;
  }
}
