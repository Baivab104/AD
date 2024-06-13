import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalScheduling {

    // Class to represent an interval
    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    // Method to perform interval scheduling maximization
    public static List<Interval> intervalScheduling(List<Interval> intervals) {
        // Sort intervals based on their end times
        Collections.sort(intervals, Comparator.comparingInt(interval -> interval.end));

        List<Interval> result = new ArrayList<>();
        int lastEndTime = Integer.MIN_VALUE;

        // Iterate through the sorted intervals
        for (Interval interval : intervals) {
            // If the start time of the current interval is greater than or equal to the end time of the last added interval
            if (interval.start >= lastEndTime) {
                result.add(interval);
                lastEndTime = interval.end; // Update the end time
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 5));
        intervals.add(new Interval(3, 9));
        intervals.add(new Interval(6, 8));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(5, 7));

        List<Interval> selectedIntervals = intervalScheduling(intervals);

        System.out.println("Selected Intervals:");
        for (Interval interval : selectedIntervals) {
            System.out.println(interval);
        }
    }
}
