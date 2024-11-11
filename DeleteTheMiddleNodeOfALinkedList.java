// https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/

/**
 * **Delete The Middle Node Of A Linked List**:
 *
 * You are given the `head` of a linked list. **Delete** the **middle node**,
 * and return _the `head` of the modified linked list_.
 *
 * The **middle node** of a linked list of size `n` is the `⌊n / 2⌋th` node from
 * the **start** using **0-based indexing**, where `⌊x⌋` denotes the largest
 * integer less than or equal to `x`.
 *
 * - For `n` = `1`, `2`, `3`, `4`, and `5`, the middle nodes are `0`, `1`, `1`,
 * `2`, and `2`, respectively.
 */

public class DeleteTheMiddleNodeOfALinkedList {
  public ListNode deleteMiddle(ListNode head) {

    if (head.next == null) {
      return null;
    } else if (head.next.next == null) {
      head.next = null;
      return head;
    }

    ListNode dummy = new ListNode(0, head);
    ListNode slow = dummy;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    slow.next = slow.next.next;

    return dummy.next;
  }
}
