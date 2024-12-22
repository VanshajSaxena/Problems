// https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/

import java.util.ArrayList;
import java.util.List;

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

  // MARK: Pending.
  public int maxKDivisibleComponentsII(int n, int[][] edges, int[] values, int k) {

  }
}
