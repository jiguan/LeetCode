package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

/*
 * Using index as short url has following disadvantages:
 * If I¡¯m asked to encode the same long URL several times, it will get several entries. That wastes codes and memory.
 * People can find out how many URLs have already been encoded. Not sure I want them to know.
 * People might try to get special numbers by spamming me with repeated requests shortly before their desired number comes up.
 * Only using digits means the codes can grow unnecessarily large. Only offers a million codes with length 6 (or smaller). Using six digits or lower or upper case letters would offer (10+26*2)6 = 56,800,235,584 codes with length 6.
 */
public class EncodeAndDecodeTinyURL {

    Map<String, String> url = new HashMap<>();
    Map<String, String> reverseUrl = new HashMap<>();
    static String BASE_HOST = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (url.containsKey(longUrl)) return BASE_HOST + url.get(longUrl);
        String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        String shortUrl = null;
        do {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < 6; ++i) {
                int index = r.nextInt(charset.length());
                buffer.append(charset.charAt(index));
            }
            shortUrl = buffer.toString();
        } while (url.containsKey(shortUrl));
        url.put(longUrl, shortUrl);
        reverseUrl.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return reverseUrl.get(shortUrl.replace(BASE_HOST, ""));
    }

    @Test
    public void test0() {
        String longUrl = "https://leetcode.com/problems/design-tinyurl";
        assertEquals(longUrl, decode(encode(longUrl)));
    }
}
