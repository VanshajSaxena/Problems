// https://leetcode.com/problems/binary-tree-preorder-traversal/description/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * **Binary Tree Preorder Traversal**:
 *
 * Given the `root` of a binary tree, return _the preorder traversal of its
 * nodes' values_.
 */

public class BinaryTreePreorderTraversal {
  /**
   * Recursive Solution
   */
  public List<Integer> preorderTraversalI(TreeNode root) {
    ArrayList<Integer> visited = new ArrayList<>();
    if (root == null) {
      return visited;
    }
    traverse(root, visited);
    return visited;
  }

  private void traverse(TreeNode root, ArrayList<Integer> visited) {
    visited.add(root.val);

    if (root.left != null) {
      traverse(root.left, visited);
    }

    if (root.right != null) {
      traverse(root.right, visited);
    }
  }

  /**
   * Iterative Solution
   */
  public List<Integer> preorderTraversalII(TreeNode root) {
    ArrayList<Integer> visited = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    if (root != null) {
      stack.push(root);
    }

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      visited.add(node.val);

      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }

    return visited;
  }
}
