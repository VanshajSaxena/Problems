// https://leetcode.com/problems/network-delay-time/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * **Network Delay Time**:
 *
 * You are given a network of `n` nodes, labeled from `1` to `n`. You are also
 * given `times`, a list of travel times as directed edges `times[i] = (ui, vi,
 * wi)`, where `ui` is the source node, `vi` is the target node, and `wi` is the
 * time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node `k`. Return _the **minimum** time it
 * takes for all the `n` nodes to receive the signal_. If it is impossible for
 * all the `n` nodes to receive the signal, return `-1`.
 */

public class NetworkDelayTime {
  public int networkDelayTimeI(int[][] times, int n, int source) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

    for (int[] time : times) {
      graph.putIfAbsent(time[0], new HashMap<Integer, Integer>());
      graph.get(time[0]).put(time[1], time[2]);
    }

    // Priority queue to store [distance, node] pairs.
    Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]); // Sort by distance.
    queue.add(new int[] { 0, source });

    boolean[] visited = new boolean[n + 1];
    int minTime = 0;

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int currNode = curr[1];
      int currDist = curr[0];

      if (visited[currNode]) {
        continue;
      }

      visited[currNode] = true;
      minTime = currDist;
      n--;

      if (graph.containsKey(currNode)) {
        Map<Integer, Integer> neighbors = graph.get(currNode);
        for (int next : neighbors.keySet()) {
          queue.add(new int[] { currDist + neighbors.get(next), next });
        }
      }
    }

    return n == 0 ? minTime : -1;
  }

  public int networkDelayTimeII(int[][] times, int n, int k) {
    // Step 1: Build the graph
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int i = 1; i <= n; i++) {
      graph.put(i, new ArrayList<>());
    }
    for (int[] time : times) {
      graph.get(time[0]).add(new int[] { time[1], time[2] }); // {target, weight}
    }

    // Step 2: Initialize distance array and priority queue
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[k] = 0; // Distance to the source node is 0
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // {node, distance}
    pq.offer(new int[] { k, 0 });

    // Step 3: Process the graph
    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      int node = current[0];
      int time = current[1];

      if (time > dist[node])
        continue; // Skip if we already found a shorter path

      for (int[] neighbor : graph.get(node)) {
        int nextNode = neighbor[0];
        int weight = neighbor[1];
        if (dist[node] + weight < dist[nextNode]) {
          dist[nextNode] = dist[node] + weight;
          pq.offer(new int[] { nextNode, dist[nextNode] });
        }
      }
    }

    // Step 4: Find the maximum distance
    int maxTime = 0;
    for (int i = 1; i <= n; i++) {
      if (dist[i] == Integer.MAX_VALUE)
        return -1; // Unreachable node
      maxTime = Math.max(maxTime, dist[i]);
    }

    return maxTime;
  }
}
