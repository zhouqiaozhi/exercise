package com.zhou.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.zhou.exercise.Solution;
import com.zhou.exercise.leetcode.model.TreeNode;

public class Solution1530 extends Solution<Integer> {
	int D;
    int res = 0;
    public int countPairs(TreeNode root, int D) {
        this.D = D;
        dfs2(root);
        return res;
    }

    int[] dfs2(TreeNode node) {
        if(node == null) {
            return new int[D + 1];
        }
        if(node.left == null && node.right == null) {
            var cur = new int[D + 1];
            cur[1] = 1;
            return cur;
        }
        var left = dfs2(node.left);
        var right = dfs2(node.right);
        for(int i = 1; i < D; i++) {
            for(int j = 1; j <= D - i; j++) {
                res += left[i] * right[j];
            }
        }
        int[] cur = new int[D + 1];
        for(int i = 2; i < D; i++) {
            cur[i] = left[i - 1] + right[i - 1];
        }
        return cur;
    }


    // int D;
    // int res = 0;
    public int countPairsv1(TreeNode root, int D) {
        this.D = D;
        dfs(root, 0);
        return res;
    }

    List<Integer> dfs(TreeNode node, int depth) {
        if(node == null) {
            return null;
        }
        if(node.left == null && node.right == null) {
            return List.of(depth);
        }
        var left = dfs(node.left, depth + 1);
        var right = dfs(node.right, depth + 1);
        if(left == null) {
            return right;
        } else if(right == null) {
            return left;
        }
        for(var l: left) {
            for(var r: right) {
                if((l - depth + r - depth) <= D) {
                    res += 1;
                }
            }
        }
        var list = new ArrayList<>(left);
        list.addAll(right);
        return list;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3)), 3 };
	}

	@Override
	protected Integer getDafaultResult() {
		return 1;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		return (args) -> countPairs((TreeNode)args[0], (Integer)args[1]);
	}
}
