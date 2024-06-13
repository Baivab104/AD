import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    // Item class to store weight and value of each item
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    // Method to get the maximum value in the knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double r1 = (double) item1.value / item1.weight;
                double r2 = (double) item2.value / item2.weight;
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0;

        for (Item item : items) {
            if (capacity - item.weight >= 0) {
                // If the item can be completely taken, take it
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // If the item cannot be completely taken, take the fractional part
                double fraction = (double) capacity / item.weight;
                totalValue += item.value * fraction;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(10, 60),
            new Item(20, 100),
            new Item(30, 120)
        };

        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}
