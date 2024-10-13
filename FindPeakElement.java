// https://leetcode.com/problems/find-peak-element/description

/**
 * **Find Peak Element**:
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a **0-indexed** integer array `nums`, find a peak element, and return
 * its index. If the array contains multiple peaks, return the index to **any of
 * the peaks**.
 *
 * You may imagine that `nums[-1] = nums[n] = -∞`. In other words, an element is
 * always considered to be strictly greater than a neighbor that is outside the
 * array.
 *
 * You must write an algorithm that runs in `O(log n)` time.
 */

public class FindPeakElement {
  public int findPeakElement(int[] nums) {
    int length = nums.length;
    int low = 0, high = length - 1;

    if (length == 1) { // If only has one element return the index.
      return 0;
    } else if (nums[0] > nums[1]) { // Return first index if its peak.
      return 0;
    } else if (nums[length - 1] > nums[length - 2]) { // Return last element if its peak.
      return length - 1;
    }

    low++; // Increment `low` as it has been checked
    high--; // Decrement `high` as it has been checked
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) { // Return `mid` if its peak
        return mid;
      }
      // Check if `mid` is on an incrementing sequence. That would mean the peak is on
      // the right.
      else if (nums[mid - 1] < nums[mid]) {
        low = mid + 1;
      }
      // Check if `mid` is on a decrementing sequence. That would mean the peak is on
      // the left.
      else if (nums[mid] > nums[mid + 1]) {
        high = mid - 1;
      } else { // This will happen if `mid` is in *vallay*, peak can be on either side.
        low = mid + 1;
      }
    }
    // This should never reach.
    return -1;
  }
}
