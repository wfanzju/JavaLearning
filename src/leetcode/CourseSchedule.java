package leetcode;

import java.util.*;

/**
 * Created by fan on 7/19/15.
 */
public class CourseSchedule {

    // BFS, Topological Sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Map between course and its subsequent courses
        Map<Integer, Set<Integer>> courseGraph = new HashMap<>();
        // number of prerequisites for each course
        int[] indegree = new int[numCourses];
        Arrays.fill(indegree, 0);
        for (int i = 0; i < numCourses; i++) {
            courseGraph.put(i, new HashSet<>());
        }
        for (int[] prerequisite : prerequisites) {
            if (courseGraph.get(prerequisite[1]).add(prerequisite[0])) {
                indegree[prerequisite[0]]++;
            }
        }
        Queue<Integer> bfsQueue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                bfsQueue.offer(i);
            }
        }
        List<Integer> topoOrder = new ArrayList<>();
        while (!bfsQueue.isEmpty()) {
            int course = bfsQueue.poll();
            topoOrder.add(course);
            for (int neighbor : courseGraph.get(course)) {
                if (--indegree[neighbor] == 0) {
                    bfsQueue.offer(neighbor);
                }
            }
//            courseGraph.get(course)
//                    .stream()
//                    .filter(neighbor -> --indegree[neighbor] == 0)
//                    .forEach(bfsQueue::offer);
        }
//        System.out.print(topoOrder.stream().mapToInt(x->x).toArray());
//        return finished == numCourses;
        if (topoOrder.size() != numCourses) {
            throw new IllegalStateException("CycleFoundException");
        }
        System.out.println(topoOrder);
        return Arrays.stream(indegree)
                .filter(x -> x != 0)
                .count() == 0;
    }

    // DFS
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> courseGraph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            courseGraph.put(i, new HashSet<>());
        }
        for (int[] prerequisite : prerequisites) {
            courseGraph.get(prerequisite[1]).add(prerequisite[0]);
        }
        int[] visited = new int[numCourses];
        Arrays.fill(visited, 0);
        Stack<Integer> topoOrder = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfsRec(i, visited, courseGraph, topoOrder)) {
                    return false;
                }
            }
        }
        while (!topoOrder.empty()) {
            System.out.print(topoOrder.pop() + " ");
        }
        return true;
    }

    private boolean dfsRec(int i, int[] visited, Map<Integer, Set<Integer>> courseGraph,
                           Stack<Integer> topoOrder) {
        visited[i] = 1;
        for (int j : courseGraph.get(i)) {
            if (visited[j] == 0) {
                if (!dfsRec(j, visited, courseGraph, topoOrder)) {
                    return false;
                }
            } else if (visited[j] == 1) {
                return false;
            }
        }
        visited[i] = 2;
        topoOrder.push(i);
        return true;
    }
}
