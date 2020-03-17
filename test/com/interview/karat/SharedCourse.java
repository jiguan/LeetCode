package com.interview.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * You are a developer for a university. Your current project is to develop a system for students to
 * find courses they share with friends. The university has a system for querying courses students
 * are enrolled in, returned as a list of (ID, course) pairs.
 * 
 * Write a function that takes in a list of (student ID number, course name) pairs and returns, for
 * every pair of students, a list of all courses they share.
 * 
 * Sample Input:
 * 
 * student_course_pairs_1 = [ ["58", "Software Design"], ["58", "Linear Algebra"], ["94",
 * "Art History"], ["94", "Operating Systems"], ["17", "Software Design"], ["58", "Mechanics"],
 * ["58", "Economics"], ["17", "Linear Algebra"], ["17", "Political Science"], ["94", "Economics"],
 * ["25", "Economics"], ]
 * 
 * Sample Output (pseudocode, in any order):
 * 
 * find_pairs(student_course_pairs_1) => { [58, 17]: ["Software Design", "Linear Algebra"] [58, 94]:
 * ["Economics"] [58, 25]: ["Economics"] [94, 25]: ["Economics"] [17, 94]: [] [17, 25]: [] }
 * 
 * Additional test cases:
 * 
 * Sample Input:
 * 
 * student_course_pairs_2 = [ ["42", "Software Design"], ["0", "Advanced Mechanics"], ["9",
 * "Art History"], ]
 * 
 * Sample output:
 * 
 * find_pairs(student_course_pairs_2) => { [0, 42]: [] [0, 9]: [] [9, 42]: [] }
 */
public class SharedCourse {
    public static void main(String[] args) {
        String[][] student_course_pairs_1 = {{"58", "Software Design"}, {"58", "Linear Algebra"},
                {"94", "Art History"}, {"94", "Operating Systems"}, {"17", "Software Design"},
                {"58", "Mechanics"}, {"58", "Economics"}, {"17", "Linear Algebra"},
                {"17", "Political Science"}, {"94", "Economics"}, {"25", "Economics"},};
        print(shareClass(student_course_pairs_1));

    }

    static void print(List<List<String>> lists) {
        for (List<String> list : lists) {
            System.out.println(list);
        }
    }

    static List<List<String>> shareClass(String[][] student_course_pairs_1) {
        Map<String, Set<String>> map = new HashMap<>();
        List<String> students = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        for (String[] str : student_course_pairs_1) {
            if (!students.contains(str[0])) students.add(str[0]);
            if (map.get(str[0]) == null) {
                Set<String> set = new HashSet<>();
                map.put(str[0], set);
            }
            map.get(str[0]).add(str[1]);
        }
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Set<String> courses1 = map.get(students.get(i));
                Set<String> courses2 = map.get(students.get(j));
                List<String> list = new ArrayList<>();
                list.add(students.get(i));
                list.add(students.get(j));
                for (String course : courses1) {
                    if (courses2.contains(course)) {
                        list.add(course);
                    }
                }
                if (list.size() == 2) list.add("[]"); // [17, 25, []]
                res.add(list);
            }
        }

        return res;
    }


}
