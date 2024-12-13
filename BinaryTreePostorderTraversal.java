// https://leetcode.com/problems/binary-tree-postorder-traversal/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * **Binary Tree Postorder Traversal**:
 *
 * Given the `root` of a binary tree, return _the postorder traversal of its
 * nodes' values_.
 */

public class BinaryTreePostorderTraversal {
  /**
   * Recursive Solution
   */
  public List<Integer> postorderTraversalI(TreeNode root) {
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

    if (root.right != null) {
      traverse(root.right, visited);
    }

    visited.add(root.val);
  }

  /**
   * Iterative Solution I
   */
  public List<Integer> postorderTraversalII(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Stack<TreeNode> stack = new Stack<>();
    Stack<TreeNode> output = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      output.push(node);
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }
    while (!output.isEmpty()) {
      result.add(output.pop().val);
    }
    return result;
  }

  /**
   * Iterative Solution II
   */
  public List<Integer> postorderTraversalIII(TreeNode root) {
    LinkedList<Integer> visited = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    if (root == null)
      return visited;

    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode curr = stack.pop();
      visited.addFirst(curr.val);
      if (curr.left != null) {
        stack.push(curr.left);
      }
      if (curr.right != null) {
        stack.push(curr.right);
      }
    }
    return visited;
  }
}
