// https://leetcode.com/problems/asteroid-collision/description/

import java.util.Stack;

/**
 * **Asteroid Collision**
 *
 * We are given an array `asteroids` of integers representing asteroids in a
 * row.
 *
 * For each asteroid, the absolute value represents its size, and the sign
 * represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids
 * meet, the smaller one will explode. If both are the same size, both will
 * explode. Two asteroids moving in the same direction will never meet.
 */

public class AsteroidCollision {

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();

    for (Integer asteroid : asteroids) {
      if (asteroid > 0) {
        stack.push(asteroid);
      } else {
        while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
          stack.pop();
        }
        if (stack.isEmpty() || stack.peek() < 0) {
          stack.push(asteroid);
        }
        if (stack.peek() == -asteroid) {
          stack.pop();
        }
      }
    }

    int[] ans = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      ans[i] = stack.pop();
    }
    return ans;
  }

  public int[] asteroidCollisionSecond(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();

    for (int asteroid : asteroids) {
      boolean destroyed = false;

      // Handle collisions
      while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
        if (Math.abs(stack.peek()) == Math.abs(asteroid)) {
          stack.pop(); // Both asteroids are destroyed
          destroyed = true;
          break;
        } else if (Math.abs(stack.peek()) < Math.abs(asteroid)) {
          stack.pop(); // Top asteroid is destroyed
        } else {
          destroyed = true; // Current asteroid is destroyed
          break;
        }
      }

      if (!destroyed) {
        stack.push(asteroid); // No collision, or the asteroid survived
      }
    }

    // Convert stack to array
    int[] result = new int[stack.size()];
    for (int i = stack.size() - 1; i >= 0; i--) {
      result[i] = stack.pop();
    }

    return result;
  }
}
