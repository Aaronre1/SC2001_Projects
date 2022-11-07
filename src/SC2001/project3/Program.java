package SC2001.project3;

public class Program {
    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(4, 7),
                new Item(6, 6),
                new Item(8, 9),
        };
        Knapsack knapsack = new Knapsack(items, 50);

        Item[] items1 = new Item[]{
                new Item(5, 7),
                new Item(6, 6),
                new Item(8, 9),
        };
        Knapsack knapsack1 = new Knapsack(items1, 50);

        int capacity = 14;
        //(1)
        int maxProfit = knapsack.maxProfit(capacity);
        printResult(capacity, maxProfit);

        //(4)
        // a.
        int maxProfitDynamic = knapsack.maxProfitDynamic(capacity);
        printResult(capacity, maxProfitDynamic);
        // b.
        int maxProfitDynamic1 = knapsack1.maxProfitDynamic(capacity);
        printResult(capacity, maxProfitDynamic1);

        //////
        // 1
        UnboundedKnapsack unboundedKnapsack = new UnboundedKnapsack(items, 50);
        int result = unboundedKnapsack.maxProfit(capacity);
        printResult(capacity, result);
        // 4.a
        int resultDynamic = unboundedKnapsack.maxProfitDynamic(capacity);
        printResult(capacity, resultDynamic);
        unboundedKnapsack.printProfitTable();

        // 4.b
        UnboundedKnapsack unboundedKnapsack1 = new UnboundedKnapsack(items1, 50);
        int resultDynamic1 = unboundedKnapsack1.maxProfitDynamic(capacity);
        printResult(capacity, resultDynamic1);
        unboundedKnapsack1.printProfitTable();
    }

    private static void printResult(int capacity, int result) {
        System.out.println("Capacity: " + capacity);
        System.out.println("Max Profit: " + result + "\n");
    }
}
