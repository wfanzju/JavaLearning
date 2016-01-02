package gg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by fan on 11/3/15.
 */
public class EulerPath<V> {

    private final Map<V, List<V>> adjMatrix = new HashMap<>();
    private Map<V, List<V>> adjMatrixCopy;

    private Map<V, Integer> inDegree;
    private Map<V, Integer> outDegree;

    public void addVertex(V v) {
        adjMatrix.putIfAbsent(v, new ArrayList<V>());
    }

    public void addEdge(V v, V w) {
        addVertex(v);
        addVertex(w);
        adjMatrix.get(v).add(w);
    }

    public List<V> getEulerPath() {
        Optional<V> startOpt = getEulerPathStart();
        if (!startOpt.isPresent()) {
            throw new IllegalStateException("No EulerPath!");
        }
        adjMatrixCopy = adjMatrix.entrySet().stream()
                .collect(Collectors.toMap(x -> x.getKey(), x -> new ArrayList<>(x.getValue())));
        return getEulerPath(startOpt.get());
    }

    private List<V> getEulerPath(V curr) {
        Stack<V> tmpPath = new Stack<>();
        Stack<V> eulerPath = new Stack<>();
        tmpPath.push(curr);
        while (!tmpPath.isEmpty()) {
            curr = tmpPath.pop();
            while (curr != null) {
                tmpPath.push(curr);
                curr = adjMatrixCopy.get(curr).size() == 0 ? null : adjMatrixCopy.get(curr).remove(0);
            }
            eulerPath.push(tmpPath.pop());
        }
        Collections.reverse(eulerPath);
        return eulerPath;
    }

    private Optional<V> getEulerPathStart() {
        computeInOutDegree();
        Optional<V> startOpt = Optional.empty();
        Optional<V> endOpt = Optional.empty();
        for (V v : adjMatrix.keySet()) {
            if (outDegree.get(v) - inDegree.get(v) == 1) {
                if (startOpt.isPresent()) {
                    return Optional.empty();
                }
                startOpt = Optional.of(v);
            } else if (inDegree.get(v) - outDegree.get(v) == 1) {
                if (endOpt.isPresent()) {
                    return Optional.empty();
                }
                endOpt = Optional.of(v);
            } else if (inDegree.get(v) == outDegree.get(v)) {
                continue;
            } else {
                return Optional.empty();
            }
        }
        if (startOpt.isPresent() && endOpt.isPresent()) {
            return startOpt;
        } else if (!startOpt.isPresent() && !endOpt.isPresent()) {
            return Optional.of(adjMatrix.keySet().iterator().next());
        }
        return Optional.empty();
    }

    private void computeInOutDegree() {
        outDegree = adjMatrix.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> x.getValue().size()));
        inDegree = adjMatrix.keySet().stream()
                .collect(Collectors.toMap(x -> x, x -> 0));
        adjMatrix.values().stream()
                .flatMap(Collection::stream)
                .forEach(x -> inDegree.put(x, inDegree.get(x) + 1));
    }

    public static void main(String[] args) {
        EulerPath<Integer> graph = new EulerPath<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(3, 2);
        graph.addEdge(3, 5);
        graph.addEdge(4, 3);
        graph.addEdge(5, 1);
        graph.addEdge(5, 4);
        graph.addEdge(6, 7);
        graph.addEdge(7, 2);
        System.out.println(graph.getEulerPath());
        System.out.println(graph.adjMatrix);
        System.out.println(graph.adjMatrixCopy);
    }
}