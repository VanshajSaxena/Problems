// https://leetcode.com/problems/intersection-of-two-linked-lists/description/

/**
 * **Intersection Of Two Linked Lists**:
 *
 * Given the heads of two singly linked-lists `headA` and `headB`, return _the
 * node at which the two lists intersect_. If the two linked lists have no
 * intersection at all, return `null`.
 *
 * The test cases are generated such that there are no cycles anywhere in the
 * entire linked structure.
 *
 * **Note** that the linked lists must **retain their original structure** after
 * the function returns.
 *
 */

public class IntersectionOfTwoLinkedLists {

  /**
   * Naive Approach:
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenghtA = 1;
    int lenghtB = 1;

    ListNode currA = headA;
    while (currA.next != null) {
      lenghtA++;
      currA = currA.next;
    }

    ListNode currB = headB;
    while (currB.next != null) {
      lenghtB++;
      currB = currB.next;
    }

    if (currA != currB) {
      return null;
    }

    if (lenghtA > lenghtB) {
      int diff = lenghtA - lenghtB;
      while (diff > 0) {
        headA = headA.next;
        diff--;
      }
    } else if (lenghtB > lenghtA) {
      int diff = lenghtB - lenghtA;
      while (diff > 0) {
        headB = headB.next;
        diff--;
      }
    }

    while (headA != headB) {
      headA = headA.next;
      headB = headB.next;
    }

    return headA;
  }

  /**
   * Better Approach:
   * [LeetCode
   * Solutions](https://leetcode.com/problems/intersection-of-two-linked-lists/solutions/49785/java-solution-without-knowing-the-difference-in-len/comments/165648)
   */
  public ListNode getIntersectionNodeBetter(ListNode headA, ListNode headB) {
    // boundary check
    if (headA == null || headB == null)
      return null;

    ListNode a = headA;
    ListNode b = headB;

    // if a & b have different len, then we will stop the loop after second
    // iteration
    while (a != b) {
      // for the end of first iteration, we just reset the pointer to the head of
      // another linkedlist
      a = a == null ? headB : a.next;
      b = b == null ? headA : b.next;
    }

    return a;
  }
}
