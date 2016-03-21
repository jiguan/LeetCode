package practice.first.math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false; 
        double r = Math.log10(n) / Math.log10(3);
        if(r % 1.0 == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    @Test
    public void test0() {
    	int n = 27;
    	double r1 = Math.log10(n);
    	System.out.println(r1);
    	double r2 = Math.log10(3);
    	System.out.println(r2);
    	double r = r1/ r2;
    	System.out.println(r);
    }
    
    @Test
    public void test1() {
    	int n = 0;
    	double r1 = Math.log10(n);
    	System.out.println(r1);
    }
    
    @Test
    public void test2() {
    	int n = 27;
    	assertTrue(isPowerOfThree(n));
    }
}
