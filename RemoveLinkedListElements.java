// https://leetcode.com/problems/remove-linked-list-elements/description/

/**
 * **Remove Linked List Elements**:
 *
 * Given the `head` of a linked list and an integer `val`, remove all the nodes
 * of the linked list that has `Node.val == val`, and return the new head.
 */

public class RemoveLinkedListElements {
  public ListNode removeElements(ListNode head, int val) {
    ListNode tempNode = new ListNode(0);
    tempNode.next = head;
    recurRemoveElements(tempNode, val);
    return tempNode.next;
  }

  private void recurRemoveElements(ListNode node, int val) {
    // Base Case: Reached the end of the list.
    if (node == null) {
      // This will start backtracking.
      return;
    }

    // If `node.next` is not `null` and `node.next.val = val`, we found the node to
    // be deleted.
    if (node.next != null && node.next.val == val) {
      node.next = node.next.next; // Simply remove its reference.
      // Pass `node` as an argument because the next call will check `node.next` which
      // has been updated just now, as above.
      recurRemoveElements(node, val);
    } else {
      // If `node.next` if possibly `null`, it will be caught by the base case of the
      // next recursive call, so no need explicitly check for null safety.
      recurRemoveElements(node.next, val);
    }
  }
}
