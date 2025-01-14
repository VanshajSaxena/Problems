# https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/

from collections import defaultdict
from typing import List
import heapq


class NumberOfWaysToArriveAtDestination:
    # Find all the shortest paths from node `0` to node `n - 1` and return their count.
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        graph = defaultdict(list)

        for u, v, time in roads:
            graph[u].append((v, time))
            graph[v].append((u, time))

        distance = [float("inf")] * n
        ways = [0] * n

        pq = [(0, 0)]  # distance, node

        ways[0] = 1
        distance[0] = 0

        while pq:
            curr_dist, curr_node = heapq.heappop(pq)

            if curr_dist > distance[curr_node]:
                continue

            for neighbor, time in graph[curr_node]:
                new_dist = curr_dist + time

                if new_dist < distance[neighbor]:
                    distance[neighbor] = new_dist
                    ways[neighbor] = ways[curr_node]
                    heapq.heappush(pq, (new_dist, neighbor))
                elif new_dist == distance[neighbor]:
                    ways[neighbor] = (ways[neighbor] + ways[curr_node]) % (10**9 + 7)

        return ways[n - 1]
