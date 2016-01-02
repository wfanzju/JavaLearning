package leetcode;

/**
 * Created by fan on 10/24/15.
 */
public class SellStock {
    // one transaction
    public int oneTransaction(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int lowPrice = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - lowPrice);
            lowPrice = Math.min(lowPrice, prices[i]);
        }
        return profit;
    }

    // no transaction limit
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }

    // k transactions
    public int kTransactions(int[] prices, int k) {
        if (prices.length <= 1) {
            return 0;
        }
        if (k >= prices.length / 2) {
            return maxProfit(prices);
        }
        int[][] profitTable = new int[k + 1][prices.length];
        for (int tran = 1; tran <= k; tran++) {
            int tmpMax = profitTable[tran - 1][0] - prices[0];
            for (int day = 1; day < prices.length; day++) {
                profitTable[tran][day] = Math.max(profitTable[tran][day - 1],
                        prices[day] + tmpMax);
                tmpMax = Math.max(tmpMax, profitTable[tran - 1][day] - prices[day]);
            }
        }
        return profitTable[k][prices.length - 1];
    }

    // no transaction limit, only can "buy" one day after "sell"
    public int maxProfitAnotherDay(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] hold = new int[prices.length];
        int[] unhold = new int[prices.length];
        hold[0] = -prices[0];
        unhold[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
            if (i == 1) {
                hold[i] = Math.max(hold[0], 0 - prices[i]);
            }
            hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
        }
        return unhold[prices.length - 1];
    }
}
