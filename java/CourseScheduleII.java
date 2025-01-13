// https://leetcode.com/problems/course-schedule-ii/description

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * **Course Schedule II**:
 *
 * There are a total of `numCourses` courses you have to take, labeled from 0 to
 * `numCourses - 1`. You are given an array prerequisites where
 * `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi`
 * first if you want to take course `ai`.
 *
 * - For example, the pair `[0, 1]`, indicates that to take course `0` you have
 * to first take course `1`.
 *
 * Return _the ordering of courses you should take to finish all courses_. If
 * there are many valid answers, return **any** of them. If it is impossible to
 * finish all courses, return **an empty array**.
 */

public class CourseScheduleII {
  public int[] findOrder(int n, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    int[] indegree = new int[n];
    initializeGraph(indegree, graph, prerequisites);
    return solveByBFS(indegree, graph);
  }

  private int[] solveByBFS(int[] indegree, List<List<Integer>> graph) {
    Queue<Integer> toVisit = new ArrayDeque<>();
    int[] order = new int[indegree.length];

    for (int i = 0; i < indegree.length; i++) {
      if (indegree[i] == 0) {
        toVisit.add(i);
      }
    }

    // Fill the array from the back, in reverse.
    int visited = indegree.length;
    while (!toVisit.isEmpty()) {
      int from = toVisit.poll();
      order[visited--] = from;
      for (int to : graph.get(from)) {
        if (--indegree[to] == 0) {
          toVisit.offer(to);
        }
      }
    }

    return visited == -1 ? order : new int[0];
  }

  private void initializeGraph(int[] indegree, List<List<Integer>> graph, int[][] prerequisites) {
    for (int i = 0; i < indegree.length; i++) {
      graph.add(new ArrayList<Integer>());
    }
    for (int[] edge : prerequisites) {
      // edge[0] -> edge[1]
      graph.get(edge[0]).add(edge[1]);
      indegree[edge[1]]++;
    }
  }
}
