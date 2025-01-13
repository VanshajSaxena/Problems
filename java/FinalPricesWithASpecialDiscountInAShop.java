// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/

/**
 * **Final Prices With A Special Discount In A Shop**:
 *
 * You are given an integer array `prices` where `prices[i]` is the price of the
 * `ith` item in a shop.
 *
 * There is a special discount for items in the shop. If you buy the `ith` item,
 * then you will receive a discount equivalent to `prices[j]` where `j` is the
 * minimum index such that `j > i` and `prices[j] <= prices[i]`. Otherwise, you
 * will not receive any discount at all.
 *
 * Return an integer array `answer` where `answer[i]` is the final price you
 * will
 * pay for the `ith` item of the shop, considering the special discount.
 */

public class FinalPricesWithASpecialDiscountInAShop {

  public int[] finalPrices(int[] prices) {
    int length = prices.length;
    int[] answer = new int[length];
    int[] stack = new int[length];
    answer[length - 1] = prices[length - 1];
    int top = -1;

    // Use a monotonic stack.
    for (int i = length - 1; i >= 0; i--) {
      // Pop elements from the stack until we find a smaller or equal price.
      while (top > -1 && stack[top] > prices[i]) {
        top--;
      }
      // If stack is empty, no discount is applied; otherwise, apply the discount.
      answer[i] = top == -1 ? prices[i] : prices[i] - stack[top];
      // Push the current price onto the stack.
      stack[++top] = prices[i];
    }
    return answer;
  }
}
