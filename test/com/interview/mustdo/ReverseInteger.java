package com.interview.mustdo;

public class ReverseInteger {
    public int reverse(int x) {
        int res = 0;        
        while(x != 0 ) {
            int tmp = res * 10 + x % 10;
            if((tmp - x % 10) / 10 != res) return 0;
            res = tmp;
            x /= 10;
        }
        return res;
    }
}
