package com.zhou.exercise.leetcode.model;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public boolean equals(Object obj) {
    	return equals((TreeNode)obj);
    }
    
    public boolean equals(TreeNode node) {
    	if(this.val != node.val) {
    		return true;
    	}
    	boolean res = true;
    	if(left == null) {
    		res = res && node.left == null;
    	} else {
    		res = left.equals(node.left);
    	}
    	if(right == null) {
    		res = res && node.right == null;
    	} else {
    		res = right.equals(node.right);
    	}
    	return res;
    }
	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}
}
