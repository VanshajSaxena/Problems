// https://leetcode.com/problems/check-if-n-and-its-double-exist/

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * **Check If N And Its Double Exist**:
 *
 * Given an array arr of integers, check if there exist two indices i and j such
 * that :
 *
 * - `i != j`
 * - `0 <= i, j < arr.length`
 * - `arr[i] == 2 * arr[j]`
 */

public class CheckIfNAndItsDoubleExist {
  /**
   * Brute Force
   */
  public boolean checkIfNAndItsDoubleExist(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length; j++) {
        if (i != j && (arr[i] == 2 * arr[j])) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/check-if-n-and-its-double-exist/editorial)
   */
  public boolean checkIfExistUsingHashSet(int[] arr) {
    Set<Integer> seen = new HashSet<>();

    for (int num : arr) {
      // Check if 2 * num or num / 2 exists in the set
      if (seen.contains(2 * num) ||
          (num % 2 == 0 && seen.contains(num / 2))) {
        return true;
      }
      // Add the current number to the set
      seen.add(num);
    }
    // No valid pair found
    return false;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/check-if-n-and-its-double-exist/editorial)
   */
  public boolean checkIfExistUsingBinarySearch(int[] arr) {
    // Step 1: Sort the array
    Arrays.sort(arr);

    for (int i = 0; i < arr.length; i++) {
      // Step 2: Calculate the target (double of current number)
      int target = 2 * arr[i];
      // Step 3: Custom binary search for the target
      int index = customBinarySearch(arr, target);
      // If the target exists and is not the same index
      if (index >= 0 && index != i) {
        return true;
      }
    }
    // No valid pair found
    return false;
  }

  // Implementation of binary search
  private int customBinarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      // Avoid potential overflow
      int mid = left + (right - left) / 2;

      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return -1; // Target not found
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/check-if-n-and-its-double-exist/editorial)
   */
  public boolean checkIfExistUsingHashMap(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int num : arr) {
      // Count occurrences of each number
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    for (int num : arr) {
      // Check for double
      if (num != 0 && map.containsKey(2 * num)) {
        return true;
      }
      // Handle zero case (ensure there are at least two zeros)
      if (num == 0 && map.get(num) > 1) {
        return true;
      }
    }
    return false;
  }
}
