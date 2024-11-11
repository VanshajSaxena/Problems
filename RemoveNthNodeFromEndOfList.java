// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

/**
 * **Remove Nth Node From End Of List**:
 *
 * Given the `head` of a linked list, remove the `nth` node from the end of the
 * list and return its head.
 */

public class RemoveNthNodeFromEndOfList {
  /**
   * Recursive Approach
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0); // Create a dummy node
    dummy.next = head; // Point it to the head of the list
    recurRemoveNthFromEnd(dummy, 1, n); // Start recursion from the dummy node
    return dummy.next; // Return the updated list, skipping the dummy node
  }

  private int recurRemoveNthFromEnd(ListNode curr, int len, int n) {
    if (curr.next == null) {
      return len;
    }

    int length = recurRemoveNthFromEnd(curr.next, len + 1, n);

    if (length - n == len) {
      curr.next = curr.next.next; // Skip the nth node from the end
    }
    return length;
  }

  /**
   * Iterative Apporach
   */
  public ListNode iterRemoveNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0); // Dummy node
    dummy.next = head; // Point to head
    ListNode first = dummy;
    ListNode second = dummy;

    // Move first n + 1 steps ahead
    for (int i = 0; i <= n; i++) {
      first = first.next;
    }

    // Move both pointers until first reaches the end
    while (first != null) {
      first = first.next;
      second = second.next;
    }

    // Remove the nth node from end
    second.next = second.next.next;

    return dummy.next; // Return the updated list
  }
}
