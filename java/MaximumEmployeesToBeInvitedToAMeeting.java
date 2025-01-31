// https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * **Maximum Employees To Be Invited To A Meeting**:
 *
 * A company is organizing a meeting and has a list of `n` employees, waiting to
 * be invited. They have arranged for a large **circular** table, capable of
 * seating **any number** of employees.
 *
 * The employees are numbered from `0` to `n - 1`. Each employee has a
 * **favorite** person and they will attend the meeting **only if** they can sit
 * next to their favorite person at the table. The favorite person of an
 * employee is not themself.
 *
 * Given a **0-indexed** integer array `favorite`, where `favorite[i]` denotes
 * the favorite person of the `ith` employee, return _the **maximum number of
 * employees** that can be invited to the meeting_.
 */
// REVISION:
public class MaximumEmployeesToBeInvitedToAMeeting {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/editorial)
   */
  public int maximumInvitations(int[] favorite) {
    int n = favorite.length;
    Map<Integer, List<Integer>> adj = new HashMap<>();

    for (int person = 0; person < n; person++) {
      int favoritePerson = favorite[person];
      adj.putIfAbsent(favoritePerson, new ArrayList<>());
      adj.get(favoritePerson).add(person);
    }

    int longestCycle = 0;
    int twoCycleInvitations = 0;
    boolean[] visited = new boolean[n];

    for (int person = 0; person < n; person++) {
      if (!visited[person]) {
        Map<Integer, Integer> visitedPersons = new HashMap<>();
        int currentPerson = person;
        int distance = 0;
        while (true) {
          if (visited[currentPerson]) {
            break;
          }
          visited[currentPerson] = true;
          visitedPersons.put(currentPerson, distance++);
          int nextPerson = favorite[currentPerson];

          if (visitedPersons.containsKey(nextPerson)) {
            int cycleLength = distance - visitedPersons.get(nextPerson);
            longestCycle = Math.max(longestCycle, cycleLength);
            if (cycleLength == 2) {
              Set<Integer> visitedNodes = new HashSet<>();
              visitedNodes.add(currentPerson);
              visitedNodes.add(nextPerson);
              twoCycleInvitations += 2 + bfs(nextPerson, visitedNodes, adj) + bfs(currentPerson, visitedNodes, adj);
            }
            break;
          }
          currentPerson = nextPerson;
        }
      }
    }
    return Math.max(longestCycle, twoCycleInvitations);
  }

  private int bfs(int startNode, Set<Integer> visitedNodes, Map<Integer, List<Integer>> adj) {
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] { startNode, 0 });
    int maxDistance = 0;
    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int currentNode = current[0];
      int currentDistance = current[1];
      for (int neighbor : adj.getOrDefault(currentNode, new ArrayList<>())) {
        if (visitedNodes.contains(neighbor)) {
          continue;
        }
        visitedNodes.add(neighbor);
        queue.offer((new int[] { neighbor, currentDistance + 1 }));
        maxDistance = Math.max(maxDistance, currentDistance + 1);
      }
    }
    return maxDistance;
  }
}
