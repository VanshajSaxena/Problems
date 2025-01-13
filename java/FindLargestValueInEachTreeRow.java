// https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * **Find Largest Value In Each Tree Row**:
 *
 * Given the `root` of a binary tree, return _an array of the largest value in
 * each row of the tree **(0-indexed)**_.
 */

public class FindLargestValueInEachTreeRow {
  /**
   * BFS Solution: The optimal solution.
   */
  public List<Integer> largestValuesI(TreeNode root) {
    List<Integer> rowLargest = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();

    queue.add(root);
    if (root == null) {
      return rowLargest;
    }

    while (!queue.isEmpty()) {
      int size = queue.size();
      int maxVal = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode currNode = queue.poll();
        if (currNode.val > maxVal) {
          maxVal = currNode.val;
        }
        if (currNode.left != null) {
          queue.add(currNode.left);
        }
        if (currNode.right != null) {
          queue.add(currNode.right);
        }
      }
      rowLargest.add(maxVal);
    }

    return rowLargest;
  }

  /**
   * DFS Solution: For completeness.
   */
  public List<Integer> largestValuesII(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    searchMax(ans, root, 0);
    return ans;
  }

  private void searchMax(List<Integer> ans, TreeNode node, int depth) {
    if (node == null) {
      return;
    }

    if (depth == ans.size()) {
      ans.add(node.val);
    } else {
      ans.set(depth, Math.max(ans.get(depth), node.val));
    }

    searchMax(ans, node.left, depth);
    searchMax(ans, node.right, depth);
  }
}
