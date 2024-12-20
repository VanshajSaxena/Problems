// https://leetcode.com/problems/clone-graph/description/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * **Clone Graph**:
 *
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (`int`) and a list (`List[Node]`) of
 * its neighbors.
 *
 * ```
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * ```
 */

public class CloneGraph {
  // map [original : cloned]
  HashMap<Node, Node> map = new HashMap<Node, Node>();

  public Node cloneGraphI(Node node) {
    if (node == null)
      return null;
    HashMap<Node, Node> map = new HashMap<>();
    Queue<Node> queue = new LinkedList<Node>();

    queue.offer(node);

    map.put(node, new Node(node.val));

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      for (Node neighbor : current.neighbors) {
        if (!map.containsKey(neighbor)) {
          map.put(neighbor, new Node(neighbor.val));

          queue.offer(neighbor);
        }

        map.get(current).neighbors.add(map.get(neighbor));
      }
    }

    return map.get(node);
  }

  public Node cloneGraphII(Node node) {
    return clone(node);
  }

  private Node clone(Node node) {
    if (node == null)
      return null;

    if (map.containsKey(node)) {
      return map.get(node);
    }

    Node clone = new Node(node.val);

    map.put(node, clone);

    for (Node neighbor : node.neighbors) {
      clone.neighbors.add(clone(neighbor));
    }

    return clone;
  }
}
