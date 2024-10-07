// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

/**
 * **Best Time To Buy And Sell Stock**:
 *
 * You are given an array `prices` where `prices[i]` is the price of a given
 * stock on the `i`th day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you
 * cannot achieve any profit, return `0`.
 */

public class BestTimeToBuyAndSellStock {
  // The below algorithm is trying to find the `minimum` element in the array and
  // trying to find the maximum difference between the found `minimun` and the
  // current element in each iteration.
  public int maxProfit(int[] prices) {
    int maxProfit = 0;
    int min = prices[0];
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] < min) {
        min = prices[i];
      } else if (maxProfit < prices[i] - min) {
        maxProfit = prices[i] - min;
      }
    }
    return maxProfit;
  }
}
