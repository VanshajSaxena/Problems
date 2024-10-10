// https://leetcode.com/problems/search-in-rotated-sorted-array/description/

/**
 * **Sorted In Rotated Sorted Array**:
 *
 * There is an integer array `nums` sorted in ascending order (with **distinct
 * values**).
 *
 * Prior to being passed to your function, `nums` is possibly rotated at an
 * unknown pivot index `k` (`1 <= k < nums.length`) such that the resulting
 * array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ...,
 * nums[k-1]]` (**0-indexed**). For example, `[0,1,2,4,5,6,7]` might be rotated
 * at pivot index `3` and become `[4,5,6,7,0,1,2]`.
 *
 * Given the array `nums` after the possible rotation and an integer target,
 * return the index of target if it is in `nums`, or -1 if it is not in `nums`.
 *
 * You must write an algorithm with `O(log n)` runtime complexity.
 */

public class SearchInRotatedSortedArray {

  // The below function uses Binary Search to find the target. This checks at each
  // step which section of the array is sorted and reduces the search boundaries
  // accordingly.
  public int search(int[] nums, int target) {
    int length = nums.length;
    int low = 0, high = length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) { // Found the element.
        return mid;
      } else if (nums[low] <= nums[mid]) { // If the left half is sorted.
        if (nums[low] <= target && target <= nums[mid]) { // Target lies in the left range.
          high = mid - 1;
        } else { // This means the right half is sorted.
          low = mid + 1;
        }
      } else { // The right half is sorted.
        if (nums[mid] <= target && target <= nums[high]) { // Target lies in the right range.
          low = mid + 1;
        } else { // This means the left half is sorted.
          high = mid - 1;
        }
      }
    }
    return -1;
  }
}
