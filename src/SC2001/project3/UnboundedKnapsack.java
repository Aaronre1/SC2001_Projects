package SC2001.project3;

import static java.lang.Math.max;

public class UnboundedKnapsack {
    private Item[] items;
    private int maxCapacity;
    private int[][] profit;

    public UnboundedKnapsack(Item[] items, int maxCapacity) {
        this.items = items;
        this.maxCapacity = maxCapacity;
        this.profit = new int[items.length + 1][maxCapacity + 1];

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < maxCapacity; j++) {
                this.profit[i][j] = 0;
            }
        }
    }

    public int maxProfit(int capacity) {
        return recursive(capacity, items.length);
    }

    private int recursive(int capacity, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        }
        if (items[n - 1].weight > capacity) {
            return recursive(capacity, n - 1);
        } else {
            int skip = recursive(capacity, n - 1);
            int take = items[n - 1].value + recursive(capacity - items[n - 1].weight, n);
            return max(skip, take);
        }
    }

    public int maxProfitDynamic(int capacity) {
        return dynamic(capacity, items.length);
    }

    private int dynamic(int capacity, int n) {
        for (int i = 0; i <= n; i++) {
            for (int c = 0; c <= capacity; c++) {
                if (i == 0 || c == 0) {
                    profit[i][c] = 0;
                } else if (items[i - 1].weight <= c) {
                    int skip = profit[i - 1][c];
                    int take = items[i - 1].value + profit[i][c - items[i - 1].weight];
                    profit[i][c] = max(skip, take);
                } else {
                    profit[i][c] = profit[i - 1][c];
                }
            }
        }
        return profit[n][capacity];
    }

    public void printProfitTable() {
        for (int i = 0; i < profit.length; i++) {
            var p = profit[i];
            for (int j = 0; j < p.length; j++) {
                System.out.print(p[j] + " ");
            }
            System.out.println();
        }
    }
}
