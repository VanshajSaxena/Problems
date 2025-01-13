// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/

/**
 * **Maximum Points You Can Obtain From Cards**:
 *
 * There are several cards **arranged in a row**, and each card has an
 * associated number of points. The points are given in the integer array
 * `cardPoints`.
 *
 * In one step, you can take one card from the beginning or from the end of the
 * row. You have to take exactly `k` cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array `cardPoints` and the integer `k`, return the maximum
 * score you can obtain.
 */

public class MaximumPointsYouCanObtainFromCards {
  public int maxScoreI(int[] cardPoints, int k) {
    int n = cardPoints.length;
    int totalSum = 0;

    // Calculate total sum of the array
    for (int point : cardPoints) {
      totalSum += point;
    }

    // Sliding window to find the minimum sum of n - k elements
    int windowSize = n - k;
    int windowSum = 0;
    for (int i = 0; i < windowSize; i++) {
      windowSum += cardPoints[i];
    }

    int minWindowSum = windowSum;
    for (int i = windowSize; i < n; i++) {
      windowSum += cardPoints[i] - cardPoints[i - windowSize];
      minWindowSum = Math.min(minWindowSum, windowSum);
    }

    // Max score is total sum minus the minimum window sum
    return totalSum - minWindowSum;
  }

  public int maxScoreII(int[] c, int k) {
    int sum = 0, max = 0;
    int l = k - 1, r = c.length - 1;

    // Calculate initial sum from the left
    for (int i = 0; i < k; i++)
      sum += c[i];

    max = sum;

    // Slide the window by removing from left and adding from right
    while (l >= 0) {
      sum = sum - c[l] + c[r];
      r--;
      l--;
      max = Math.max(max, sum);
    }
    return max;
  }
}
