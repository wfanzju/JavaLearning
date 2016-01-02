package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fan on 7/15/15.
 * <p>
 * PrefixTree
 */
public class Trie {
    private TrieNode<Character> root;

    public Trie() {
        root = new TrieNode<>(null);
    }

    public void insert(String word) {
        TrieNode<Character> curr = root;
        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c - 'a', new TrieNode<>(c));
            curr = curr.children.get(c - 'a');
        }
        curr.isLeaf = true;
    }

    public boolean search(String word) {
        TrieNode<Character> curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children.get(c - 'a') == null) {
                return false;
            }
            curr = curr.children.get(c - 'a');
        }
        return curr.isLeaf;
    }

    public boolean startsWith(String prefix) {
        TrieNode<Character> curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children.get(c - 'a') == null) {
                return false;
            }
            curr = curr.children.get(c - 'a');
        }
        return true;
    }

    public String findCommonPrefix() {
        StringBuilder res = new StringBuilder();
        TrieNode<Character> curr = root;
        while (curr != null && curr.children.size() == 1 && !curr.isLeaf) {
            curr = curr.children.values().iterator().next();
            res.append(curr.value);
        }
        return res.toString();
    }

    public List<String> findDirectories() {
        StringBuilder prefix = new StringBuilder();
        List<String> res = new ArrayList<>();
        for (TrieNode<Character> child : root.children.values()) {
            dfsTrie(child, prefix, res);
        }
        return res;
    }

    private void dfsTrie(TrieNode<Character> node, StringBuilder prefix, List<String> res) {
        prefix.append(node.value);
        if (!node.isLeaf && node.children.size() > 1) {
            int lastSlash = prefix.lastIndexOf("/");
            if (lastSlash == -1 || prefix.substring(lastSlash + 1).length() > 1) {
                prefix.append("/");
            }
        } else if (node.isLeaf) {
            res.add(prefix.toString());
        }
        for (TrieNode<Character> child : node.children.values()) {
            dfsTrie(child, prefix, res);
        }
        prefix.deleteCharAt(prefix.length() - 1);
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("usrbinjava");
        t.insert("usrbinperl");
        t.insert("usrbinpython");
        t.insert("usr");
        System.out.println(t.search("usrbinpytho"));
        System.out.println(t.startsWith("usrbin"));
        System.out.println(t.findCommonPrefix());
        System.out.println(t.findDirectories());
    }
}

class TrieNode<T> {
    final Map<Integer, TrieNode<T>> children;
    T value;
    boolean isLeaf;

    TrieNode(T val) {
        children = new HashMap<>();
        value = val;
        isLeaf = false;
    }

}