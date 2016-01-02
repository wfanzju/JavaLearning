package gg;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by fan on 10/23/15.
 */
public class TopoSortGraph<V> {
    Map<V, List<V>> adjMatrix = new HashMap<>();

    public void addEdge(V v, V w) {
        adjMatrix.putIfAbsent(v, new ArrayList<>());
        adjMatrix.putIfAbsent(w, new ArrayList<>());
        adjMatrix.get(v).add(w);
    }

    public List<V> topoSortBFS() {
        List<V> res = new ArrayList<>();
        Queue<V> queue = new LinkedList<>();
        Map<V, Integer> indegree = adjMatrix.keySet().stream()
                .collect(Collectors.toMap(x -> x, x -> 0));
        adjMatrix.values().stream()
                .flatMap(List::stream)
                .forEach(v -> indegree.put(v, indegree.get(v) + 1));
//        Map<V, Long> indegree = adjMatrix.values().stream()
//                .flatMap(List::stream)
//                .collect(Collectors.groupingBy(Function.<V>identity(), Collectors.counting()));
        queue.addAll(adjMatrix.keySet().stream()
                .filter(x -> indegree.get(x) == 0)
                .collect(Collectors.toList()));
        while (!queue.isEmpty()) {
            V v = queue.poll();
            res.add(v);
            if (!adjMatrix.containsKey(v)) {
                continue;
            }
            for (V w : adjMatrix.get(v)) {
                int indeg = indegree.get(w);
                indeg--;
                indegree.put(w, indeg);
                if (indeg == 0) {
                    queue.add(w);
                }
            }
        }
        if (res.size() != adjMatrix.size()) {
            throw new IllegalStateException("CycleFoundException");
        }
        return res;
    }

    public List<V> topoSortDFS() {
        Stack<V> res = new Stack<>();
        Map<V, Integer> visited = adjMatrix.keySet().stream()
                .collect(Collectors.toMap(x -> x, x -> 0));
        for (V v : adjMatrix.keySet()) {
            if (visited.get(v) == 0) {
                if (!topoSortDFSRec(v, visited, res)) {
                    throw new IllegalStateException("CycleFoundException");
                }
            }
        }
        Collections.reverse(res);
        return res;
    }

    private boolean topoSortDFSRec(V v, Map<V, Integer> visited, Stack<V> res) {
        visited.put(v, 1);
        for (V w : adjMatrix.get(v)) {
            if (visited.get(w) == 0) {
                if (!topoSortDFSRec(w, visited, res)) {
                    return false;
                }
            } else if (visited.get(w) == 1) {
                return false;
            }
        }
        visited.put(v, 2);
        res.push(v);
        return true;
    }

    public static List<Character> computeAlphabetOrder(String[] words) {
        TopoSortGraph<Character> alphabetTopoSortGraph = new TopoSortGraph<>();
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    alphabetTopoSortGraph.addEdge(word1.charAt(j), word2.charAt(j));
                    break;
                }
            }
        }
        System.out.println(alphabetTopoSortGraph.topoSortDFS());
        return alphabetTopoSortGraph.topoSortBFS();
    }

    public static void main(String[] args) {
        String[] words = new String[]{"xac", "aaa", "aac"};
        System.out.println(computeAlphabetOrder(words));
    }
}
