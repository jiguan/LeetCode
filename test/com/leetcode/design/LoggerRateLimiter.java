package com.leetcode.design;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

// Design a logger system that receive stream of messages along with its timestamps, each message
// should be printed if and only if it is not printed in the last 10 seconds.
public class LoggerRateLimiter {

    @Test
    public void test0() {
        Logger logger = new Logger();
        assertTrue(logger.shouldPrintMessage(1, "foo"));
        assertTrue(logger.shouldPrintMessage(2, "bar"));
        assertFalse(logger.shouldPrintMessage(3, "foo"));
        assertFalse(logger.shouldPrintMessage(8, "bar"));
        assertFalse(logger.shouldPrintMessage(10, "foo"));
        assertTrue(logger.shouldPrintMessage(11, "foo"));
    }
}

class Logger {

    // msg - last printed time
    Map<String, Integer> map = new HashMap<String, Integer>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        int lastPrint = map.getOrDefault(message, -10);
        if (timestamp - lastPrint >= 10) {
            map.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }
}
