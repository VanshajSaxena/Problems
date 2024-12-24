// https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * **Find Building Where Alice And Bob Can Meet**:
 *
 * You are given a **0-indexed** array `heights` of positive integers, where
 * `heights[i]` represents the height of the `ith` building.
 *
 * If a person is in building `i`, they can move to any other building `j` if
 * and only if `i < j` and `heights[i] < heights[j]`.
 *
 * You are also given another array `queries` where `queries[i] = [ai, bi]`. On
 * the `ith` query, Alice is in building `ai` while Bob is in building `bi`.
 *
 * Return _an array `ans` where `ans[i]` is **the index of the leftmost
 * building** where Alice and Bob can meet on the `ith` query. If Alice and Bob
 * cannot move to a common building on query `i`, set `ans[i]` to `-1`_.
 */
public class FindBuildingWhereAliceAndBobCanMeet {
  /**
   * Novice Way: TLE
   */
  public int[] leftmostBuildingQueriesI(int[] heights, int[][] queries) {
    int[] ans = new int[queries.length];

    Arrays.fill(ans, -1);
    for (int i = 0; i < queries.length; i++) {

      int alice = queries[i][0];
      int bob = queries[i][1];

      int atRight = Math.max(alice, bob);
      int atLeft = Math.min(alice, bob);
      if (atRight == atLeft) {
        ans[i] = atRight;
        continue;
      }
      for (int k = atRight; k < heights.length; k++) {
        if (heights[k] > heights[atLeft] && heights[k] >= heights[atRight]) {
          ans[i] = k;
          break;
        }
      }

    }

    return ans;
  }

  /**
   * [LeetCode
   * Solutions](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/solutions/4304269/java-c-python-priority-queue)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public int[] leftmostBuildingQueriesII(int[] A, int[][] queries) {
    int n = A.length, qn = queries.length;

    List<int[]>[] que = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      que[i] = new ArrayList();
    }

    PriorityQueue<int[]> h = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    int[] res = new int[qn];
    Arrays.fill(res, -1);
    // Step 1
    for (int qi = 0; qi < qn; qi++) {
      int i = queries[qi][0], j = queries[qi][1];
      if (i < j && A[i] < A[j]) {
        res[qi] = j;
      } else if (i > j && A[i] > A[j]) {
        res[qi] = i;
      } else if (i == j) {
        res[qi] = i;
      } else { // Step 2
        que[Math.max(i, j)].add(new int[] { Math.max(A[i], A[j]), qi });
      }
    }
    // Step 3
    for (int i = 0; i < n; i++) {
      while (!h.isEmpty() && h.peek()[0] < A[i]) {
        res[h.poll()[1]] = i;
      }
      for (int[] q : que[i]) {
        h.add(q);
      }
    }

    return res;
  }

  // https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/editorial/#approach-1-monotonic-stack
  // public int[] leftmostBuildingQueriesIII(int[] heights, int[][] queries) {
  // List<Pair<Integer, Integer>> monoStack = new ArrayList<>();
  // int[] result = new int[queries.length];
  // Arrays.fill(result, -1);
  // List<List<Pair<Integer, Integer>>> newQueries = new
  // ArrayList<>(heights.length);
  //
  // for (int i = 0; i < heights.length; i++) {
  // newQueries.add(new ArrayList<>());
  // }
  //
  // for (int i = 0; i < queries.length; i++) {
  // int a = queries[i][0];
  // int b = queries[i][1];
  // if (a > b) {
  // int temp = a;
  // a = b;
  // b = temp;
  // }
  // if (heights[b] > heights[a] || a == b) {
  // result[i] = b;
  // } else {
  // newQueries.get(b).add(new Pair<>(heights[a], i));
  // }
  // }
  //
  // for (int i = heights.length - 1; i >= 0; i--) {
  // int monoStackSize = monoStack.size();
  // for (Pair<Integer, Integer> pair : newQueries.get(i)) {
  // int position = search(pair.getKey(), monoStack);
  // if (position < monoStackSize && position >= 0) {
  // result[pair.getValue()] = monoStack
  // .get(position)
  // .getValue();
  // }
  // }
  //
  // while (!monoStack.isEmpty() &&
  // monoStack.get(monoStack.size() - 1).getKey() <= heights[i]) {
  // monoStack.remove(monoStack.size() - 1);
  // }
  //
  // monoStack.add(new Pair<>(heights[i], i));
  // }
  //
  // return result;
  // }
  //
  // private int search(int height, List<Pair<Integer, Integer>> monoStack) {
  // int left = 0;
  // int right = monoStack.size() - 1;
  // int ans = -1;
  // while (left <= right) {
  // int mid = (left + right) / 2;
  // if (monoStack.get(mid).getKey() > height) {
  // ans = Math.max(ans, mid);
  // left = mid + 1;
  // } else {
  // right = mid - 1;
  // }
  // }
  // return ans;
  // }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/editorial/#approach-2-priority-queue)
   */
  // REVISION: This needs revision.
  public int[] leftmostBuildingQueriesIV(int[] heights, int[][] queries) {
    List<List<List<Integer>>> storeQueries = new ArrayList<>(heights.length);

    for (int i = 0; i < heights.length; i++) {
      storeQueries.add(new ArrayList<>());
    }

    PriorityQueue<List<Integer>> maxIndex = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));
    int[] result = new int[queries.length];
    Arrays.fill(result, -1);

    // Store the mappings for all queries in storeQueries.
    for (int currQuery = 0; currQuery < queries.length; currQuery++) {
      int a = queries[currQuery][0], b = queries[currQuery][1];
      if (a < b && heights[a] < heights[b]) {
        result[currQuery] = b;
      } else if (a > b && heights[a] > heights[b]) {
        result[currQuery] = a;
      } else if (a == b) {
        result[currQuery] = a;
      } else {
        storeQueries
            .get(Math.max(a, b))
            .add(Arrays.asList(Math.max(heights[a], heights[b]), currQuery));
      }
    }

    // If the priority queue's minimum pair value is less than the current index of
    // height, it is an answer to the query.
    for (int index = 0; index < heights.length; index++) {
      while (!maxIndex.isEmpty() && maxIndex.peek().get(0) < heights[index]) {
        result[maxIndex.peek().get(1)] = index;
        maxIndex.poll();
      }
      // Push the with their maximum index as the current index in the priority queue.
      for (List<Integer> element : storeQueries.get(index)) {
        maxIndex.offer(element);
      }
    }
    return result;
  }
}
