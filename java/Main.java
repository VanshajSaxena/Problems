/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    Solution sol = new Solution();
    var result = sol.findMaxConsecutiveOnes(new int[] { 1, 1, 0, 1, 1, 1 });
    System.out.println(result);
  }
}
