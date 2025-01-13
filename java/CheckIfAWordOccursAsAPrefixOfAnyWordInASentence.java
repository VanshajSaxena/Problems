// https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/description/

import java.util.ArrayList;

/**
 * **Check If A Word Occurs As A Prefix Of Any Word In A Sentence**:
 *
 * Given a `sentence` that consists of some words separated by a **single
 * space**, and a `searchWord`, check if `searchWord` is a prefix of any word in
 * `sentence`.
 *
 * Return _the index of the word in `sentence` (**1-indexed**) where
 * `searchWord` is a prefix of this word_. If `searchWord` is a prefix of more
 * than one word, return the index of the first word (**minimum index**). If
 * there is no such word return 1.
 *
 * A **prefix** of a string s is any leading contiguous substring of s.
 */

public class CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {

  /**
   * Doesn't use a Java built-in Function.
   */
  public int isPrefixOfWord(String sentence, String searchWord) {
    ArrayList<String> words = new ArrayList<>();

    int j = 0;
    int i = 0;
    for (; i < sentence.length(); i++) {
      if (sentence.charAt(i) == ' ') {
        String word = sentence.substring(j, i);
        words.add(word);
        j = i + 1;
      }
    }

    words.add(sentence.substring(j, i));

    int idx = 0;
    for (String word : words) {
      idx++;
      if (word.length() < searchWord.length()) {
        continue;
      }

      if (isPrefix(word, searchWord)) {
        return idx;
      }
    }
    return -1;
  }

  private boolean isPrefix(String word, String searchWord) {
    for (int i = 0; i < searchWord.length(); i++) {
      if (word.charAt(i) != searchWord.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Uses Java's built-in functions.
   */
  public int isPrefixOfWordOptimised(String sentence, String searchWord) {
    int index = 1; // 1-indexed word count
    int n = searchWord.length();

    for (String word : sentence.split(" ")) {
      if (word.length() >= n && word.startsWith(searchWord)) {
        return index;
      }
      index++;
    }
    return -1;
  }
}
