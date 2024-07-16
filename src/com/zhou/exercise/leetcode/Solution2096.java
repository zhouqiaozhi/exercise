package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;
import com.zhou.exercise.leetcode.model.TreeNode;

public class Solution2096 extends Solution<String> {
	StringBuilder sb = new StringBuilder();
    int a, b;
    int an = 0;
    public String getDirections(TreeNode root, int a, int b) {
        this.a = a;
        this.b = b;
        dfs4(root);
        while(an > 0) {
            sb.append("U");
            an--;
        }
        return sb.reverse().toString();
    }

//     1 has a
//     2 has b
//     3 has ab
    int dfs4(TreeNode node) {
        if(node == null) return 0;
        int v = node.val == a ? 1 : (node.val == b ? 2 : 0);
        int lv = dfs4(node.left);
        int rv = dfs4(node.right);
        int nxt = lv | rv;
        if(nxt == 1) {
            an++;
        } else if(nxt == 2) {
            if(lv == 2) {
                sb.append("L");
            } else {
                sb.append("R");
            }
        } else if(nxt == 3) {
            if(lv != 3 && rv != 3) {
                an++;
                if(lv == 2) {
                    sb.append("L");
                } else {
                    sb.append("R");
                }
            }
        }
        return v | nxt;
    }
    
    // v1
    TreeNode parent;
//    int a;
//    int b;
//    StringBuilder sb = new StringBuilder();
    public String getDirectionsv1(TreeNode root, int a, int b) {
        // find common parent
        this.a = a;
        this.b = b;
        dfs(root);
        // build a to parent
        // System.out.println(parent.val);
        dfs2(parent);
        // build parent to b
        int start = sb.length();
        dfs3(parent);
        reverse(start, sb.length() - 1);
        return sb.toString();
    }

    void reverse(int start, int end) { 
        while(start < end) {
            swap(start++, end--);
        }
    }

    void swap(int i, int j) {
        char t = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, t);
    }

    boolean dfs3(TreeNode node) {
        if(node == null) {
            return false;
        }
        var ok = isB(node.val);
        var lok = dfs3(node.left);
        var rok = dfs3(node.right);
        if(lok) {
            sb.append("L");
        } else if(rok) {
            sb.append("R");
        }
        return ok || lok || rok;
    }

    boolean dfs2(TreeNode node) {
        if(node == null) {
            return false;
        }
        var ok = isA(node.val);
        var lok = dfs2(node.left);
        var rok = dfs2(node.right);
        if(lok || rok) {
            sb.append("U");
        }
        return ok || lok || rok;
    }

    boolean dfs(TreeNode node) {
        if(node == null) return false;
        var ok = isOne(node.val);
        var lok = dfs(node.left);
        var rok = dfs(node.right);
        if(v(ok) + v(lok) + v(rok) == 2) {
            parent = node;
        }
        return ok || lok || rok;
    }

    int v(boolean v) {
        return v ? 1 : 0;
    }

    boolean isOne(int v) {
        return isA(v) || isB(v);
    }

    boolean isA(int v) {
        return v == a;
    }

    boolean isB(int v) {
        return v == b;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new TreeNode(2, new TreeNode(1), null), 2, 1 };
	}

	@Override
	protected String getDafaultResult() {
		return "L";
	}

	@Override
	protected Function<Object[], String> getFunc() {
		// TODO Auto-generated method stub
		return (args) -> getDirections((TreeNode)args[0], (Integer)args[1], (Integer)args[2]);
	}
}
