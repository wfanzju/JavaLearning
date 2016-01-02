package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan on 11/21/15.
 */
public class InsertInterval {

    class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.size()
                && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i));
        }
        while (i < intervals.size()
                && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(
                    Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        res.add(newInterval);
        while (i < intervals.size()) {
            res.add(intervals.get(i));
        }
        return res;
    }

}
