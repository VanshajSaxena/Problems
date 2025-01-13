// https://leetcode.com/problems/fruit-into-baskets/description/

import java.util.HashMap;

/**
 * **Fruit Into Baskets**
 *
 * You are visiting a farm that has a single row of fruit trees arranged from
 * left to right. The trees are represented by an integer array `fruits` where
 * `fruits[i]` is the **type** of fruit the `ith` tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some
 * strict rules that you must follow:
 *
 * - You only have **two** baskets, and each basket can only hold a **single
 * type** of fruit. There is no limit on the amount of fruit each basket can
 * hold.
 * - Starting from any tree of your choice, you must pick **exactly one fruit**
 * from **every** tree (including the start tree) while moving to the right. The
 * picked fruits must fit in one of your baskets.
 * - Once you reach a tree with fruit that cannot fit in your baskets, you must
 * stop.
 *
 * Given the integer array `fruits`, return _the **maximum** number of fruits
 * you can
 * pick_.
 */

public class FruitIntoBaskets {

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/fruit-into-baskets/solutions/170740/java-c-python-sliding-window-for-k-elements)
   */
  public int totalFruitI(int[] tree) {

    HashMap<Integer, Integer> map = new HashMap<>();

    int i = 0, j;

    for (j = 0; j < tree.length; ++j) {
      map.put(tree[j], map.getOrDefault(tree[j], 0) + 1);

      if (map.size() > 2) {
        map.put(tree[i], map.get(tree[i]) - 1);
        map.remove(tree[i++], 0);
      }
    }

    return j - i;
  }

  /**
   * [LeetCode
   * Solution](https://leetcode.com/problems/fruit-into-baskets/solutions/170745/problem-longest-subarray-with-2-elements)
   * [Hint](https://leetcode.com/problems/fruit-into-baskets/solutions/170745/problem-longest-subarray-with-2-elements/comments/742259)
   */
  public int totalFruitII(int[] tree) {
    int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
    for (int c : tree) {
      cur = c == a || c == b ? cur + 1 : count_b + 1;
      count_b = c == b ? count_b + 1 : 1;
      if (b != c) {
        a = b;
        b = c;
      }
      res = Math.max(res, cur);
    }
    return res;
  }
}
