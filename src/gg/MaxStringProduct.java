package gg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by fan on 10/25/15.
 */
public class MaxStringProduct {
    public long maxProduct(Set<String> dict) {
        Map<String, Integer> map = dict.stream()
                .collect(Collectors.toMap(x -> x, this::computeCharSign));
        List<String> list = new ArrayList<>(dict);
        long maxProduct = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String word1 = list.get(i);
                String word2 = list.get(j);
                if ((map.get(word1) & map.get(word2)) == 0) {
                    long product = word1.length() * word2.length();
                    if (product > maxProduct) {
                        maxProduct = product;
                    }
                }
            }
        }
        return maxProduct;
    }

    private int computeCharSign(String word) {
        int charSign = 0;
        for (char c : word.toCharArray()) {
            charSign |= (1 << (c - 'a'));
        }
        return charSign;
    }

    public static void main(String[] args){
        MaxStringProduct test = new MaxStringProduct();
        Set<String> dict = new HashSet<>();
        dict.add("hello");
        dict.add("fan");
        dict.add("wang");
        dict.add("world");
        System.out.println(test.maxProduct(dict));
    }
}
