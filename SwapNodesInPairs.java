// https://leetcode.com/problems/swap-nodes-in-pairs/description/

/**
 * **Swap Nodes In Pairs**:
 *
 * Given a linked list, swap every two adjacent nodes and return its head. You
 * must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 */

public class SwapNodesInPairs {
  public ListNode swapPairs(ListNode head) {
    ListNode newHead = recurSwapPairs(head);
    return newHead;
  }

  private ListNode recurSwapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode firstNode = head;
    ListNode secondNode = head.next;

    firstNode.next = recurSwapPairs(secondNode.next);

    secondNode.next = firstNode;

    return secondNode;
  }
}
