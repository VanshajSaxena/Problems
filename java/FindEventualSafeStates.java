// https://leetcode.com/problems/find-eventual-safe-states/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * **Find Eventual Safe States**:
 *
 * There is a directed graph of `n` nodes with each node labeled from `0` to `n
 * 1`. The graph is represented by a **0-indexed** 2D integer array `graph`
 * where `graph[i]` is an integer array of nodes adjacent to node `i`, meaning
 * there is an edge from node `i` to each node in `graph[i]`.
 *
 * A node is a **terminal node** if there are no outgoing edges. A node is a
 * **safe node** if every possible path starting from that node leads to a
 * **terminal node** (or another safe node).
 *
 * Return _an array containing all the **safe nodes** of the graph_. The answer
 * should be sorted in **ascending** order.
 */

public class FindEventualSafeStates {
  public List<Integer> eventualSafeNodesI(int[][] graph) {
    int n = graph.length, inDegree[] = new int[n];
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      for (int node : graph[i]) {
        adj.get(node).add(i);
        inDegree[i]++;
      }
    }
    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    boolean[] safe = new boolean[n];
    while (!queue.isEmpty()) {
      int node = queue.poll();
      safe[node] = true;

      for (int neighbor : adj.get(node)) {
        if (--inDegree[neighbor] == 0) {
          queue.add(neighbor);
        }
      }
    }

    List<Integer> safeNodes = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (safe[i]) {
        safeNodes.add(i);
      }
    }
    return safeNodes;
  }

  public List<Integer> eventualSafeNodesII(int[][] graph) {
    int n = graph.length;
    boolean[] visit = new boolean[n];
    boolean[] inStack = new boolean[n];

    for (int i = 0; i < n; i++) {
      dfs(i, graph, visit, inStack);
    }

    List<Integer> safeNodes = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (!inStack[i]) {
        safeNodes.add(i);
      }
    }

    return safeNodes;
  }

  private boolean dfs(int node, int[][] graph, boolean[] visit, boolean[] inStack) {
    if (inStack[node]) {
      return true;
    }
    if (visit[node]) {
      return false;
    }

    visit[node] = true;
    for (int neighbor : graph[node]) {
      if (dfs(neighbor, graph, visit, inStack)) {
        return true;
      }
    }

    inStack[node] = false;
    return false;
  }
}
