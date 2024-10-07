// https://leetcode.com/problems/majority-element/description/

/**
 * **Majority Element**:
 *
 * Given an array `nums` of size `n`, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You
 * may assume that the majority element always exists in the array.
 */

public class MajorityElement {
  // This solution works on Moore's Voting Algorithm: The algorithm relies on the
  // principle that if you cancel out each occurrence of an element with a
  // different element, the majority element (if it exists) will remain.
  // NOTE:- This only works when the given array has a majority element, as it
  // does not verify the element to be the majority element. The
  // question gurantees a majority element.
  public int majorityElement(int[] nums) {
    int count = 0, element = nums[0];
    for (int i = 0; i < nums.length; i++) {
      if (count == 0) {
        element = nums[i];
        count++;
      } else if (element == nums[i]) {
        count++;
      } else {
        count--;
      }
    }
    return element;
  }
}
