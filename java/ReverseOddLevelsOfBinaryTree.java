// https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * **Reverse Odd Levels Of Binary Tree**:
 *
 * Given the `root` of a **perfect** binary tree, reverse the node values at
 * each **odd** level of the tree.
 *
 * - For example, suppose the node values at level 3 are `[2,1,3,4,7,11,29,18]`,
 * then it should become `[18,29,11,7,4,3,1,2]`.
 *
 * Return _the root of the reversed tree_.
 *
 * A binary tree is **perfect** if all parent nodes have two children and all
 * leaves are on the same level.
 *
 * The level of a node is the number of edges along the path between it and the
 * root node.
 */

public class ReverseOddLevelsOfBinaryTree {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/editorial/#approach-1-depth-first-search)
   */
  public TreeNode reverseOddLevelsI(TreeNode root) {
    swapChildNodes(root.left, root.right, 0);
    return root;
  }

  private void swapChildNodes(TreeNode leftChild, TreeNode rightChild, int level) {
    if (leftChild == null || rightChild == null) {
      return;
    }

    if (level % 2 == 0) {
      int temp = leftChild.val;
      leftChild.val = rightChild.val;
      rightChild.val = temp;
    }

    swapChildNodes(leftChild.left, rightChild.right, level + 1);
    swapChildNodes(leftChild.right, rightChild.left, level + 1);
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/editorial/#approach-2-breadth-first-search)
   */
  public TreeNode reverseOddLevelsII(TreeNode root) {
    if (root == null) {
      return null;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int level = 0;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<TreeNode> currentLevel = new ArrayList<>();

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        currentLevel.add(node);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      if (level % 2 == 1) {
        int left = 0, right = currentLevel.size() - 1;
        while (left < right) {
          int temp = currentLevel.get(left).val;
          currentLevel.get(left).val = currentLevel.get(right).val;
          currentLevel.get(right).val = temp;
          left++;
          right--;
        }
      }
      level++;
    }

    return root;
  }
}
