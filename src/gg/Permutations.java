package gg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fan on 10/11/15.
 */
public class Permutations {
    public List<List<Character>> permuteUnique(char[] nums) {
        // no need if all elements are unique
        Arrays.sort(nums);
        List<List<Character>> res = new ArrayList<>();
        List<Character> curr = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteDfs(res, curr, nums, visited);
        return res;
    }

    private void permuteDfs(List<List<Character>> res, List<Character> curr,
                            char[] nums, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // no need if all elements are unique
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                return;
            }
            visited[i] = true;
            curr.add(nums[i]);
            permuteDfs(res, curr, nums, visited);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }

    public String shortestAllPermutations(int n) {
        char[] nums = new char[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (char) (i + '1');
        }
        List<String> permutations = permuteUnique(nums).stream()
                .map(this::charListToStr).collect(Collectors.toList());
        StringBuilder res = new StringBuilder();
        res.append(permutations.get(0));
        permutations.remove(0);
        while (!permutations.isEmpty()) {
            int minCost = n;
            String next = permutations.get(0);
            for (String s : permutations) {
                int cost = costToAdd(res.toString(), s);
                if (cost < minCost) {
                    minCost = cost;
                    next = s;
                }
            }
            permutations.remove(next);
            res.append(next.substring(next.length() - minCost));
        }
        return res.toString();
    }

    private int costToAdd(String curr, String next) {
        for (int i = 1; i < next.length(); i++) {
            if (curr.substring(curr.length() - next.length() + i)
                    .equals(next.substring(0, next.length() - i))) {
                return i;
            }
        }
        return next.length();
    }

    private String charListToStr(List<Character> l) {
        return l.stream().map(Object::toString).reduce((s, c) -> s + c).get();
    }

    public static void main(String[] args) {
        Permutations test = new Permutations();
        System.out.println(test.shortestAllPermutations(3));
        System.out.println(test.shortestAllPermutations(3).length());
        System.out.println(test.shortestAllPermutations(4).length());
        // 2^n +n-1
        System.out.println(test.shortestAllPermutations(4)
                .equals("123412314231243121342132413214321"));
    }
}
