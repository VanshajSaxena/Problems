// https://leetcode.com/problems/delete-characters-to-make-fancy-string/description/

/**
 * **Inner Delete Characters To Make Fancy String**:
 *
 * A **fancy string** is a string where no **three consecutive** characters are
 * equal.
 *
 * Given a string `s`, delete the **minimum** possible number of characters from
 * `s` to make it **fancy**.
 *
 * Return _the final string after the deletion_. It can be shown that the answer
 * will always be unique.
 */

class DeleteCharactersToMakeFancyString {

  public String makeFancyString(String s) {
    // If string length is less than 3, it's already fancy
    if (s.length() < 3) {
      return s;
    }

    // Use StringBuilder for efficient string manipulation
    StringBuilder sb = new StringBuilder();

    // Always add the first character
    sb.append(s.charAt(0));

    // Iterate through characters from index 1 to second-to-last
    for (int i = 1; i < s.length() - 1; i++) {
      // Check previous, current, and next characters
      char prev = s.charAt(i - 1);
      char curr = s.charAt(i);
      char next = s.charAt(i + 1);

      // Add current character only if it doesn't create 3 consecutive same chars
      if (!(prev == curr && curr == next)) {
        sb.append(curr);
      }
    }

    // Always add the last character
    sb.append(s.charAt(s.length() - 1));

    return sb.toString();
  }
}
