// https://leetcode.com/problems/string-compression-iii/description/

/**
 * **String Compression III**:
 *
 * Given a string `word`, compress it using the following algorithm:
 *
 * - Begin with an empty string `comp`. While `word` is **not** empty, use the
 * following operation:
 * -- Remove a maximum length prefix of `word` made of a single character c
 * repeating at most 9 times.
 * -- Append the length of the prefix followed by c to `comp`.
 *
 * Return the string `comp`.
 */

public class StringCompressionIII {
  public String compressedString(String word) {
    StringBuilder comp = new StringBuilder();
    char[] wordArr = word.toCharArray();

    int j = 1;
    for (int i = 0; i < word.length() - 1; i++) {
      if (wordArr[i] == wordArr[i + 1]) {
        if (j > 8) {
          comp.append(j);
          comp.append(wordArr[i]);
          j = 1;
        } else {
          j++;
        }
      } else {
        comp.append(j);
        comp.append(wordArr[i]);
        j = 1;
      }
    }

    comp.append(j);
    comp.append(word.charAt(wordArr.length - 1));

    return comp.toString();
  }
}
