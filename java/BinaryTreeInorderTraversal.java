// https://leetcode.com/problems/binary-tree-inorder-traversal/description/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * **Binary Tree Postorder Traversal**:
 *
 * Given the `root` of a binary tree, return _the inorder traversal of its
 * nodes' values_.
 */

public class BinaryTreeInorderTraversal {
  /**
   * Recursive Solution
   */
  public List<Integer> inorderTraversalI(TreeNode root) {
    ArrayList<Integer> visited = new ArrayList<>();
    if (root == null) {
      return visited;
    }
    traverse(root, visited);
    return visited;
  }

  private void traverse(TreeNode root, ArrayList<Integer> visited) {
    if (root.left != null) {
      traverse(root.left, visited);
    }

    visited.add(root.val);

    if (root.right != null) {
      traverse(root.right, visited);
    }
  }

  /**
   * Iterative Solution
   */
  public List<Integer> inorderTraversalII(TreeNode root) {
    ArrayList<Integer> visited = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();

      visited.add(root.val);
      root = root.right;
    }

    return visited;
  }
}
