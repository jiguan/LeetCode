package com.algorithm;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BinarySearch {

    public int findFloorIndex(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = (end - start + 1) / 2 + start;
            if (arr[mid] > target) {
                end = mid - 1;
            } else if (arr[mid] < target) {
                start = mid;
            } else {
                return mid;
            }
        }
        return start;
    }

    @Test
    public void testFindFloorIndex1() {
        int[] arr = {1, 2, 5, 7};
        int target = 3;
        assertEquals(Integer.valueOf(1), Integer.valueOf(findFloorIndex(arr, target)));
    }

    @Test
    public void testFindFloorIndex2() {
        int[] arr = {1, 2, 5, 7};
        int target = 5;
        assertEquals(Integer.valueOf(2), Integer.valueOf(findFloorIndex(arr, target)));
    }
    
    @Test
    public void testFindFloorIndex3() {
        int[] arr = {1, 2, 5};
        int target = 4;
        assertEquals(Integer.valueOf(1), Integer.valueOf(findFloorIndex(arr, target)));
    }
    
    @Test
    public void testFindFloorIndex4() {
        int[] arr = {1, 2, 5};
        int target = 0;
        assertEquals(Integer.valueOf(0), Integer.valueOf(findFloorIndex(arr, target)));
    }
    
    @Test
    public void testFindFloorIndex5() {
        int[] arr = {1, 2, 5};
        int target = 6;
        assertEquals(Integer.valueOf(2), Integer.valueOf(findFloorIndex(arr, target)));
    }
    
    public int findCeilIndex(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (arr[mid] > target) {
                end = mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    @Test
    public void testFindCellIndex1() {
        int[] arr = {1, 2, 5, 7};
        int target = 3;
        assertEquals(Integer.valueOf(2), Integer.valueOf(findCeilIndex(arr, target)));
    }
    
    @Test
    public void testFindCellIndex2() {
        int[] arr = {1, 2, 5, 7};
        int target = 5;
        assertEquals(Integer.valueOf(2), Integer.valueOf(findCeilIndex(arr, target)));
    }
    
    @Test
    public void testFindCellIndex3() {
        int[] arr = {1, 2, 5};
        int target = 3;
        assertEquals(Integer.valueOf(2), Integer.valueOf(findCeilIndex(arr, target)));
    }
    
    @Test
    public void testFindCellIndex4() {
        int[] arr = {1, 2, 5};
        int target = 0;
        assertEquals(Integer.valueOf(0), Integer.valueOf(findCeilIndex(arr, target)));
    }
    
    @Test
    public void testFindCellIndex5() {
        int[] arr = {1, 2, 5};
        int target = 6;
        assertEquals(Integer.valueOf(2), Integer.valueOf(findCeilIndex(arr, target)));
    }
}


