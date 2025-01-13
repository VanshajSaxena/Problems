// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {

  class CityStopCostTriplet implements Comparable<CityStopCostTriplet> {
    int stop;
    int city;
    int cost;

    CityStopCostTriplet(int city, int stop, int cost) {
      this.city = city;
      this.stop = stop;
      this.cost = cost;
    }

    public int compareTo(CityStopCostTriplet triplet) {
      return Integer.compare(this.cost, triplet.cost);
    }
  }

  class CityCost {
    int city;
    int cost;

    CityCost(int city, int cost) {
      this.city = city;
      this.cost = cost;
    }
  }

  public int findCheapestPriceII(int n, int[][] flights, int src, int dst, int k) {
    int visited[] = new int[n];
    Arrays.fill(visited, -1);
    Map<Integer, List<CityCost>> map = new HashMap<>();
    for (int[] flight : flights) {
      if (!map.containsKey(flight[0])) {
        map.put(flight[0], new ArrayList<>());
      }
      map.get(flight[0]).add(new CityCost(flight[1], flight[2]));
    }
    PriorityQueue<CityStopCostTriplet> pq = new PriorityQueue<>();
    pq.offer(new CityStopCostTriplet(src, 0, 0));
    while (!pq.isEmpty()) {
      CityStopCostTriplet triplet = pq.poll();
      if (triplet.city == dst) {
        return triplet.cost;
      }
      if (triplet.stop > k) {
        continue;
      }
      if (visited[triplet.city] != -1 && visited[triplet.city] < triplet.stop) {
        continue;
      }
      visited[triplet.city] = triplet.stop;
      if (map.containsKey(triplet.city))
        for (CityCost neighbor : map.get(triplet.city)) {
          pq.offer(new CityStopCostTriplet(neighbor.city, triplet.stop + 1, triplet.cost + neighbor.cost));
        }
    }
    return -1;
  }
}
