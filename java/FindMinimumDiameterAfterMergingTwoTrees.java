// https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindMinimumDiameterAfterMergingTwoTrees {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/editorial/#approach-1-farthest-of-farthest-bfs)
   */
  public int minimumDiameterAfterMergeI(int[][] edges1, int[][] edges2) {
    int m = edges1.length + 1, n = edges2.length + 1;

    List<List<Integer>> adjList1 = getAdjList(m, edges1);
    List<List<Integer>> adjList2 = getAdjList(n, edges2);

    int diameter1 = findDiameterI(m, adjList1);
    int diameter2 = findDiameterI(n, adjList2);

    int commonDiameter = (int) Math.ceil(diameter1 / 2.0) + (int) Math.ceil(diameter2 / 2.0) + 1;

    return Math.max(commonDiameter, Math.max(diameter1, diameter2));
  }

  private int findDiameterI(int size, List<List<Integer>> adjList) {
    Pair firstBFS = findFarthestNode(size, adjList, 0);
    int firstFarthestNode = firstBFS.getFirst();

    Pair secondBFS = findFarthestNode(size, adjList, firstFarthestNode);

    return secondBFS.getSecond();
  }

  private Pair findFarthestNode(int n, List<List<Integer>> adjList, int sourceNode) {
    Queue<Integer> queue = new LinkedList<>();

    boolean[] visited = new boolean[n];

    queue.add(sourceNode);
    visited[sourceNode] = true;

    int maxDistance = 0, farthestNode = sourceNode;
    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        int currNode = queue.poll();

        farthestNode = currNode;

        for (int neighbor : adjList.get(currNode)) {
          if (!visited[neighbor]) {
            visited[neighbor] = true;
            queue.add(neighbor);
          }
        }
      }
      if (!queue.isEmpty()) {
        maxDistance++;
      }
    }

    return new Pair(farthestNode, maxDistance);
  }

  private List<List<Integer>> getAdjList(int size, int[][] edges) {
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      adjList.add(new ArrayList<>());
    }
    for (int i = 0; i < edges.length; i++) {
      adjList.get(edges[i][0]).add(edges[i][1]);
      adjList.get(edges[i][1]).add(edges[i][0]);
    }
    return adjList;
  }

  // REVISION: This needs revision.
  public int minimumDiameterAfterMergeII(int[][] edges1, int[][] edges2) {
    int m = edges1.length, n = edges2.length;

    List<List<Integer>> adjList1 = getAdjList(m, edges1);
    List<List<Integer>> adjList2 = getAdjList(n, edges2);

    int diameter1 = findDiameterII(adjList1, 0, -1).getFirst();
    int diameter2 = findDiameterII(adjList2, 0, -1).getFirst();

    int commonDiameter = (int) Math.ceil(diameter1 / 2.0) + (int) Math.ceil(diameter2 / 2.0) + 1;

    return Math.max(commonDiameter, Math.max(diameter1, diameter2));
  }

  private Pair findDiameterII(List<List<Integer>> adjList, int node, int parent) {
    int maxDepth1 = 0, maxDepth2 = 0;
    int diameter = 0;

    for (int neighbor : adjList.get(node)) {
      if (node == parent) {
        continue;
      }

      Pair result = findDiameterII(adjList, neighbor, node);
      int childDiameter = result.getFirst();
      int depth = result.getSecond() + 1;

      diameter = Math.max(diameter, childDiameter);

      if (depth > maxDepth1) {
        maxDepth2 = maxDepth1;
        maxDepth1 = depth;
      } else if (depth > maxDepth2) {
        maxDepth2 = depth;
      }
    }

    diameter = Math.max(diameter, maxDepth1 + maxDepth2);

    return new Pair(diameter, maxDepth1);
  }

  // MARK: Pending.
  public int minimumDiameterAfterMergeIII(int[][] edges1, int[][] edges2) {
    return 0;
  }
}
