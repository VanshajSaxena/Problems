// https://leetcode.com/problems/is-graph-bipartite/description/

/**
 * **Is Graph Bipartite**:
 *
 * There is an **undirected** graph with `n` nodes, where each node is numbered
 * between `0` and `n - 1`. You are given a 2D array `graph`, where `graph[u]`
 * is an array of nodes that node `u` is adjacent to. More formally, for each
 * `v` in `graph[u]`, there is an undirected edge between node `u` and node `v`.
 * The graph has the following properties:
 *
 * - There are no self-edges (`graph[u]` does not contain `u`).
 * - There are no parallel edges (`graph[u]` does not contain duplicate values).
 * - If `v` is in `graph[u]`, then `u` is in `graph[v]` (the graph is
 * undirected).
 * - The graph may not be connected, meaning there may be two nodes `u` and `v`
 * such that there is no path between them.
 *
 * A graph is **bipartite** if the nodes can be partitioned into two independent
 * sets `A` and `B` such that **every** edge in the graph connects a node in set
 * `A` and a node in set `B`.
 *
 * Return `true` _if and only if it is **bipartite**_.
 */

public class IsGraphBipartite {
  public boolean isBipartiteI(int[][] graph) {
    int length = graph.length, colors[] = new int[length];

    for (int i = 0; i < length; i++) {
      if (colors[i] == 0 && !isValidColor(graph, colors, i, 1)) {
        return false;
      }
    }
    return true;
  }

  private boolean isValidColor(int[][] graph, int[] colors, int node, int color) {
    colors[node] = color;
    for (int neighbor : graph[node]) {
      if (colors[neighbor] == -color) {
        continue;
      }
      if (colors[neighbor] == color || !isValidColor(graph, colors, neighbor, -color)) {
        return false;
      }
    }
    return true;
  }

  // BOOKMARK: Needs implementation.
  public boolean isBipartiteII(int[][] graph) {
    return true;
  }
}
