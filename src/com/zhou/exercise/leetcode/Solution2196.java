package com.zhou.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.zhou.exercise.Solution;
import com.zhou.exercise.leetcode.model.TreeNode;

/*
 * url: https://leetcode.com/problems/create-binary-tree-from-descriptions/
 */
public class Solution2196 extends Solution<TreeNode> {
	// v2
    public TreeNode createBinaryTree(Integer[][] A) {
        // [value, TreeNode]
        // [value, in]
        TreeNode[] f = new TreeNode[100001];
        int[] in = new int[100001];
        for(var a: A) {
            if(f[a[0]] == null) f[a[0]] = new TreeNode(a[0]);
            if(f[a[1]] == null) f[a[1]] = new TreeNode(a[1]);
            if(a[2] == 1) {
                f[a[0]].left = f[a[1]];
            } else {
                f[a[0]].right = f[a[1]];
            }
            in[a[1]]++;
        }
        for(var a: A) {
            if(in[a[0]] == 0) {
                return f[a[0]];
            }
        }
        return new TreeNode();
    }
    // v1
    Map<Integer, Integer[]> map1 = new HashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    public TreeNode createBinaryTreev1(Integer[][] A) {
        // [value, [left, right]]
        // [value, in]
        for(int i = 0; i < A.length; i++) {
            map1.putIfAbsent(A[i][0], new Integer[2]);
            map1.get(A[i][0])[A[i][2]] = A[i][1];
            map2.putIfAbsent(A[i][0], 0);
            map2.merge(A[i][1], 1, Integer::sum);
        }
        for(var entry: map2.entrySet()) {
            if(entry.getValue() == 0) {
                return build(entry.getKey());
            }
        }
        return new TreeNode();
    }

    TreeNode build(Integer root) {
        if(root == null) {
            return null;
        }
        var node = new TreeNode(root);
        var next = map1.get(root);
        if(next != null) {
            node.left = build(next[1]);
            node.right = build(next[0]);
        }
        return node;
    }

	@Override
	protected Object[] getDefaultInput() {
		// TODO Auto-generated method stub
		return new Object[] { new Integer[][]{{20,15,1},{20,17,0},{50,20,1},{50,80,0},{80,19,1}} };
	}

	@Override
	protected TreeNode getDafaultResult() {
		// TODO Auto-generated method stub
		return new TreeNode(50, new TreeNode(20, new TreeNode(15), new TreeNode(17)), new TreeNode(80, new TreeNode(19), null));
	}

	@Override
	protected Function<Object[], TreeNode> getFunc() {
		return (args) -> createBinaryTree((Integer[][])args[0]);
	}
	
}
