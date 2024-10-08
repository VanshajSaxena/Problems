// https://www.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1

import java.util.ArrayList;
import java.util.Collections;

/**
 * **Leaders In An Array**:
 *
 * Given an array `arr` of `n` positive integers, your task is to find all the
 * leaders in the array. An element of the array is considered a leader if it is
 * greater than all the elements on its right side or if it is equal to the
 * maximum element on its right side. The rightmost element is always a leader.
 */

public class LeadersInAnArray {

  // The below function traverses the given array `arr` from the right and checks
  // for all the elements that are greater than maxLeader, which is initialised to
  // `arr[n - 1]`. Once and element is found, it adds to the List.
  // The List is then reversed to complete the requirement of the question test
  // cases (the elements needs to be printed from left to right).
  static ArrayList<Integer> leaders(int n, int arr[]) {
    ArrayList<Integer> ansList = new ArrayList<>();
    int maxLeader = arr[n - 1];
    for (int i = n - 1; i >= 0; i--) {
      if (arr[i] >= maxLeader) {
        ansList.add(arr[i]);
        maxLeader = arr[i];
      }
    }
    Collections.reverse(ansList);
    return ansList;
  }
}
