package com.leetcode.array;

import java.util.Arrays;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        int[] begin = new int[intervals.length];
        int[] end = new int[intervals.length];
    	for(int i=0;i<intervals.length;++i) {
        	begin[i] = intervals[i][0];
        	end[i] = intervals[i][1];
        }
    	
    	Arrays.sort(begin);
    	Arrays.sort(end);
    	int rooms = 0;
    	int preRoomIndex = 0;
    	for(int i=0; i<intervals.length; ++i) {
    		if(begin[i] < end[preRoomIndex]) {
    			rooms++;
    		} else {
    			preRoomIndex++;
    		}
    	}
    	return rooms;
    }
}
