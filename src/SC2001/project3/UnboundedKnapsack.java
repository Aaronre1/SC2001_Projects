package SC2001.project3;

import static java.lang.Math.max;

public class UnboundedKnapsack {
    private Item[] items;
    private int maxCapacity;
    private int[][] profit;

    public UnboundedKnapsack(Item[] items, int maxCapacity){
        this.items = items;
        this.maxCapacity = maxCapacity;
        this.profit = new int[items.length][maxCapacity + 1];

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < maxCapacity; j++) {
                this.profit[i][j] = 0;
            }
        }
    }

    public int maxProfit(int capacity) {return recursive(capacity, items.length);}

    private int recursive(int capacity, int n){
        if (n==0||capacity==0){
            return 0;
        }
        if (items[n-1].weight>capacity){
            return  recursive(capacity, n-1);
        }else{
            int skip = recursive(capacity, n-1);
            int take = items[n-1].value + recursive(capacity-items[n-1].weight,n);
            return max(skip, take);
        }
    }
}
