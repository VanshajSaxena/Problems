// https://leetcode.com/problems/rearrange-array-elements-by-sign/description/

/**
 * **Rearrange Array Elements By Sign**:
 *
 * You are given a **0-indexed** integer array `nums` of even length consisting
 * of an equal number of positive and negative integers.
 *
 * You should return the array of nums such that the the array follows the given
 * conditions:
 *
 * 1. Every **consecutive pair** of integers have **opposite signs**.
 * 2. For all integers with the same sign, the **order** in which they were
 * present in `nums` is **preserved**.
 * 3. The rearranged array begins with a positive integer.
 *
 * Return the _modified array after rearranging the elements to satisfy the
 * aforementioned conditions_.
 */

public class RearrangeArrayElementsBySign {
  // The below solution first creates two arrays for storing all the positives and
  // negatives, by iterating over the given array once.
  // Then, remerges the two arrays so that they satisfy the given conditions.
  public int[] rearrangeArray(int[] nums) {
    int n = 0, p = 0;
    int[] negatives = new int[nums.length / 2];
    int[] positives = new int[nums.length / 2];

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < 0) {
        negatives[n++] = nums[i];
      } else if (nums[i] >= 0) {
        positives[p++] = nums[i];
      }
    }
    n = 0;
    p = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i % 2 == 0) {
        nums[i] = positives[p++];
      } else {
        nums[i] = negatives[n++];
      }
    }
    return nums;
  }

  // Better approach: The below approach trys to place the elements of the `nums`
  // array directly to the `ans` array by keeping the index of `p` at `0` and `n`
  // at `1` and incrementing them by 2 whenever a positive or a negative element
  // is place in `ans` array respectively.
  public int[] rearrangeArrayTwo(int[] nums) {
    int[] ans = new int[nums.length];

    int p = 0;
    int n = 1;

    for (int num : nums) {
      if (num >= 0) {
        ans[p] = num;
        p += 2;
      } else {
        ans[n] = num;
        n += 2;
      }
    }
    return ans;
  }
}
