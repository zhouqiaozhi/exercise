package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;
import com.zhou.exercise.leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Solution1110 extends Solution<List<TreeNode>> {
    boolean[] delete = new boolean[1001];
    List<TreeNode> res = new ArrayList<>();
    public List<TreeNode> delNodes(TreeNode root, Integer[] to_delete) {
        for(var x: to_delete) {
            delete[x] = true;
        }
        dfs(root, true);
        return res;
    }

    TreeNode dfs(TreeNode node, boolean isHead) {
        if(node == null) {
            return null;
        }
        var left = node.left;
        var right = node.right;

        if(delete[node.val]) {
            node = null;
            dfs(left, true);
            dfs(right, true);
        } else {
            if(isHead) {
                res.add(node);
            }
            node.left = dfs(node.left, false);
            node.right = dfs(node.right, false);
        }
        return node;
    }
    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{ new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7))), new Integer[]{3, 5} };
    }

    @Override
    protected List<TreeNode> getDafaultResult() {
        return List.of(new TreeNode(1, new TreeNode(2, new TreeNode(4), null), null), new TreeNode(6), new TreeNode(7));
    }

    @Override
    protected Function<Object[], List<TreeNode>> getFunc() {
        return (args) -> delNodes((TreeNode)args[0], (Integer[])args[1]);
    }
}
