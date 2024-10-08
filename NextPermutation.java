// https://leetcode.com/problems/next-permutation/description/

/**
 * **Next Permutation**:
 *
 * A **permutation** of an array of integers is an arrangement of its members
 * into a sequence or linear order.
 *
 * For example, for `arr = [1,2,3]`, the following are all the permutations of
 * `arr`: `[1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]`.
 *
 * The **next permutation** of an array of integers is the next
 * lexicographically greater permutation of its integer. More formally, if all
 * the permutations of
 * the array are sorted in one container according to their lexicographical
 * order, then the **next permutation** of that array is the permutation that
 * follows it in the sorted container. If such arrangement is not possible, the
 * array must be rearranged as the lowest possible order (i.e., sorted in
 * ascending order).
 *
 * For example, the next permutation of `arr = [1,2,3]` is `[1,3,2]`.
 * Similarly, the next permutation of `arr = [2,3,1]` is `[3,1,2]`.
 * While the next permutation of `arr = [3,2,1]` is `[1,2,3]` because `[3,2,1]`
 * does not have a lexicographical larger rearrangement.
 *
 * Given an array of integers nums, _find the next permutation of `nums`_.
 *
 * The replacement must be in place and use only constant extra memory.
 */

public class NextPermutation {

  public void nextPermutation(int[] nums) {

    // Find the longest non-increasing suffix: This helps identify `pivot`, which is
    // the first element from the right that is smaller than the element before it.
    int pivot = -1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        pivot = i;
        break;
      }
    }

    // If no such pivot exists the permutation is the last one (i.e., it's sorted in
    // descending order), and reversing it gives the first permutation (next
    // Permutation).
    if (pivot == -1) {
      reverse(nums, 0);
      return;
    }

    // Find the rightmost successor to the pivot in the suffix. This is the smallest
    // element in the suffix that is larger than the pivot. And swap it with the
    // pivot.
    for (int i = nums.length - 1; i > pivot; i--) {
      if (nums[i] > nums[pivot]) {
        int temp = nums[i];
        nums[i] = nums[pivot];
        nums[pivot] = temp;
        break;
      }
    }

    // Reverse the suffix.
    reverse(nums, pivot + 1);
  }

  private void reverse(int[] nums, int start) {
    int i = start, j = nums.length - 1;
    while (i < j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
      i++;
      j--;
    }
  }
}
