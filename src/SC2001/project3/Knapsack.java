package SC2001.project3;

public class Knapsack {
    private Item[] items;
    private int maxCapacity;
    private int[][] profit;

    public Knapsack(Item[] items, int maxCapacity) {
        this.items = items;
        this.maxCapacity = maxCapacity;
        this.profit = new int[items.length][maxCapacity + 1];

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < maxCapacity; j++) {
                this.profit[i][j] = 0;
            }
        }
    }

    public int maxProfit(int capacity) {
        return recursive(capacity, items.length - 1);
    }

    public int maxProfitDynamic(int capacity) {
        return dynamic(capacity, items.length - 1);
    }

    private int recursive(int capacity, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        } else if (items[n].weight > capacity) {
            return recursive(capacity, n - 1);
        } else {
            int skip = recursive(capacity, n - 1);
            int take = items[n].value + recursive(capacity - items[n].weight, n - 1);
            return max(skip, take);
        }
    }

    private int dynamic(int capacity, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                var item = items[i];
                if (j < item.weight) {
                    this.profit[i][j] = this.profit[i - 1][j];
                } else {
                    this.profit[i][j] = max(items[i].value + this.profit[i - 1][j - item.weight], this.profit[i - 1][j]);
                }
            }
        }
        return this.profit[n][capacity];

    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
