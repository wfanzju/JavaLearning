package gg;

import java.util.*;

/**
 * Created by fan on 10/30/15.
 */
public class MaxConcurrentFlights {
    private final NavigableMap<Double, Integer> counter = new TreeMap<>();

    public int getMaxFlights(double[][] flightsTime) {
        List<FlightTime> timeList = new ArrayList<>();
        for (double[] time : flightsTime) {
            timeList.add(new FlightTime(time[0], true));
            timeList.add(new FlightTime(time[1], false));
        }
        timeList.sort(FlightTime::compareTo);
        int maxCount = 0;
        int currCount = 0;
        for (FlightTime t : timeList) {
            if (t.isStart) {
                currCount++;
            } else {
                currCount--;
            }
            maxCount = Math.max(maxCount, currCount);
            counter.put(t.time, currCount);
        }
        System.out.println(counter);
        return maxCount;
    }

    public int getMaxFlightsQ(double[][] flightsTime) {
        Arrays.sort(flightsTime, (a, b) -> Double.compare(a[0], b[0]));
        int max = 0;
        PriorityQueue<double[]> queue = new PriorityQueue<>(flightsTime.length,
                (a, b) -> Double.compare(a[1], b[1]));
        for (double[] flight : flightsTime) {
            while (!queue.isEmpty() && flight[0] >= queue.peek()[1]) {
                queue.poll();
            }
            queue.offer(flight);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public int getCount(double time) {
        Map.Entry<Double, Integer> entry = counter.floorEntry(time);
        return entry == null ? 0 : entry.getValue();
    }

    public List<double[]> getOverlap(double[][] flightsTime) {
        List<double[]> overlaps = new ArrayList<>();
        List<FlightTime> timeList = new ArrayList<>();
        for (double[] time : flightsTime) {
            timeList.add(new FlightTime(time[0], true));
            timeList.add(new FlightTime(time[1], false));
        }
        timeList.sort(FlightTime::compareTo);
        int currCount = 0;
        double start = 0;
        for (FlightTime t : timeList) {
            if (t.isStart) {
                if (currCount == 1) {
                    start = t.time;
                }
                currCount++;
            } else {
                if (currCount == 2) {
                    overlaps.add(new double[]{start, t.time});
                }
                currCount--;
            }
        }
        return overlaps;
    }

    public static void main(String[] args) {
        MaxConcurrentFlights test = new MaxConcurrentFlights();
        double[][] time = new double[][]{{1, 7}, {2, 4}, {4, 6}};
        System.out.println(test.getMaxFlights(time));
        for (double[] overlap : test.getOverlap(time)) {
            System.out.println(overlap[0] + ":" + overlap[1]);
        }
        System.out.println(test.getCount(5.5));
        System.out.println(test.getCount(7.8));
        System.out.println(test.getCount(6));
        System.out.println(test.getCount(0.2));
    }

    private class FlightTime implements Comparable<FlightTime> {
        double time;
        boolean isStart;

        public FlightTime(double time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(FlightTime ft) {
            int diff = Double.compare(this.time, ft.time);
            return diff != 0 ? diff : -Boolean.compare(this.isStart, ft.isStart);
        }
    }
}
