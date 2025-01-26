// https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/description/

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * **Make Lexicographically Smallest Array By Swapping Elements**:
 *
 * You are given a **0-indexed** array of **positive** integers `nums` and a
 * positive integer `limit`.
 *
 * In one operation, you can choose any two indices `i` and `j` and swap
 * `nums[i]` and `nums[j]` if `|nums[i] - nums[j]| <= limit`.
 *
 * Return _the **lexicographically smallest array** that can be obtained by
 * performing the operation any number of times_.
 *
 * An array `a` is lexicographically smaller than an array `b` if in the first
 * position where `a` and `b` differ, array `a` has an element that is less than
 * the corresponding element in `b`. For example, the array `[2,10,3]` is
 * lexicographically smaller than the array `[10,2,3]` because they differ at
 * index `0` and `2 < 10`.
 */

public class MakeLexicographicallySmallestArrayBySwappingElements {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/editorial)
   */
  public int[] lexicographicallySmallestArray(int[] nums, int limit) {
    int[] numsSorted = nums.clone();
    Arrays.sort(numsSorted);

    int currentGroup = 0;
    Map<Integer, List<Integer>> groupToNums = new HashMap<>();
    Map<Integer, Integer> numsToGroup = new HashMap<>();

    groupToNums.put(currentGroup, new LinkedList<>(Arrays.asList(numsSorted[0])));
    numsToGroup.put(numsSorted[0], currentGroup);

    for (int i = 1; i < numsSorted.length; i++) {
      if (numsSorted[i] - numsSorted[i - 1] > limit) { // A new group starts.
        currentGroup++;
      }

      if (!groupToNums.containsKey(currentGroup)) {
        groupToNums.put(currentGroup, new LinkedList<>());
      }
      groupToNums.get(currentGroup).add(numsSorted[i]);
      numsToGroup.put(numsSorted[i], currentGroup);
    }

    for (int i = 0; i < nums.length; i++) {
      int group = numsToGroup.get(nums[i]);
      nums[i] = groupToNums.get(group).remove(0);
    }

    return nums;
  }
  // Divided the elements into groups. (Using a predicate)
  // Sort within groups lexicographically.
}
