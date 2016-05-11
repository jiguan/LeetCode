package practice.first.array;

import org.junit.Test;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i=len-1;i>=0;i--) {
            if(digits[i]<9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] nums = new int[len+1];
        nums[0] = 1;
        return nums;
    }
    
    @Test
    public void test0() {
        int[] digits = new int[]{1,2,3}; //124
        for(int num : plusOne(digits)) {
            System.out.print(num);
        }
    }
    
    @Test
    public void test1() {
        int[] digits = new int[]{9,9,9}; //1000
        for(int num : plusOne(digits)) {
            System.out.print(num);
        }
    }
}
