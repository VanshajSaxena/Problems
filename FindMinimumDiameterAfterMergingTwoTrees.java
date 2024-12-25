// https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/description/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindMinimumDiameterAfterMergingTwoTrees {
  public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
    int m = edges1.length + 1, n = edges2.length + 1;

    List<List<Integer>> adjList1 = getAdjList(m, edges1);
    List<List<Integer>> adjList2 = getAdjList(n, edges2);

    int diameter1 = findDiameter(m, adjList1);
    int diameter2 = findDiameter(n, adjList2);

    int commonDiameter = (int) Math.ceil(diameter1 / 2.0) + (int) Math.ceil(diameter2 / 2.0) + 1;

    return Math.max(commonDiameter, Math.max(diameter1, diameter2));
  }

  private int findDiameter(int size, List<List<Integer>> adjList) {
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
}
