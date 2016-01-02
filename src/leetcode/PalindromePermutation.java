package leetcode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by fan on 11/21/15.
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        BitSet bs = new BitSet();
        for (byte b : s.getBytes()) {
            bs.flip(b);
        }
        return bs.cardinality() < 2;
    }

    public List<String> permutePalindrome(String s) {
        int[] counter = new int[256];
        for (char c : s.toCharArray()) {
            counter[c]++;
        }
        int oddChar = -1, oddCount = 0;
        for (int i = 0; i < counter.length; i++) {
            if (oddCount == 0 && (counter[i] & 1) == 1) {
                oddChar = i;
                oddCount++;
                counter[i]--;
            }else if ((counter[i] & 1) == 1) {
                return new ArrayList<>();
            }
        }
        String curr = oddChar == -1 ? "" : "" + (char) oddChar;
        List<String> res = new ArrayList<>();
        dfs(res, curr, counter, s.length());
        return res;
    }

    private void dfs(List<String> res, String curr, int[] counter, int len) {
        if (curr.length() == len) {
            res.add(curr);
            return;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] <= 0) {
                continue;
            }
            counter[i] -= 2;
            curr = (char) i + curr + (char) i;
            dfs(res, curr, counter, len);
            curr = curr.substring(1, curr.length() - 1);
            counter[i] += 2;
        }
    }
}
