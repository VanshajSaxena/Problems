// https://leetcode.com/problems/word-subsets/description/

import java.util.ArrayList;
import java.util.List;

public class WordSubsets {
  /**
   * [LeetCode Editorial]( https://leetcode.com/problems/word-subsets/editorial )
   */
  public List<String> wordSubsets(String[] words1, String[] words2) {
    List<String> ans = new ArrayList<>();

    int[] bMax = new int[26];

    for (String b : words2) {
      int[] bCount = getCount(b);

      for (int i = 0; i < 26; i++) {
        bMax[i] = Math.max(bMax[i], bCount[i]);
      }
    }

    search: for (String a : words1) {
      int[] aCount = getCount(a);
      for (int i = 0; i < 26; i++) {
        if (aCount[i] < bMax[i]) {
          continue search;
        }
      }
      ans.add(a);
    }

    return ans;
  }

  private int[] getCount(String word) {
    int[] bCount = new int[26];
    for (char c : word.toCharArray()) {
      bCount[c - 'a']++;
    }
    return bCount;
  }
}
