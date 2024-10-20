// https://leetcode.com/problems/reverse-linked-list/description/

/**
 * **Reverse Linked List**:
 *
 * Given the `head` of a singly linked list, reverse the list, and return _the
 * reversed list_.
 */

public class ReverseLinkedList {

  public ListNode reverseList(ListNode head) {
    return recurReverseList(head);
  }

  private ListNode recurReverseList(ListNode current) {
    // Base Case: If the list has only a single element or the end of the list is
    // reached.
    if (current == null || current.next == null) {
      return current;
    }

    // Recursive Case: Recurse to the next element in the list until "Base Case" is
    // reached.
    ListNode newHead = recurReverseList(current.next);

    // This is a rearrangement operation where `current.next`'s next is pointed back
    // to `current`.
    // For a list like 1 -> 2 -> 3 -> null
    // when the recursion unwinds, `2` could be an invocation's current element, in
    // that case, `2.next.next` can be imagined as `3.next` (as current.next is 3).
    // This is same as `3.next = 2`.
    current.next.next = current;
    // Now the list looks like `1 -> 2 -> 3 -> 2`, this is a cycle. And the next
    // operation will break a cycle like this.
    current.next = null;

    // `newHead` recorded from the last invocation is returned.
    return newHead;
  }
}
