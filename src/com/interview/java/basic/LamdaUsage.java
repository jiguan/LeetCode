package com.interview.java.basic;

import java.util.List;
import java.util.stream.Collectors;

public class LamdaUsage {

    public List<Integer> modePlusOneFilter(List<Integer> input, int mod) {
        // mod++;
        // Complain Local variable mod defined in an enclosing scope must be final or effectively
        // final
        return input.stream().filter(element -> element % mod == 0).collect(Collectors.toList());
    }

}
