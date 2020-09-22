package com.interview.indeed;

import java.util.ArrayList;
import java.util.List;

public class SumaryRange {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (right < nums.length - 1 && (nums[right + 1] - nums[right] <= 1)) {
                right++;
            } else {
                StringBuilder sb = new StringBuilder();
                if (left == right) {
                    sb.append(nums[left]);
                } else {
                    sb.append(nums[left]).append("->").append(nums[right]);
                }
                result.add(sb.toString());
                right++;
                left = right;
            }
        }
        return result;
    }

    public List<String> summaryRanges2(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int left = 0;
        int right = 0;
        int gap = 0;
        while (right < nums.length) {
            if (right < nums.length - 1 && gap == 0) {
                gap = nums[right + 1] - nums[right];
                right++;
            } else if (right < nums.length - 1 && gap == nums[right + 1] - nums[right]) {
                right++;
            } else {
                StringBuilder sb = new StringBuilder();
                if (left == right) {
                    sb.append(nums[left]);
                } else {
                    sb.append(nums[left]).append("-").append(nums[right]).append("/").append(gap);
                }
                result.add(sb.toString());
                right++;
                gap = 0;
                left = right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 3, 3, 5, 7, 8, 13, 20, 21, 29};
        SumaryRange sr = new SumaryRange();
        System.out.println(sr.summaryRanges(nums));
        System.out.println(sr.summaryRanges3(nums));
        System.out.println(sr.summaryRanges2(nums));
        System.out.println(sr.summaryWithGap(nums));
    }

    public List<String> summaryRanges3(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int start = 0;
        int end = 0;
        int count = 0;
        while (end < nums.length) {
            if (end < nums.length - 1 && nums[end + 1] == nums[end] + 1) {
                end++;
            } else {
                StringBuilder sb = new StringBuilder();
                if (start < end) {
                    sb.append(nums[start]).append("-").append(nums[end]);
                } else {
                    sb.append(nums[start]);
                }
                start = end + 1;
                result.add(sb.toString());
                end++;
            }
        }
        return result;
    }

    public List<String> summaryWithGap(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int start = 0;
        int end = 0;
        int gap = 0;
        while (end < nums.length) {
            if (end < nums.length - 1 && gap == 0) {
                gap = nums[end + 1] - nums[end];
                end++;
            } else if (end < nums.length - 1 && gap == nums[end + 1] - nums[end]) {
                end++;
            } else {
                StringBuilder sb = new StringBuilder();
                if (start == end) {
                    sb.append(nums[start]);
                } else {
                    sb.append(nums[start]).append("-").append(nums[end]).append("/").append(gap);
                }
                result.add(sb.toString());
                gap = 0;
                end++;
                start = end;
            }
        }
        return result;
    }
}
