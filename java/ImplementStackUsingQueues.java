// https://leetcode.com/problems/implement-stack-using-queues/description/

import java.util.LinkedList;
import java.util.Queue;

/**
 * **Implement Stack Using Queues**:
 *
 * Implement a last-in-first-out (LIFO) stack using only two queues. The
 * implemented stack should support all the functions of a normal stack (`push`,
 * `top`, `pop`, and `empty`).
 *
 * Implement the `MyStack` class:
 *
 * - `void push(int x)` Pushes element x to the top of the stack.
 * - `int pop()` Removes the element on the top of the stack and returns it.
 * - `int top()` Returns the element on the top of the stack.
 * - `boolean empty()` Returns `true` if the stack is empty, `false` otherwise.
 *
 * **Notes**:
 *
 * - You must use **only** standard operations of a queue, which means that only
 * `push to back`, `peek/pop from front`, `size` and `is empty` operations are
 * valid.
 * - Depending on your language, the queue may not be supported natively. You
 * may simulate a queue using a list or deque (double-ended queue) as long as
 * you use only a queue's standard operations.
 */

public class ImplementStackUsingQueues {
  Queue<Integer> queue = new LinkedList<>();

  public void push(int x) {
    queue.offer(x);
    // Rotate queue until the last added element faces front.
    for (int i = 0; i < queue.size() - 1; i++) {
      queue.offer(queue.poll());
    }
  }

  public int pop() {
    return queue.poll();
  }

  public int top() {
    return queue.peek();
  }

  public boolean empty() {
    return queue.isEmpty();
  }
}
