// https://leetcode.com/problems/merge-two-sorted-lists/description/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * **Merge Two Sorted Lists**:
 *
 * You are given the heads of two sorted linked lists `list1` and `list2`.
 *
 * Merge the two lists into one **sorted** list. The list should be made by
 * splicing together the nodes of the first two lists.
 *
 * Return _the head of the merged linked list_.
 */

public class MergeTwoSortedLists {
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    return recurMergeTwoLists(list1, list2);
  }

  // Recursive function that returns the head of the merged linked list.
  private ListNode recurMergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) { // Base Case: When one list is empty.
      return list2;
    } else if (list2 == null) { // Base Case: When another is empty.
      return list1;
    }

    if (list1.val <= list2.val) {
      // Choose list1's node when it's smaller to maintain sorted order.
      // The recursive call handles merging the remaining elements, and
      // its result becomes our 'next' pointer to maintain the chain.
      list1.next = recurMergeTwoLists(list1.next, list2);
      return list1;
    } else {
      // Same logic applies when list2's node is smaller.
      list2.next = recurMergeTwoLists(list1, list2.next);
      return list2;
    }
  }
}
