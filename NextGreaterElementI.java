// https://leetcode.com/problems/next-greater-element-i/description/

import java.util.HashMap;
import java.util.Stack;

/**
 * **NextGreaterElementI**:
 *
 * The **next greater element** of some element `x` in an array is the **first
 * greater** element that is **to the right** of `x` in the same array.
 *
 * You are given two **distinct 0-indexed** integer arrays `nums1` and `nums2`,
 * where `nums1` is a subset of `nums2`.
 *
 * For each `0 <= i < nums1.length`, find the index j such that `nums1[i] ==
 * nums2[j]` and determine the **next greater element** of `nums2[j]` in
 * `nums2`. If there is no next greater element, then the answer for this query
 * is `-1`.
 *
 * Return _an array `ans` of length `nums1.length` such that `ans[i]` is the
 * **next greater element** as described above_.
 */

public class NextGreaterElementI {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();
    Stack<Integer> stack = new Stack<>();

    for (int i = nums2.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
        stack.pop();
      }

      if (!stack.isEmpty()) {
        map.put(nums2[i], stack.peek());
      } else {
        map.put(nums2[i], -1);
      }

      stack.push(nums2[i]);
    }

    int[] ans = new int[nums1.length];

    for (int i = 0; i < ans.length; i++) {
      ans[i] = map.get(nums2[i]);
    }

    return ans;
  }
}
