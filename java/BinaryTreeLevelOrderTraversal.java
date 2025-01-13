// https://leetcode.com/problems/binary-tree-level-order-traversal/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * **Binary Tree Level Order Traversal**:
 *
 * Given the `root` of a binary tree, return _the level order traversal of its
 * nodes' values_. (i.e., from left to right, level by level).
 */

public class BinaryTreeLevelOrderTraversal {
  public List<List<Integer>> levelOrderI(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    Queue<TreeNode> upperQueue = new LinkedList<>();
    Queue<TreeNode> lowerQueue = new LinkedList<>();
    TreeNode node = root;
    if (root == null) {
      return result;
    }
    upperQueue.add(node);
    do {
      ArrayList<Integer> level = new ArrayList<>();
      lowerQueue = new LinkedList<>();
      while (!upperQueue.isEmpty()) {
        node = upperQueue.poll();
        level.add(node.val);
        if (node.left != null) {
          lowerQueue.offer(node.left);
        }
        if (node.right != null) {
          lowerQueue.offer(node.right);
        }
      }
      result.add(level);
      if (!lowerQueue.isEmpty()) {
        upperQueue = new LinkedList<>(lowerQueue);
      }
    } while (!lowerQueue.isEmpty());

    return result;
  }

  public List<List<Integer>> levelOrderII(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size(); // Key improvement: capture current level's size
      List<Integer> currentLevel = new ArrayList<>();

      // Process exactly the number of nodes in this level
      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        currentLevel.add(node.val);

        // Enqueue children for next level
        if (node.left != null)
          queue.offer(node.left);
        if (node.right != null)
          queue.offer(node.right);
      }

      result.add(currentLevel);
    }

    return result;
  }
}
