# https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/

from collections import defaultdict
import heapq
from typing import List


class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance:
    def __init__(self):
        self.graph = defaultdict(list)

    # Dijkstra's Algorithm
    def findTheCityI(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        self.buildGraph(edges)

        minCity = (-1, float("inf"))
        for city in range(0, n):
            reach = self.dijkstra(distanceThreshold, n, city)

            if reach < minCity[1]:
                minCity = (city, reach)
            elif reach == minCity[1]:
                if minCity[0] < city:
                    minCity = (city, reach)

        return minCity[0]

    def dijkstra(self, distanceThreshold: int, n: int, source: int) -> int:
        distance = [float("inf")] * n
        distance[source] = 0
        pq = [(0, source)]  # (distance, node)

        while pq:
            curr_dist, curr_node = heapq.heappop(pq)

            if curr_dist > distance[curr_node]:
                continue

            for neighbor, cost in self.graph[curr_node]:
                if curr_dist + cost < distance[neighbor]:
                    distance[neighbor] = curr_dist + cost
                    heapq.heappush(pq, (curr_dist + cost, neighbor))

        count = 0

        for dist in distance:
            if dist <= distanceThreshold:
                count += 1

        return count - 1

    def buildGraph(self, edges):
        self.graph.clear()
        for u, v, cost in edges:
            self.graph[u].append((v, cost))
            self.graph[v].append((u, cost))

    # Floyed-Warshall Approach
    def findTheCityII(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        INF = float("inf")
        distance = [[INF] * n for _ in range(n)]

        for i in range(n):
            distance[i][i] = 0

        for start, end, weight in edges:
            distance[start][end] = weight
            distance[end][start] = weight

        for via in range(n):
            for i in range(n):
                for j in range(n):
                    distance[i][j] = min(
                        distance[i][j], distance[i][via] + distance[via][j]
                    )

        # FIX: Need a fix not accepted.
        min_count = INF
        minCity = -1
        for source in range(n):
            count = 0
            for destination in range(n):
                if (
                    source != destination
                    and distance[source][destination] <= distanceThreshold
                ):
                    count += 1

                if count < min_count or (count == min_count and source > minCity):
                    min_count = count
                    minCity = source
        return minCity
