// https://leetcode.com/problems/count-mentions-per-user/description/

import java.util.Collections;
import java.util.List;

/**
 * **Count Mentions Per User**:
 *
 * You are given an integer `numberOfUsers` representing the total number of
 * users and an array `events` of size `n x 3`.
 *
 * Each `events[i]` can be either of the following two types:
 *
 * 1 **Message Event**: `["MESSAGE", "timestampi", "mentions_stringi"]`
 * - This event indicates that a set of users was mentioned in a message at
 * `timestampi`.
 * - The `mentions_stringi` string can contain one of the following tokens:
 * - `id<number>`: where `<number>` is an integer in range `[0,numberOfUsers -
 * 1]`. There can be **multiple** ids separated by a single whitespace and may
 * contain duplicates. This can mention even the offline users.
 * - `ALL`: mentions **all** users.
 * - `HERE`: mentions all **online** users.
 * 2 **Offline Event**: `["OFFLINE", "timestampi", "idi"]`
 * - This event indicates that the user `idi` had become offline at `timestampi`
 * for **60 time units**. The user will automatically be online again at time
 * `timestampi + 60`.
 *
 * Return an array `mentions` where `mentions[i]` represents the number of
 * mentions the user with id `i` has across all `MESSAGE` events.
 *
 * All users are initially online, and if a user goes offline or comes back
 * online, their status change is processed before handling any message event
 * that occurs at the same timestamp.
 *
 * **Note** that a user can be mentioned **multiple** times in a **single**
 * message event, and each mention should be counted **separately**.
 */

public class CountMentionsPerUser {

  public int[] countMentions(int numberOfUsers, List<List<String>> events) {
    Collections.sort(events, (a, b) -> {
      int time1 = Integer.parseInt(a.get(1)), time2 = Integer.parseInt(b.get(1));
      if (time1 == time2) {
        if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE")) {
          return -1;
        }
      }
      return Integer.parseInt(a.get(1)) - Integer.parseInt(b.get(1));
    });

    int ans[] = new int[numberOfUsers];
    int usertimestamp[] = new int[numberOfUsers];

    for (List<String> event : events) {
      String msg = event.get(0);
      int time = Integer.parseInt(event.get(1));
      if (msg.equals("OFFLINE")) {
        usertimestamp[Integer.parseInt(event.get(2))] = time + 60;

      } else {
        String mentions_string = event.get(2);
        if (mentions_string.equals("ALL")) {
          for (int i = 0; i < numberOfUsers; i++) {
            ans[i]++;
          }

        } else if (mentions_string.equals("HERE")) {
          for (int i = 0; i < numberOfUsers; i++) {
            if (usertimestamp[i] <= time)
              ans[i]++;
          }

        } else {
          for (String id : event.get(2).split(" ")) {
            int curr = Integer.parseInt(id.substring(2));
            ans[curr]++;
          }
        }
      }
    }

    return ans;
  }
}
