package gg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fan on 10/21/15.
 */
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;
    }
    private final Map<Integer, DLinkedNode> cache;
    private final int capacity;
    private final DLinkedNode head; // dummy head
    private final DLinkedNode tail; // dummy tail

    private int count;

    public LRUCache(int capacity) {
        this.count = 0;
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.head = new DLinkedNode();
        head.pre = null;
        this.tail = new DLinkedNode();
        tail.next = null;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            throw new RuntimeException("No such key found!");
        }
        this.moveToHead(node);
        return node.value;
    }

    public void set(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node != null) {
            node.value = value;
            this.moveToHead(node);
        } else {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);
            count++;
            if (count > capacity) {
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                count--;
            }
        }
    }

    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.next;
        pre.next = post;
        post.pre = pre;
    }

    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }
}
