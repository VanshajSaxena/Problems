// https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/description/

/**
 * **Minimum Limit Of Balls In A Bag**:
 *
 * You are given an integer array `nums` where the `ith` bag contains `nums[i]`
 * balls. You are also given an integer `maxOperations`.
 *
 * You can perform the following operation at most `maxOperations` times:
 *
 * - Take any bag of balls and divide it into two new bags with a **positive**
 * number of balls.
 * -- For example, a bag of `5` balls can become two new bags of `1` and `4`
 * balls, or two new bags of `2` and `3` balls.
 *
 * Your penalty is the **maximum** number of balls in a bag. You want to
 * **minimize** your penalty after the operations.
 *
 * Return _the minimum possible penalty after performing the operations_.
 */

class Solution {

  public int minimumSize(int[] nums, int maxOperations) {
    int left = 1;
    int right = 0;

    for (int num : nums) {
      right = Math.max(right, num);
    }

    while (left < right) {
      int middle = (left + right) / 2;

      if (isPossible(middle, nums, maxOperations)) {
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    return left;
  }

  private boolean isPossible(
      int maxBallsInBag,
      int[] nums,
      int maxOperations) {

    int totalOperations = 0;

    for (int num : nums) {
      int operations = (int) Math.ceil(num / (double) maxBallsInBag) - 1;
      totalOperations += operations;

      if (totalOperations > maxOperations) {
        return false;
      }
    }

    return true;
  }
}
