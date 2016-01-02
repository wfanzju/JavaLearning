package gg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/5/15.
 */
public class MergeIntervals {
    private class Interval{
        int start;
        int end;

        Interval(int s, int e) {
            start=s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }
        intervals.sort((a, b) -> Integer.compare(a.start, b.start));
        List<Interval> res = new ArrayList<>();
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (prev.end < curr.start) {
                res.add(prev);
                prev = curr;
            } else {
                Interval combined = new Interval(prev.start, Math.max(prev.end, curr.end));
                prev = combined;
            }
        }
        res.add(prev);
        return res;
    }
}
