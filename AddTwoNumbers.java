// https://leetcode.com/problems/add-two-numbers/

import java.util.List;

import javax.crypto.Mac;

/**
 * **Add Two Numbers**:
 *
 * You are given two **non-empty** linked lists representing two non-negative
 * integers. The digits are stored in **reverse order**, and each of their nodes
 * contains a single digit. Add the two numbers and return the sum as a linked
 * list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 */

public class AddTwoNumbers {

  /**
   * Naive Approach
   */
  public ListNode addTwoNumbersWithStrings(ListNode l1, ListNode l2) {

    // Create string buffers
    StringBuffer stbr1 = new StringBuffer();
    StringBuffer stbr2 = new StringBuffer();

    // Populate buffers
    while (l1 != null && l2 != null) {
      // Here each call to `insert(offset: offset, i: integer)` is an O(n) operation.
      // Thus is highly inefficient.
      stbr1.insert(0, l1.val);
      stbr2.insert(0, l2.val);
      l1 = l1.next;
      l2 = l2.next;
    }

    while (l1 != null) {
      stbr1.insert(0, l1.val);
      l1 = l1.next;
    }

    while (l2 != null) {
      stbr2.insert(0, l2.val);
      l2 = l2.next;
    }

    int result = Integer.valueOf(stbr1.toString()) + Integer.valueOf(stbr2.toString());

    // Convert calculated `result` to `char []`.
    char[] resultDigits = String.valueOf(result).toCharArray();

    // Populate `head` with last element of the array.
    ListNode head = new ListNode(Character.getNumericValue(resultDigits[resultDigits.length - 1]));
    ListNode curr = head; // temp node to help create a new list.

    // From second last element to the start of the array.
    for (int j = resultDigits.length - 2; j >= 0; j--) {
      // Create new nodes and move forward.
      curr.next = new ListNode(Character.getNumericValue(resultDigits[j]));
      curr = curr.next;
    }

    // Return `head`.
    return head;
  }

  /**
   * Optimised Approach: Here without converting and node to String, we are
   * directly processing each node's element one by one. As it is done in normal
   * sum problems.
   * 
   * A carry is propogated to the end of the number from the right to the left and
   * at each iteration a value is recorded.
   * If a carry is left after all iterations, it is added as a new node in the
   * list.
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, current = dummyHead;
    int carry = 0;

    while (p != null || q != null) {
      int num1 = p != null ? p.val : 0; // If list `p` get exhausted, return `0` for further iterations.
      int num2 = q != null ? q.val : 0; // If list `p` get exhausted, return `0` for further iterations.

      // sum is `num1 + num2` plus previous `carry`.
      int sum = carry + num1 + num2;
      carry = sum / 10; // `sum / 10` gives the quotient, which would be carried over by `carry`.

      int value = sum % 10; // This gives the remainder which would be recorded in the next node.

      current.next = new ListNode(value); // New node for the calculated `value`.
      current = current.next; // Move forward.

      // Don't move forward for the list which is exhausted.
      if (p != null) {
        p = p.next;
      }
      // Don't move forward for the list which is exhausted.
      if (q != null) {
        q = q.next;
      }
    }

    // If a `carry` is left add it as a new node.
    if (carry > 0) {
      current.next = new ListNode(carry);
    }

    // Return `dummyHead.next`, as dummyHead is not the part of the list.
    return dummyHead.next;
  }
}
