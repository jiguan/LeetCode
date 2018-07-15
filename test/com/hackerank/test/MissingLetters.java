package com.hackerank.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MissingLetters {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            if(line.isEmpty()) {
                System.out.println("NULL");
                continue;
            }
            Set<Character> set = new HashSet<>(26);
            for(int i = 0; i < 26; i++) {
              set.add((char) ('a' + i));
            }
            
            for(char c : line.toCharArray()) {
              if(Character.isLetter(c)) {
                set.remove(Character.toLowerCase(c));
              }
            }
            
            if(set.isEmpty()) System.out.println("NULL");
            else {
                List<Character> res = new ArrayList<>(set);
                
                Collections.sort(res);
                StringBuilder sb = new StringBuilder();
                for(char c : res) {
                    sb.append(c);
                }
                System.out.println(sb.toString());
            }
        }
      }
}
