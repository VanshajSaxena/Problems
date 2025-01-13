// https://leetcode.com/problems/take-gifts-from-the-richest-pile/description/

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * **Take Gifts From The Richest Pile**:
 *
 * You are given an integer array `gifts` denoting the number of gifts in
 * various piles. Every second, you do the following:
 *
 * - Choose the pile with the maximum number of gifts.
 * - If there is more than one pile with the maximum number of gifts, choose
 * any.
 * - Leave behind the floor of the square root of the number of gifts in the
 * pile. Take the rest of the gifts.
 *
 * Return _the number of gifts remaining after `k` seconds_.
 */

public class TakeGiftsFromTheRichestPile {
  public long pickGifts(int[] gifts, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

    for (Integer pile : gifts) {
      heap.add(pile);
    }

    for (int i = 0; i < k; i++) {
      heap.add((int) Math.floor(Math.sqrt(heap.poll())));
    }

    long totalGifts = 0;
    while (!heap.isEmpty()) {
      totalGifts += heap.poll();
    }

    return totalGifts;
  }
}
