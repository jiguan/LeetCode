package practice.first.dp;
//  http://www.geeksforgeeks.org/dynamic-programming-set-37-boolean-parenthesization-problem/
/*
 * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
   Let the input be in form of two arrays one contains the symbols (T and F) in order and other contains operators (&, | and ^}
 */

public class BooleanParenthesizationProblem {
    public int parenthesize(Character[] expr, Character[] ops) {
        int len = expr.length;
        int[][] T = new int[len][len], F = new int[len][len];
        for(int i=0;i<len;i++) {
            if(expr[i]=='T') {
                T[i][i] = 1;
            } else {
                F[i][i] = 1;
            }
        }
        
//        for(int i=0;i<len;i++) {
//            for(int j=i+1;j<;j++) {
//                int opIndex = Math.max(i, j);
//                if(ops[opIndex]=='&') {
//                    T[i][j] = T[i][]
//                }
//            }
//        }
        return 0;
    }
}
