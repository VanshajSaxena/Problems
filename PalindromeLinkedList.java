// https://leetcode.com/problems/palindrome-linked-list/description/

import java.util.Stack;

/**
 * **Palindrome Linked List**:
 *
 * Given the `head` of a singly linked list, return `true` _if it is a
 * palindrome or `false` otherwise_.
 */

public class PalindromeLinkedList {

  /**
   * This function is a simple approach to finding if a list is **Palindrome**,
   * this is not an optimised approach to solve this problem.
   * It uses iteration to solve the problem.
   *
   * Complexity:
   * - Time: O(n): Because the list is traversed twice.
   * - Space: O(n): Because the use of a stack.
   */
  public boolean isPalindrome(ListNode head) {
    ListNode start = head;
    Stack<ListNode> stack = new Stack<>();

    // Push first element into the stack
    stack.push(head);
    while (head.next != null) { // Push all elements one by one into the stack.
      stack.push(head.next);
      head = head.next;
    }

    while (!stack.isEmpty()) {
      // Check if the value at the top of the stack equals to the element's `val` of
      // the current iteration.
      if (start.val != stack.pop().val) {
        return false;
      }
      start = start.next;
    }

    // If all elements are same, the list is palindromic, hence return `true`.
    return true;
  }

  private ListNode curr; // instance variable to keep track of the head of the list.

  /**
   * This is a recursive apporach for finding if a list is **Palindromic**.
   * Complexity:
   * - Time: O(n) - Traversal
   * - Space: O(n) - Recursion Stack
   */
  public boolean isPalindromeWithRecursion(ListNode head) {
    curr = head;
    return recurIsPalindrome(head);
  }

  private boolean recurIsPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }

    boolean ans = recurIsPalindrome(head.next) && head.val == curr.val;
    curr = curr.next;
    return ans;
  }

  /**
   * The idea is to find the middle of a linked list and reverse the right half,
   * then compare it with the left half.
   * Complexity:
   * - Time: O(n) - Traversal.
   * - Space: O(1) - Constant extra space has been used.
   */
  public boolean isPalindromeOptimised(ListNode head) {
    ListNode slow = head;
    ListNode fast = head.next;

    while (fast != null && fast.next != null) {
      // This means if `slow` is moving with a speed of `1`, `fast` is moving at a
      // speed of `2`.
      slow = slow.next;
      fast = fast.next.next;
    }

    // Returns the head of the reversed linked list.
    ListNode rev = reverse(slow.next);

    slow.next = null;

    // Check palindrome
    while (rev != null) {
      if (head.val != rev.val) {
        return false;
      }
      head = head.next;
      rev = rev.next;
    }

    return true;
  }

  // Reverse a linked list in place.
  private ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

}
