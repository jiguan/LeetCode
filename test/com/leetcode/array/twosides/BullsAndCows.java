package com.leetcode.array.twosides;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BullsAndCows {
	public String getHint(String secret, String guess) {
	    int[] count = new int[10];
        int bull = 0, cow = 0;
        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bull++;
            } else {
                if(count[secret.charAt(i) - '0']++ < 0) {
                    cow++;
                }
                if(count[guess.charAt(i) - '0']-- > 0) {
                    cow++;
                }
            }
        }
        return bull + "A" + cow + "B";
	}
	
	@Test
	public void defaultTest() {
		assertEquals("1A3B", getHint("1807", "7810"));
		assertEquals("1A1B", getHint("1123", "0111"));
		assertEquals("1A0B", getHint("11", "10"));
		assertEquals("1A0B", getHint("11", "01"));
		assertEquals("0A4B", getHint("1122", "2211"));
	}
}
