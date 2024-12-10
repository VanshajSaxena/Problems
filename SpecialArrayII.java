// https://leetcode.com/problems/special-array-ii/description/

import java.util.ArrayList;

/**
 * **Special Array II**:
 *
 * An array is considered **special** if every pair of its adjacent elements
 * contains two numbers with different parity.
 *
 * You are given an array of integer `nums` and a 2D integer matrix `queries`,
 * where for `queries[i] = [fromi, toi]` your task is to check that subarray
 * `nums[fromi..toi]` is **special** or not.
 *
 * Return an array of booleans `answer` such that `answer[i]` is `true` if
 * `nums[fromi..toi]` is special.
 */

public class SpecialArrayII {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/special-array-ii/editorial)
   */
  public boolean[] isArraySpecialI(int[] nums, int[][] queries) {
    int length = nums.length;
    boolean[] ans = new boolean[queries.length];
    ArrayList<Integer> violatingIndices = new ArrayList<>();

    for (int i = 1; i < length; i++) {
      if (nums[i] % 2 == nums[i - 1] % 2) {
        violatingIndices.add(i);
      }
    }

    for (int i = 0; i < queries.length; i++) {
      int[] query = queries[i];

      int right = query[0];
      int left = query[1];

      boolean foundViolating = binarySearch(right + 1, left, violatingIndices);

      if (foundViolating) {
        ans[i] = false;
      } else {
        ans[i] = true;
      }
    }

    return ans;
  }

  private boolean binarySearch(int start, int end, ArrayList<Integer> violatingIndices) {
    int left = 0;
    int right = violatingIndices.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int violatingIndex = violatingIndices.get(mid);
      if (violatingIndex < start) {
        left = mid + 1;
      } else if (violatingIndex > end) {
        right = mid - 1;
      } else {
        return true;
      }
    }

    return false;
  }

  public boolean[] isArraySpecialII(int[] nums, int[][] queries) {
    int length = nums.length;
    int[] prefix = new int[length];
    boolean[] ans = new boolean[queries.length];
    prefix[0] = 0;

    for (int i = 1; i < length; i++) {
      if (nums[i] % 2 == nums[i - 1] % 2) {
        prefix[i] = prefix[i - 1] + 1;
      } else {
        prefix[i] = prefix[i - 1];
      }
    }

    for (int i = 0; i < ans.length; i++) {
      int[] query = queries[i];
      int start = query[0];
      int end = query[1];

      ans[i] = prefix[end] - prefix[start] == 0;
    }

    return ans;
  }
}
