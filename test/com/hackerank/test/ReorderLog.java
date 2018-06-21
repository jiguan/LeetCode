package com.hackerank.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
/**
 * You have been given a task of reordering some data from a log file. Every line in the log file is a space delimited
 * list of strings and all lines begin with an identifier that is alphanumeric. After the identifier, a line will
 * consist of either a list of words using only English letters or only a list of integers. There will be no lines that
 * consist of only an identifier.
 * 
 * Your task is to reorder the data from the log file such that all the lines with words are at the top in the log file.
 * in lexicographical order. Words are ordered lexicographically ignoring the identifier except in the case of ties. In
 * the case of ties (if there are two lines that are identical except for the identifier), the identifier is used to
 * order lexicographically. Alphanumerics should be sorted in ASCII order (numbers come before letters). The identifiers
 * must still be part of the lines in the output Strings. Lines with integers do not need to be sorted relative to other
 * lines with integers.
 */
public class ReorderLog {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<String> reorderLines(int logFileSize, List<String> logLines) {
        // WRITE YOUR CODE HERE
        List<String> alphaList = new ArrayList<>();
        List<String> numericList = new ArrayList<>();

        for (String line : logLines) {
            String content = line.split(" ", 2)[1];
            if (content.matches("[0-9 ]+")) {
                numericList.add(line);
            } else if (content.matches("[a-zA-Z ]+")) {
                alphaList.add(line);
            }
        }

        Collections.sort(alphaList, new LogComparater());
        Collections.sort(numericList, new LogComparater());

        alphaList.addAll(numericList);
        return alphaList;
    }

    class LogComparater implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            String[] arr1 = s1.split(" ", 2);
            String[] arr2 = s2.split(" ", 2);

            String content1 = arr1[1].trim(), content2 = arr2[1].trim();

            // if the log contents are same, compare their ids
            if (content1.equals(content2)) {
                return arr1[0].compareTo(arr2[0]);
            } else {
                return content1.compareTo(content2);
            }
        }
    }

    @Test
    public void test0() {
        List<String> logLines = Arrays.asList("mi2 jog mid pet", "wz3 34 54 398", "a1 apls cow bar", "x4 45 21 7");
        List<String> result = reorderLines(logLines.size(), logLines);
        PrettyPrint.print(result);
    }

    @Test
    public void test1() {
        List<String> logLines = Arrays.asList("r1a box ape bit", "r2 box ape bit");
        List<String> result = reorderLines(logLines.size(), logLines);
        PrettyPrint.print(result);
    }

    @Test
    public void test2() {
        String s = "a3  aa sas";
        PrettyPrint.print(s.split(" ", 2));
    }
}
