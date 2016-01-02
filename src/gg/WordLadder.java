package gg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by fan on 11/20/15.
 */
public class WordLadder {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        int minStep = Integer.MAX_VALUE;
        dict.add(end);
        // map from word to steps needed from start to current word
        Map<String, Integer> stepMap = dict.stream()
                .collect(Collectors.toMap(s -> s, s -> Integer.MAX_VALUE));
        stepMap.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int step = stepMap.get(word) + 1;
            if (step > minStep) {
                break;
            }
            for (int i = 0; i < word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    String newWord = word.substring(0, i) + c + word.substring(i + 1);
                    if (!dict.contains(newWord)) {
                        continue;
                    }
                    if (step > stepMap.get(newWord)) {
                        continue;
                    } else if (step < stepMap.get(newWord)) {
                        queue.add(newWord);
                        stepMap.put(newWord, step);
                    }
                    graph.putIfAbsent(newWord, new ArrayList<>());
                    graph.get(newWord).add(word);
                    if (newWord.equals(end)) {
                        minStep = step;
                    }
                }
            }
        }
        List<String> path = new ArrayList<>();
        backTrace(end, start, path, res, graph);
        return res;
    }

    private void backTrace(String word, String start, List<String> path, List<List<String>> res,
                           Map<String, List<String>> graph) {
        path.add(0, word);
        if (word.equals(start)) {
            res.add(new ArrayList<>(path));
        } else if (graph.containsKey(word)) {
            for (String next : graph.get(word)) {
                backTrace(next, start, path, res, graph);
            }
        }
        path.remove(0);
    }

    public static void main(String[] args) {
        WordLadder test = new WordLadder();
        System.out.println(test.findLadders("a", "c", new HashSet<>()));
    }
}
