// https://leetcode.com/problems/longest-common-prefix/description/

/**
 * **Longest Common Prefix**:
 *
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 *
 * If there is no common prefix, return an empty string `""`.
 */

public class LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    // Return early if `strs.length == 0`.
    if (strs == null || strs.length == 0) {
      return "";
    }

    // Find the string with the smallest length.
    int minLength = Integer.MAX_VALUE;
    for (String str : strs) {
      minLength = Math.min(minLength, str.length());
    }

    // Start a Binary Search that finds common prefix in each string in `strs` at
    // each iteration, until `low <= high`.
    int low = 1;
    int high = minLength;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (isCommonPrefix(strs, mid)) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    // Return substring of the first string from `0` to eventual average of `low`
    // and `high` pointer.
    return strs[0].substring(0, (low + high) / 2);
  }

  // This is a helper function that checks if all the strings in `strs` upto the
  // length of `len` has the same prefix.
  private boolean isCommonPrefix(String[] strs, int len) {
    String prefix = strs[0].substring(0, len);
    for (int i = 1; i < strs.length; i++) {
      if (!strs[i].startsWith(prefix)) {
        return false;
      }
    }
    return true;
  }
}
