package com.hackerank.test;

import java.io.IOException;

public class StockPickerFromTheFuture {
    static int[] parseInformationFromTheFuture(String rawData) {
        String[] ss = rawData.split("\\,");
        int[] a = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            a[i] = Integer.valueOf(ss[i]);
        }
        return a;
    }

    static String formatOutputString(int buyPrice, int buyDay, int sellPrice, int sellDay, int profit) {
        return "BUY@" + buyPrice + " on day " + buyDay + " and SELL@" + sellPrice + " on day " + sellDay + ".  For a profit of $" + profit + " per share!";
    }

    static String computeBestTransaction(int[] data) {
        int buyPrice = data[0], buyDay = 0, sellPrice = data[0], sellDay = 0, profit = 0;
        int minPrice = data[0], minPriceDay = 0, maxPrice = data[0];

        for (int i = 1; i < data.length; ++i) {
            int price = data[i];
            if (price < minPrice) {
                minPrice = price;
                minPriceDay = i + 1;
                maxPrice = price;
            } else if (price > maxPrice) {
                maxPrice = price;
                if (maxPrice - minPrice > profit) {
                    profit = maxPrice - minPrice;
                    sellPrice = maxPrice;
                    sellDay = i + 1;
                    buyPrice = minPrice;
                    buyDay = minPriceDay;
                }
            }
        }

        return formatOutputString(buyPrice, buyDay, sellPrice, sellDay, profit);// formatOutputString(buyPrice,buyDay,sellPrice,sellDay,profit);
    }

    public static void main(String[] args) throws IOException {
        int[] data = new int[]{100, 90, 95, 100, 110, 100, 90, 100};
        String result = computeBestTransaction(data);
        String expected = "BUY@90 on day 2 and SELL@110 on day 5.  For a profit of $20 per share!";
        assert (expected.equals(result));
    }
}
