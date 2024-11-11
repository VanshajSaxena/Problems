// https://leetcode.com/problems/sort-list/description/

/**
 * **Sort List**:
 *
 * Given the `head` of a linked list, return _the list after sorting it in
 * **ascending order**_.
 */

public class SortList {
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode fast = head;
    ListNode prev = null;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      prev = slow;
      slow = slow.next;
    }

    ListNode mid = prev.next;
    prev.next = null;

    ListNode left = sortList(head);
    ListNode right = sortList(mid);

    return merge(left, right);
  }

  private ListNode merge(ListNode left, ListNode right) {
    ListNode temp = new ListNode(0);
    ListNode curr = temp;
    while (left != null && right != null) {
      if (left.val < right.val) {
        curr.next = left;
        left = left.next;
      } else {
        curr.next = right;
        right = right.next;
      }
      curr = curr.next;
    }
    while (left != null) {
      curr.next = left;
      left = left.next;
      curr = curr.next;
    }
    while (right != null) {
      curr.next = right;
      right = right.next;
      curr = curr.next;
    }
    return temp.next;
  }
}
