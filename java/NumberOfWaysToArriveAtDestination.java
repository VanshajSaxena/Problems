// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NumberOfWaysToArriveAtDestination {
  public int countPaths(int n, int[][] roads) {
    List<List<int[]>> graph = new ArrayList<>();
    final int MOD = 1_000_000_007;

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    // Build Graph.
    for (int[] road : roads) {
      graph.get(road[0]).add(new int[] { road[1], road[2] });
      graph.get(road[1]).add(new int[] { road[1], road[2] });
    }

    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

    queue.add(new int[] { 0, 0 }); // distance, node

    long[] distance = new long[n];
    Arrays.fill(distance, Long.MAX_VALUE);
    int[] ways = new int[n];

    ways[0] = 1;

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();

      int currDist = curr[0];
      int currNode = curr[1];

      if (currDist > distance[currNode]) {
        continue;
      }

      for (int[] neighbor : graph.get(currNode)) {
        int neighborNode = neighbor[1];
        long neighborDist = neighbor[0];

        long newDist = neighborDist + currDist;

        if (newDist < distance[neighborNode]) {
          distance[neighborNode] = newDist;
          ways[neighborNode] = ways[currNode];
          queue.add(new int[] { (int) newDist, neighborNode });
        } else if (newDist == distance[neighborNode]) {
          ways[neighborNode] = (ways[currNode] + ways[neighborNode]) % MOD;
        }
      }
    }

    return ways[n - 1];
  }
}
