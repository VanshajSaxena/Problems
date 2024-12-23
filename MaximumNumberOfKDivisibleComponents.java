// https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * **Maximum Number Of K Divisible Components**:
 *
 * There is an undirected tree with `n` nodes labeled from `0` to `n - 1`. You
 * are given the integer `n` and a 2D integer array `edges` of length `n - 1`,
 * where `edges[i] = [ai, bi]` indicates that there is an edge between nodes
 * `ai` and `bi` in the tree.
 *
 * You are also given a **0-indexed** integer array `values` of length `n`,
 * where
 * `values[i]` is the **value** associated with the `ith` node, and an integer
 * `k`.
 *
 * A **valid split** of the tree is obtained by removing any set of edges,
 * possibly empty, from the tree such that the resulting components all have
 * values that are divisible by `k`, where the **value of a connected
 * component** is the sum of the values of its nodes.
 *
 * Return _the **maximum number of components** in any valid split_.
 */

public class MaximumNumberOfKDivisibleComponents {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/maximum-number-of-k-divisible-components/editorial/#approach-1-depth-first-search-dfs)
   */
  public int maxKDivisibleComponentsI(int n, int[][] edges, int[] values, int k) {
    @SuppressWarnings("unchecked")
    List<Integer>[] adjacencyLists = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      adjacencyLists[i] = new ArrayList<Integer>();
    }

    for (int[] edge : edges) {
      int node1 = edge[0];
      int node2 = edge[1];
      adjacencyLists[node1].add(node2);
      adjacencyLists[node2].add(node1);
    }

    int[] connectedComponents = new int[1];

    calculateValidSplits(0, -1, adjacencyLists, connectedComponents, k, values);

    return connectedComponents[0];
  }

  private int calculateValidSplits(int currentNode, int parentNode, List<Integer>[] adjacencyLists,
      int[] connectedComponents, int k,
      int[] values) {

    int sum = 0;

    for (int neighborNode : adjacencyLists[currentNode]) {
      if (neighborNode != parentNode) {
        sum += calculateValidSplits(neighborNode,
            currentNode,
            adjacencyLists,
            connectedComponents,
            k,
            values);

        sum %= k;
      }
    }

    sum += values[currentNode];
    sum %= k;

    if (sum == 0) {
      connectedComponents[0]++;
    }

    return sum;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/maximum-number-of-k-divisible-components/editorial/#approach-2-breadth-first-search-bfs)
   */
  public int maxKDivisibleComponentsII(int n, int[][] edges, int[] values, int k) {

    // Base case: if there are less than 2 nodes, return 1
    if (n < 2)
      return 1;

    int componentCount = 0;
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    // Step 1: Build the graph
    for (int[] edge : edges) {
      int node1 = edge[0], node2 = edge[1];
      graph.computeIfAbsent(node1, key -> new HashSet<>()).add(node2);
      graph.computeIfAbsent(node2, key -> new HashSet<>()).add(node1);
    }

    // Convert values to long to prevent overflow
    long[] longValues = new long[values.length];
    for (int i = 0; i < values.length; i++) {
      longValues[i] = values[i];
    }

    // Step 2: Initialize the BFS queue with leaf nodes (nodes with only one
    // connection)
    Queue<Integer> queue = new LinkedList<>();
    for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
      if (entry.getValue().size() == 1) {
        queue.add(entry.getKey());
      }
    }

    // Step 3: Process nodes in BFS order
    while (!queue.isEmpty()) {
      int currentNode = queue.poll();

      // Find the neighbor node
      int neighborNode = -1;
      if (graph.get(currentNode) != null &&
          !graph.get(currentNode).isEmpty()) {
        neighborNode = graph.get(currentNode).iterator().next();
      }

      if (neighborNode >= 0) {
        // Remove the edge between current and neighbor
        graph.get(neighborNode).remove(currentNode);
        graph.get(currentNode).remove(neighborNode);
      }

      // Check divisibility of the current node's value
      if (longValues[currentNode] % k == 0) {
        componentCount++;
      } else if (neighborNode >= 0) {
        // Add current node's value to the neighbor
        longValues[neighborNode] += longValues[currentNode];
      }

      // If the neighbor becomes a leaf node, add it to the queue
      if (neighborNode >= 0 &&
          graph.get(neighborNode) != null &&
          graph.get(neighborNode).size() == 1) {
        queue.add(neighborNode);
      }
    }

    return componentCount;
  }
}
