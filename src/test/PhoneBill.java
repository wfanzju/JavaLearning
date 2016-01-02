package test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 11/12/15.
 */
public class PhoneBill {
    public int solution(String s) {
        Map<String, Double> timeMap = new HashMap<>();
        Map<String, Long> costMap = new HashMap<>();
        String[] lines = s.split("\\r?\\n");
        for (String l : lines) {
            String[] info = l.split(",");
            timeMap.putIfAbsent(info[1], 0.0);
            costMap.putIfAbsent(info[1], 0L);
            timeMap.put(info[1], timeMap.get(info[1]) + getTime(info[0]));
            costMap.put(info[1], costMap.get(info[1]) + getCost(info[0]));
        }
        String maxNumber=null;
        double maxTime = 0;
        for (String k : timeMap.keySet()) {
            if (timeMap.get(k) > maxTime) {
                maxNumber = k;
            }else if (timeMap.get(k) == maxTime) {
                maxNumber = (k.compareTo(maxNumber)<0)?k:maxNumber;
            }
        }
        int cost = 0;
        for (String k : costMap.keySet()) {
            if (k.equals(maxNumber)) {
                continue;
            }
            cost += costMap.get(k);
        }
        return cost;
    }

    private double getTime(String s) {
        String[] ts = s.split(":");
        return Double.parseDouble(ts[0]) * 60 + Double.parseDouble(ts[1]) + Double.parseDouble(ts[2]) / 60;
    }

    private long getCost(String s) {
        String[] ts = s.split(":");
        int[] nums = new int[3];
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(ts[i]);
        }
        if (nums[0] == 0 && nums[1] < 5) {
            return (nums[1] * 60 + nums[2]) * 3;
        }
        long minutes = nums[0] * 60 + nums[1];
        if (nums[2] != 0) {
            minutes += 1;
        }
        return minutes * 150;
    }

    public static void main(String[] args) {
        PhoneBill test = new PhoneBill();
        int cost = test.solution("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090");
        System.out.println(cost);
    }
}
