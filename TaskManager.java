// https://leetcode.com/problems/design-task-manager/description/

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * **Task Manager**:
 *
 * There is a task management system that allows users to manage their tasks,
 * each associated with a priority. The system should efficiently handle adding,
 * modifying, executing, and removing tasks.
 *
 * Implement the `TaskManager` class:
 *
 * - `TaskManager(vector<vector<int>>& tasks)` initializes the task manager with
 * a list of user-task-priority triples. Each element in the input list is of
 * the form `[userId, taskId, priority]`, which adds a task to the specified
 * user with the given priority.
 * - `void add(int userId, int taskId, int priority)` adds a task with the
 * specified `taskId` and `priority` to the user with `userId`. It is
 * **guaranteed** that `taskId` does not _exist_ in the system.
 * - `void edit(int taskId, int newPriority)` updates the priority of the
 * existing `taskId` to `newPriority`. It is **guaranteed** that `taskId`
 * _exists_ in the system.
 * - `void rmv(int taskId)` removes the task identified by `taskId` from the
 * system. It is **guaranteed** that `taskId` _exists_ in the system.
 * - `int execTop()` executes the task with the **highest** priority across all
 * users. If there are multiple tasks with the same **highest** priority,
 * execute the one with the highest `taskId`. After executing, the `taskId` is
 * **removed** from the system. Return the `userId` associated with the executed
 * task. If no tasks are available, return -1.
 *
 * **Note** that a user may be assigned multiple tasks.
 */

public class TaskManager {

  private class Task implements Comparable<Task> {
    int userId, taskId, priority;

    Task(int userId, int taskId, int priority) {
      this.userId = userId;
      this.taskId = taskId;
      this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
      if (other.priority != this.priority) {
        return Integer.compare(other.priority, this.priority);
      }
      return Integer.compare(other.taskId, this.taskId);
    }
  }

  // sorted map [Task : userId]
  TreeMap<Task, Integer> sortedTasks;
  // unsorted map [taskId : Task]
  HashMap<Integer, Task> taskMap;

  public TaskManager(List<List<Integer>> tasks) {
    sortedTasks = new TreeMap<>();
    taskMap = new HashMap<>();

    for (List<Integer> task : tasks) {
      int userId = task.get(0), taskId = task.get(1), priority = task.get(2);
      add(userId, taskId, priority);
    }
  }

  public void add(int userId, int taskId, int priority) {
    sortedTasks.put(new Task(userId, taskId, priority), userId);
    taskMap.put(taskId, new Task(userId, taskId, priority));
  }

  public void edit(int taskId, int newPriority) {
    Task task = taskMap.get(taskId);
    if (task != null) {
      sortedTasks.remove(task);
      task.priority = newPriority;
      sortedTasks.put(task, task.userId);
    }
  }

  public void rmv(int taskId) {
    Task task = taskMap.get(taskId);
    if (task != null) {
      sortedTasks.remove(task);
      taskMap.remove(taskId);
    }
  }

  public int execTop() {
    if (sortedTasks.isEmpty()) {
      return -1;
    }
    Task topTask = sortedTasks.firstKey();
    sortedTasks.remove(topTask);
    taskMap.remove(topTask.taskId);
    return topTask.userId;
  }
}
