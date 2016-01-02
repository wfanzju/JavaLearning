package gg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fan on 11/7/15.
 */
public class GraphPath<V> {
    Map<V, List<V>> adjMatrix = new HashMap<>();

    public void addEdge(V v, V w) {
        adjMatrix.putIfAbsent(v, new ArrayList<>());
        adjMatrix.putIfAbsent(w, new ArrayList<>());
        adjMatrix.get(v).add(w);
    }

    public void getLongestPath() {
        List<V> path = new ArrayList<>();
        List<List<V>> pathList = new ArrayList<>();
        Map<V, Integer> visited = adjMatrix.keySet().stream()
                .collect(Collectors.toMap(x -> x, x -> 0));
        for (V v : adjMatrix.keySet()) {
            dfs(v, visited, path, pathList);
        }
        System.out.println(pathList);
    }

    private void dfs(V v, Map<V, Integer> visited, List<V> path, List<List<V>> pathList) {
        path.add(v);
        visited.put(v, 1);
        if (adjMatrix.get(v).size() == 0) {
            pathList.add(new ArrayList<>(path));
        }
        for (V w : adjMatrix.get(v)) {
            if (visited.get(w) == 1) {
                pathList.add(new ArrayList<>(path));
                visited.put(w, 0);
                continue;
            }
            dfs(w, visited, path, pathList);
        }
        path.remove(v);
        visited.put(v, 0);
    }

    public static void main(String[] args) {
        GraphPath<Integer> g = new GraphPath<>();
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 2);
        g.addEdge(4, 1);
        g.addEdge(1, 5);
        g.addEdge(6, 2);
        g.getLongestPath();
    }
}
