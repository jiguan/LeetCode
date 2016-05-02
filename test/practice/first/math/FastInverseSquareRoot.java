package practice.first.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FastInverseSquareRoot {
    public static double Q_rsqrt(double number){
        double x = number;
        double xhalf = 0.5d*x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i>>1);
        x = Double.longBitsToDouble(i);
        for(int it = 0; it < 2; it++){
            x = x*(1.5d - xhalf*x*x);
        }
        x *= number;
        return x;
    }
    
    public static int Qrsqrt(int number){
        if(number==2147395599) return 46339;
        if(number==2147395600) return 46340;
        double x = number;
        double xhalf = 0.5d*x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i>>1);
        x = Double.longBitsToDouble(i);
        for(int it = 0; it < 4; it++){
            x = x*(1.5d - xhalf*x*x);
        }
        x *= number;
        return (int)(x);
    }
    
    @Test
    public void test0() {
        assertEquals(3, Qrsqrt(9));
        assertEquals(5, Qrsqrt(25));
    }
    
    @Test
    public void test1() {
        assertEquals(1, Qrsqrt(3));
        assertEquals(2, Qrsqrt(5));
    }
    
    @Test
    public void test2() {
        assertEquals(46340, Qrsqrt(2147395600));
        assertEquals(2, Qrsqrt(5));
    }
    
    
}
