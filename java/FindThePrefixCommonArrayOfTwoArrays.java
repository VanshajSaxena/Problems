// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * **Find The Prefix Common Array Of Two Arrays**:
 *
 * You are given two **0-indexed** integer permutations `A` and `B` of length
 * `n`.
 *
 * A **prefix common array** of `A` and `B` is an array `C` such that `C[i]` is
 * equal to the count of numbers that are present at or before the index `i` in
 * both `A` and `B`.
 *
 * Return _the **prefix common array** of `A` and `B`_.
 *
 * A sequence of `n` integers is called a **permutation** if it contains all
 * integers from `1` to `n` exactly once.
 */

public class FindThePrefixCommonArrayOfTwoArrays {
  /**
   * Novice Way
   */
  public int[] findThePrefixCommonArrayI(int[] A, int[] B) {
    HashSet<Integer> setA = new HashSet<>();
    HashSet<Integer> setB = new HashSet<>();
    int n = A.length;
    int[] commonCount = new int[n];
    for (int i = 0; i < n; i++) {
      setA.add(A[i]);
      setB.add(B[i]);
      for (int j = 1; j <= n; j++) {
        if (setA.contains(j) && setB.contains(j)) {
          commonCount[i]++;
        }
      }
    }
    return commonCount;
  }

  /**
   * Still Novice: Using a map.
   */
  public int[] findThePrefixCommonArrayII(int[] A, int[] B) {
    Map<Integer, Integer> map = new HashMap<>();
    int n = A.length;

    int[] commonCount = new int[n];
    for (int i = 0; i < n; i++) {
      map.put(A[i], map.getOrDefault(A[i], 0) + 1);
      map.put(B[i], map.getOrDefault(B[i], 0) + 1);
      for (int j = 1; j <= n; j++) {
        if (map.getOrDefault(j, 0) == 2) {
          commonCount[i]++;
        }
      }
    }

    return commonCount;
  }

  public int[] findThePrefixCommonArrayIII(int[] A, int[] B) {
    int n = A.length;
    int[] commonCount = new int[n];
    int[] freq = new int[n + 1];
    int currentCount = 0;
    for (int i = 0; i < n; i++) {
      freq[A[i]]++;
      if (freq[A[i]] == 2) {
        currentCount++;
      }
      freq[B[i]]++;
      if (freq[B[i]] == 2) {
        currentCount++;
      }
      commonCount[i] = currentCount;
    }
    return commonCount;
  }
}
