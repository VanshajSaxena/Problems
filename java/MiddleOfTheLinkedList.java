// https://leetcode.com/problems/middle-of-the-linked-list/description/

/**
 * **Middle Of The Linked List**:
 *
 * Given the `head` of a singly linked list, return _the middle node of the
 * linked list_.
 *
 * If there are two middle nodes, return _the second middle node_.
 */

public class MiddleOfTheLinkedList {
  /**
   * This is done using **Tortoise Hare** Method.
   */
  public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null && slow != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
