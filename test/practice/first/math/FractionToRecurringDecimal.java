package practice.first.math;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        StringBuffer buffer = new StringBuffer();
        int beforeZero = 0;
        if ((numerator > 0) ^ (denominator > 0)) {
            buffer.append("-");
            beforeZero++;
        }
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        beforeZero += num / den < 0 ? 0 : (num / den + "").length();
        HashMap<Long, Integer> map = new HashMap<>();
        while (num != 0) {
            long residue = num / den;
            long mod = num % den;
            if (map.containsKey(mod)) {
                buffer.insert(map.get(mod), "(");
                buffer.append(residue + ")");
                break;
            }
            buffer.append(residue);
            map.put(mod, buffer.length());
            num = mod * 10;
        }
        if (buffer.length() > beforeZero) {
            buffer.insert(beforeZero, ".");
        }
        return buffer.toString();
    }

    @Test
    public void test0() {
        int numerator = 2;
        int denominator = 3;
        assertEquals("0.(6)", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test1() {
        int numerator = 1;
        int denominator = 4;
        assertEquals("0.25", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test2() {
        int numerator = 2;
        int denominator = 1;
        assertEquals("2", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test3() {
        int numerator = 4;
        int denominator = 6;
        assertEquals("0.(6)", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test4() {
        int numerator = 1;
        int denominator = 11;
        assertEquals("0.(09)", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test5() {
        int numerator = 22;
        int denominator = 7;
        assertEquals("3.(142857)", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test6() {
        int numerator = -50;
        int denominator = 8;
        assertEquals("-6.25", fractionToDecimal(numerator, denominator));
    }

    @Test
    public void test7() {
        int numerator = -1;
        int denominator = -2147483648;
        assertEquals("0.0000000004656612873077392578125", fractionToDecimal(numerator, denominator));
    }
}
