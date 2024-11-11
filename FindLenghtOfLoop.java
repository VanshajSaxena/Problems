// https://www.geeksforgeeks.org/problems/find-length-of-loop/1/

/**
 * **Find Lenght Of Loop**:
 *
 * Given the head of a linked list, determine whether the list contains a loop.
 * If a loop is present, **return the number of nodes** in the loop, otherwise
 * **return 0**.
 */

public class FindLenghtOfLoop {
  public int countNodesinLoop(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        break;
      }
    }
    if (fast == null || fast.next == null) {
      return 0;
    }
    slow = slow.next;
    int count = 1;
    while (slow != fast) {
      slow = slow.next;
      count++;
    }
    return count;
  }
}
