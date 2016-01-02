package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fan on 11/21/15.
 */
public class WordSearch {
    class TrieNode {
        boolean isWord = false;
        TrieNode[] children = new TrieNode[26];
    }

    private final TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, Set<String> words) {
        Set<String> res = new HashSet<>();
        buildTrie(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children[board[i][j] - 'a'] != null) {
                    dfs(board, visited, i, j, root, "", res);
                }
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j,
                     TrieNode node, String word, Set<String> res) {
        if (!inside(board, i, j) || visited[i][j]
                || node.children[board[i][j] - 'a'] == null) {
            return;
        }
        visited[i][j] = true;
        node = node.children[board[i][j] - 'a'];
        if (node.isWord) {
            res.add(word + board[i][j]);
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dirs) {
            dfs(board, visited, i + d[0], j + d[1], node, word + board[i][j], res);
        }
        visited[i][j] = false;
    }

    private boolean inside(char[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }

    private void buildTrie(Set<String> words) {
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }
    }
}
