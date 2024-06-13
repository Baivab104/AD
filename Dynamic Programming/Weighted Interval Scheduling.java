import java.util.Arrays;
import java.util.Comparator;

class Interval {
    int start, end, weight;

    Interval(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class WeightedIntervalScheduling {

    // Function to find the latest non-overlapping interval
    private static int latestNonOverlapping(Interval[] intervals, int index) {
        for (int j = index - 1; j >= 0; j--) {
            if (intervals[j].end <= intervals[index].start) {
                return j;
            }
        }
        return -1;
    }

    // Function to find the maximum weight of non-overlapping intervals
    public static int findMaxWeight(Interval[] intervals) {
        int n = intervals.length;

        // Sort intervals based on their end times
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.end));

        // dp[i] will store the maximum weight of non-overlapping intervals from 0 to i
        int[] dp = new int[n];
        dp[0] = intervals[0].weight;

        for (int i = 1; i < n; i++) {
            // Include the current interval
            int inclWeight = intervals[i].weight;
            int l = latestNonOverlapping(intervals, i);
            if (l != -1) {
                inclWeight += dp[l];
            }

            // Exclude the current interval
            dp[i] = Math.max(inclWeight, dp[i - 1]);
        }

        // The last entry of dp[] will have the result
        return dp[n - 1];
    }

    // Main method to test the above
    public static void main(String[] args) {
        Interval[] intervals = {
            new Interval(1, 3, 5),
            new Interval(2, 5, 6),
            new Interval(4, 6, 5),
            new Interval(6, 7, 4),
            new Interval(5, 8, 11),
            new Interval(7, 9, 2)
        };

        System.out.println("Maximum weight of non-overlapping intervals: " + findMaxWeight(intervals));
    }
}
