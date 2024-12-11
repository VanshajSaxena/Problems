// https://leetcode.com/problems/task-scheduler/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * **Task Scheduler**:
 *
 * You are given an array of CPU `tasks`, each labeled with a letter from A to
 * Z, and a number `n`. Each CPU interval can be idle or allow the completion of
 * one task. Tasks can be completed in any order, but there's a constraint:
 * there has to be a gap of **at least** `n` intervals between two tasks with
 * the same label.
 *
 * Return the **minimum** number of CPU intervals required to complete all
 * tasks.
 */

public class TaskScheduler {
  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/task-scheduler/editorial/#approach-1-using-priority-queue--max-heap)
   */
  public int leastIntervalI(char[] tasks, int n) {
    int[] freq = new int[26];

    // Map to store the frequencies of `tasks`.
    for (int task : tasks) {
      freq[task - 'A']++;
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    // Only add the frequencies of the `tasks` as we do not need to know which tasks
    // has the highest frequency, rather we only need to know at each cycle what is
    // the next hightest frequency.
    for (int i = 0; i < freq.length; i++) {
      if (freq[i] > 0) {
        pq.offer(freq[i]);
      }
    }

    int time = 0;

    while (!pq.isEmpty()) {
      int cycle = n + 1;
      ArrayList<Integer> store = new ArrayList<>();
      int taskCounter = 0;
      while (cycle-- > 0 && !pq.isEmpty()) {
        int currentFreq = pq.poll();
        if (currentFreq > 1) {
          store.add(currentFreq - 1);
        }
        taskCounter++;
      }
      // Store back the remaining uncompleted tasks to the `pq` so they can be
      // scheduled again.
      store.forEach(pq::offer);
      // Add time for the completed cycle.
      time += pq.isEmpty() ? taskCounter : cycle;
    }

    return time;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/task-scheduler/editorial/#approach-2-filling-the-slots-and-sorting)
   */
  public int leastIntervalII(char[] tasks, int n) {
    int[] freq = new int[26];

    for (char task : tasks) {
      freq[task - 'A']++;
    }

    Arrays.sort(freq);

    int maxFreq = freq[25] - 1;

    int idelSlots = maxFreq * n;

    for (int i = 24; i >= 0; i--) {
      idelSlots -= Math.min(maxFreq, freq[i]);
    }

    return idelSlots > 0 ? tasks.length + idelSlots : tasks.length;
  }

  /**
   * [LeetCode
   * Editorial](https://leetcode.com/problems/task-scheduler/editorial/#approach-3-greedy-approach)
   */
  public int leastIntervalIII(char[] tasks, int n) {

    int[] counter = new int[26];
    int maximum = 0;
    int maxCount = 0;

    for (char task : tasks) {
      counter[task - 'A']++;
      if (maximum < counter[task - 'A']) {
        maximum = counter[task - 'A'];
        maxCount = 1;
      } else if (maximum == counter[task - 'A']) {
        maxCount++;
      }
    }

    int partCount = maximum - 1;
    int partLength = n - (maxCount - 1);
    int emptySlots = partCount * partLength;
    int availableTasks = tasks.length - maximum * maxCount;
    int idles = Math.max(0, emptySlots - availableTasks);

    return tasks.length + idles;
  }
}
