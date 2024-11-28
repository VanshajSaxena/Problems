// https://leetcode.com/problems/trapping-rain-water/description/

/**
 * **Trapping Rain Water**:
 *
 * Given `n` non-negative integers representing an elevation map where the width
 * of each bar is `1`, compute how much water it can trap after raining.
 */

public class TrappingRainWater {
  public int trap(int[] height) {
    int waterTrapped = 0;
    for (int i = 0; i < height.length; i++) {
      int rightMaxElevation = 0;
      int leftMaxElevation = 0;

      int left = i;
      while (left >= 0) {
        leftMaxElevation = Math.max(leftMaxElevation, height[left]);
        left--;
      }

      int right = i;
      while (right < height.length) {
        rightMaxElevation = Math.max(rightMaxElevation, height[right]);
        right++;
      }
      waterTrapped += Math.min(leftMaxElevation, rightMaxElevation) - height[i];
    }
    return waterTrapped;
  }

  public int trapBetter(int[] height) {
    int length = height.length;
    int[] leftMaxElevations = new int[length];
    int[] rightMaxElevations = new int[length];
    int waterTrapped = 0;

    leftMaxElevations[0] = height[0];
    for (int i = 1; i < length; i++) {
      leftMaxElevations[i] = Math.max(leftMaxElevations[i - 1], height[i]);
    }

    rightMaxElevations[length - 1] = height[length - 1];
    for (int i = length - 2; i >= 0; i--) {
      rightMaxElevations[i] = Math.max(rightMaxElevations[i + 1], height[i]);
    }

    for (int i = 0; i < height.length; i++) {
      waterTrapped += Math.min(leftMaxElevations[i], rightMaxElevations[i]) - height[i];
    }

    return waterTrapped;
  }

  public int trapOptimal(int[] height) {
    int waterTrapped = 0;
    int left = 0, right = height.length - 1;
    int leftMaxElevation = 0;
    int rightMaxElevation = 0;

    while (left <= right) {
      if (height[left] <= height[right]) {
        if (height[left] >= leftMaxElevation) {
          leftMaxElevation = height[left];
        } else {
          waterTrapped += leftMaxElevation - height[left];
        }
        left++;
      } else {
        if (height[right] >= rightMaxElevation) {
          rightMaxElevation = height[right];
        } else {
          waterTrapped += rightMaxElevation - height[right];
        }
        right--;
      }
    }

    return waterTrapped;
  }
}
