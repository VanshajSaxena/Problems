// https://leetcode.com/problems/longest-consecutive-sequence/description/

import java.util.HashSet;

/**
 * **Longest Consecutive Sequence**:
 *
 * Given an unsorted array of integers `nums`, return _the length of the longest
 * consecutive elements sequence._
 *
 * You must write an algorithm that runs in `O(n)` time.
 */

public class LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    // Add all the elements to a set.
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }

    // For each member `integer` in `set` if `integer + 1` does not exists run a
    // loop checking for all the consecutive elements that are in `set`. Return the
    // longest consecutive sequence.
    int longest = 1;
    for (Integer integer : set) {
      if (!(set.contains(integer - 1))) {
        int count = 1;
        int next = integer;

        while (set.contains(next + 1)) {
          next++;
          count++;
        }
        longest = Math.max(longest, count);
      }
    }
    return longest;
  }
}
