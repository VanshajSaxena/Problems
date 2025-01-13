// https://leetcode.com/problems/odd-even-linked-list/description/

/**
 * **Odd Even Linked List**:
 *
 * Given the `head` of a singly linked list, group all the nodes with odd
 * indices together followed by the nodes with even indices, and return _the
 * reordered list_.
 *
 * The **first** node is considered **odd**, and the **second** node is
 * **even**, and so on.
 *
 * Note that the relative order inside both the even and odd groups should
 * remain as it was in the input.
 *
 * You must solve the problem in `O(1)` extra space complexity and `O(n)` time
 * complexity.
 */

public class OddEvenLinkedList {
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode odd = head;
    ListNode even = head.next;
    ListNode temp = head.next;

    while (even != null && even.next != null) {
      odd.next = odd.next.next;
      even.next = even.next.next;
      odd = odd.next;
      even = even.next;
    }
    odd.next = temp;
    return head;
  }
}
