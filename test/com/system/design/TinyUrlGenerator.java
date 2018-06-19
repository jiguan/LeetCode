package com.system.design;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class TinyUrlGenerator {

    // Base 62
    public String charsetStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public final int base = charsetStr.length(); // 62
    
    private AtomicInteger counter = new AtomicInteger(10);
    
    public Map<Long, String> store = new HashMap<>();
    
    public String generateUrl(String url) {
        final long nextNum = getNextNumber();
        String tinyUrl = convertAndGetBase62Code(nextNum);
        store.put(nextNum, url);
        return tinyUrl;
    }
    
    public long getNextNumber() {
        int counterValue = counter.incrementAndGet();
        long systemTime = Long.valueOf("" + counterValue + System.currentTimeMillis());
        return systemTime;
    }
    
    public String convertAndGetBase62Code(long num) {
        StringBuffer buffer = new StringBuffer();
        while(num > 0) {
            int remainder = (int)(num % base);
            buffer.append(charsetStr.charAt(remainder));
            num = num / base;
        }
        return buffer.toString();
    }
    
    public String reverseTinyUrl(String tinyUrl) {
        long tinyUrlId = convertToBase10(tinyUrl);
        return store.get(tinyUrlId);
    }
    
    private long convertToBase10(String tinyUrlCode) {
        long base10 = 0;
        char[] chars = tinyUrlCode.toCharArray();
        
        for(int i = chars.length -1 ; i>=0;i--) {
            int index = charsetStr.indexOf(chars[i]);
            base10 += index * Math.pow(base, i);
        }
        return base10;
    }
    
    @Test
    public void test0() {
        
    }
}
