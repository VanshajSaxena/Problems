// https://leetcode.com/problems/adding-spaces-to-a-string/description/

/**
 * **Adding Spaces To A String**:
 *
 * You are given a **0-indexed** string `s` and a **0-indexed** integer array
 * `spaces` that describes the indices in the original string where spaces will
 * be added. Each space should be inserted **before** the character at the given
 * index.
 *
 * - For example, given `s = "EnjoyYourCoffee"` and `spaces = [5, 9]`, we place
 * spaces before `'Y'` and `'C'`, which are at indices `5` and `9` respectively.
 * Thus, we obtain `"Enjoy Your Coffee"`.
 *
 * Return _the modified string **after** the spaces have been added_.
 */

public class AddingSpacesToAString {
  public String addSpaces(String s, int[] spaces) {
    StringBuilder strWithSpaces = new StringBuilder();

    // Preallocate memory to reduce the overhead of increasing StringBuilder size
    // every time.
    strWithSpaces.ensureCapacity(s.length() + spaces.length);

    strWithSpaces.append(s.substring(0, spaces[0])).append(" ");

    for (int i = 0; i < spaces.length - 1; i++) {
      strWithSpaces.append(s.substring(spaces[i], spaces[i + 1])).append(" ");
    }

    strWithSpaces.append(s.substring(spaces[spaces.length - 1]));

    return strWithSpaces.toString();
  }
}
