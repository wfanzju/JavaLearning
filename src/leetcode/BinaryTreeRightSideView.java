package leetcode;

import helper.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by fan on 7/17/15.
 */
public class BinaryTreeRightSideView {

    public List<Integer> levelOrderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(i == size-1){ // last element in current level
                    result.add(node.getVal());
                }
                if(node.getLeft()!=null){
                    queue.offer(node.getLeft());
                }
                if(node.getRight()!=null){
                    queue.offer(node.getRight());
                }
            }
        }
        return result;
    }

    public List<Integer> dfsRightSideView(TreeNode root){
        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }
    private void dfs(TreeNode curr, List<Integer> res, int currDepth){
        if(curr==null){
            return;
        }
        if(currDepth==res.size()){
            res.add(curr.getVal());
        }
        dfs(curr.getRight(), res, currDepth+1);
        dfs(curr.getLeft(), res, currDepth+1);
    }
}
