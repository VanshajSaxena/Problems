// https://leetcode.com/problems/hand-of-straights/description/

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * **Hand Of Straights**:
 *
 * Alice has some number of cards and she wants to rearrange the cards into
 * groups so that each group is of size `groupSize`, and consists of `groupSize`
 * consecutive cards.
 *
 * Given an integer array `hand` where `hand[i]` is the value written on the
 * `ith` card and an integer `groupSize`, return `true` if she can rearrange the
 * cards, or `false` otherwise.
 */

public class HandOfStraights {

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/hand-of-straights/editorial/#approach-1-using-map)
   */
  public boolean isNStraightHandI(int[] hand, int groupSize) {
    int handSize = hand.length;
    if (handSize % groupSize != 0) {
      return false;
    }

    // TreeMap to store the count of each card value
    TreeMap<Integer, Integer> cardCount = new TreeMap<>();
    for (int i = 0; i < handSize; i++) {
      cardCount.put(hand[i], cardCount.getOrDefault(hand[i], 0) + 1);
    }

    // Process the cards until the map is empty
    while (cardCount.size() > 0) {
      // Get the smallest card value
      int current_card = cardCount.entrySet().iterator().next().getKey();
      // Check each consecutive sequence of groupSize cards
      for (int i = 0; i < groupSize; i++) {
        // If a card is missing or has exhausted its occurrences
        if (!cardCount.containsKey(current_card + i))
          return false;
        cardCount.put(
            current_card + i,
            cardCount.get(current_card + i) - 1);
        // Remove the card value if its occurrences are exhausted
        if (cardCount.get(current_card + i) == 0)
          cardCount.remove(
              current_card + i);
      }
    }

    return true;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/hand-of-straights/editorial/#approach-2-optimal)
   */
  public boolean isNStraightHandII(int[] hand, int groupSize) {
    // Map to store the count of each card value
    TreeMap<Integer, Integer> cardCount = new TreeMap<>();

    for (int card : hand) {
      cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
    }

    // Queue to keep track of the number of new groups
    // starting with each card value
    Queue<Integer> groupStartQueue = new LinkedList<>();
    int lastCard = -1, currentOpenGroups = 0;
    for (Map.Entry<Integer, Integer> entry : cardCount.entrySet()) {
      int currentCard = entry.getKey();
      // Check if there are any discrepancies in the sequence
      // or more groups are required than available cards
      if ((currentOpenGroups > 0 && currentCard > lastCard + 1) ||
          currentOpenGroups > cardCount.get(currentCard)) {
        return false;
      }
      // Calculate the number of new groups starting with the current card
      groupStartQueue.offer(cardCount.get(currentCard) - currentOpenGroups);
      lastCard = currentCard;
      currentOpenGroups = cardCount.get(currentCard);
      // Maintain the queue size to be equal to groupSize
      if (groupStartQueue.size() == groupSize) {
        currentOpenGroups -= groupStartQueue.poll();
      }
    }

    // All groups should be completed by the end
    return currentOpenGroups == 0;
  }
}
