// https://www.interviewbit.com/problems/nearest-smaller-element/

/**
 * **Nearest Smaller Element**:
 *
 * Given an array, find the **nearest** smaller element G[i] for every element
 * A[i] in the array such that the element has an **index smaller than i**.
 *
 * More formally,
 *
 * G[i] for an element A[i] = an element A[j] such that
 * j is maximum possible AND
 * j < i AND
 * A[j] < A[i]
 *
 * Elements for which no smaller element exist, consider next smaller element as
 * -1.
 */

public class NearestSmallerElement {
  public int[] prevSmaller(int[] A) {
    int length = A.length;

    int[] stack = new int[length];
    int head = -1;

    int[] ans = new int[length];

    for (int i = 0; i < length; i++) {
      int element = A[i];

      while (head != -1 && stack[head] >= element) {
        head--;
      }

      ans[i] = (head == -1) ? -1 : stack[head];

      stack[++head] = element;
    }
    return ans;
  }
}
