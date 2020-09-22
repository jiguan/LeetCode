package com.interview.indeed;

import java.util.HashMap;
import java.util.Map;

public class DiceCombination {

    int count = 0;

    public float sumPossibility(int dice, int target) {
        int total = (int) Math.pow(6, dice);
        helper(dice, 0, target);
        return (float) count / total;
    }

    public void helper(int dice, int sum, int target) {
        if (dice == 0) {
            if (target == sum)
                count++;
            return;
        }
        for (int i = 1; i <= 6; i++) {
            helper(dice - 1, sum + i, target);
        }
    }

    Map<String, Integer> cache = new HashMap<>();

    public float sumPossibilityTopDownDP(int dice, int target) {
        int total = (int) Math.pow(6, dice);
        int res = helperTopDownDP(dice, target);
        return (float) res / total;
    }

    public int helperTopDownDP(int dice, int target) {
        if (dice == 0) {
            return target == 0 ? 1 : 0;
        }
        if (cache.containsKey(dice + "/" + target)) {
            return cache.get(dice + "/" + target);
        }
        int res = 0;
        for (int k = 1; k <= 6; k++) {
            res += helperTopDownDP(dice - 1, target - k);
        }
        cache.put(dice + "/" + target, res);
        return res;
    }


    public double getPossibility(int dice, int target) {
        if (dice <= 0 || target < dice || target > 6 * dice) {
            return 0.0;
        }
        int total = (int) Math.pow(6, dice);
        int[][] cache = new int[dice + 1][target + 1];
        int count = dfsMemo(dice, target, cache);
        return (double) count / total;
    }

    public int dfsMemo(int dice, int target, int[][] cache) {
        int res = 0;
        if (dice == 0) {
            if (target == 0)
                return 1;
        }
        if (target > dice * 6 || target < dice) {
            return 0;
        }
        if (cache[dice][target] != 0) {
            return cache[dice][target];
        }

        for (int i = 1; i <= 6; i++) {
            res += dfsMemo(dice - 1, target - i, cache);
        }
        cache[dice][target] = res;
        return res;
    }

    public float sumPossibilityButtomUpDP(int dice, int target) {
        int total = (int) Math.pow(6, dice);

        // num of combination when using i dices to get j
        int[][] dp = new int[dice + 1][target + 1];
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1;
        }
        for (int i = 2; i <= dice; i++)
            for (int j = i; j <= target; j++)
                for (int k = 1; k <= 6 && j - k >= i - 1; k++)
                    dp[i][j] += dp[i - 1][j - k];
        return (float) dp[dice][target] / total;
    }


    public static void main(String[] args) {
        DiceCombination dc = new DiceCombination();
        System.out.println(dc.sumPossibility(10, 35));
        System.out.println(dc.getPossibility(10, 35));
        System.out.println(dc.sumPossibilityTopDownDP(10, 35));
        System.out.println(dc.sumPossibilityButtomUpDP(10, 35));
    }
}
