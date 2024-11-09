// https://leetcode.com/problems/reorder-list/description/

/**
 * **Reorder List**:
 *
 * You are given the head of a singly linked-list. The list can be represented
 * as:
 *
 * `L0 → L1 → … → Ln - 1 → Ln`
 *
 * _Reorder the list to be on the following form_:
 *
 * `L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …`
 *
 * You may not modify the values in the list's nodes. Only nodes themselves may
 * be changed.
 */

public class ReorderList {
  public void reorderList(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode secondHalf = reverseList(slow.next);
    slow.next = null;

    ListNode firstHalf = head;

    while (secondHalf != null) {
      ListNode temp1 = firstHalf.next;
      ListNode temp2 = secondHalf.next;

      firstHalf.next = secondHalf;
      secondHalf.next = temp1;

      firstHalf = temp1;
      secondHalf = temp2;
    }
  }

  private ListNode reverseList(ListNode current) {
    if (current == null || current.next == null) {
      return current;
    }

    ListNode newHead = reverseList(current.next);

    current.next.next = current;

    current.next = null;

    return newHead;
  }
}
