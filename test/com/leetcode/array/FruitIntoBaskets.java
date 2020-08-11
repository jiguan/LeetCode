package com.leetcode.array;

/*
 * Fruit Into Baskets
 * 
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * 
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * 
 * 1. Add one piece of fruit from this tree to your baskets.
 * 
 * 2. If you cannot, stop. Move to the next tree to the right of the current tree. If there is no
 * tree to the right, stop.
 * 
 * Note that you do not have any choice after the initial choice of starting tree: you must perform
 * step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * 
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket
 * to only carry one type of fruit each.
 * 
 * What is the total amount of fruit you can collect with this procedure?
 * 
 */
public class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        int lastFruit = -1, secondLastFruit = -1;
        int lastFruitCount = 0;
        int currMax = 0;
        int max = 0;

        for (int fruit : tree) {
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currMax++;
            } else {
                currMax = lastFruitCount + 1; // last fruit + new fruit, discard second last fruit
            }

            if (fruit == lastFruit) {
                lastFruitCount++;
            } else {
                secondLastFruit = lastFruit;
                lastFruit = fruit;
                lastFruitCount = 1;
            }
            max = Math.max(max, currMax);
        }

        return max;
    }
}
