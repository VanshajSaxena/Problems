// https://leetcode.com/problems/count-number-of-nice-subarrays/description/

/**
 * **Count Number Of Nice Subarrays**:
 *
 * Given an array of integers `nums` and an integer `k`. A continuous subarray
 * is called nice if there are `k` odd numbers on it.
 *
 * Return _the number of **nice** sub-arrays_.
 */

public class CountNumberOfNiceSubarrays {

  public int numberOfSubarrays(int[] nums, int k) {
    return niceAtMax(nums, k) - niceAtMax(nums, k - 1);
  }

  private int niceAtMax(int[] nums, int k) {
    int start = 0, niceCount = 0, end;

    for (end = 0; end < nums.length; end++) {
      if ((nums[end] & 1) == 1) {
        k--;
      }

      while (start <= end && k < 0) {
        if ((nums[start] & 1) == 1) {
          k++;
        }
        start++;
      }

      niceCount += (end - start + 1);
    }

    return niceCount;
  }
}
