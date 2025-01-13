// https://leetcode.com/problems/assign-cookies/description/

import java.util.Arrays;

/**
 * **Assign Cookies**:
 *
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 *
 * Each child `i` has a greed factor `g[i]`, which is the minimum size of a
 * cookie that the child will be content with; and each cookie `j` has a size
 * `s[j]`. If `s[j] >= g[i]`, we can assign the cookie `j` to the child `i`, and
 * the child `i` will be content. Your goal is to maximize the number of your
 * content children and output the maximum number.
 */

public class AssignCookies {
  // Greedy Solution:
  // https://leetcode.com/problems/assign-cookies/editorial/#approach-greedy-two-pointer
  public int findContentChildren(int[] greed, int[] size) {
    // Sort arrays
    Arrays.sort(size);
    Arrays.sort(greed);

    int contentChild = 0;
    int cookieIndex = 0;
    while (contentChild < greed.length && cookieIndex < size.length) {
      if (size[cookieIndex] >= greed[contentChild]) {
        contentChild++;
      }
      cookieIndex++;
    }
    return contentChild;
  }
}
