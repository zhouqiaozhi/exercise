package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution1653 extends Solution<Integer> {
	
	public Integer minimumDeletions(String S) {
        int n = S.length();
        char[] chs = S.toCharArray();
        int f = 0;
        int[] s = new int[n + 1];
        for(int a = n - 1; a >= 0; a--) {
            s[a] += -(chs[a] - 'b') + s[a + 1];
        }
        int del = s[0];
        for(int a = 0; a < n; a++) {
            f += chs[a] - 'a';
            del = Math.min(del, f + s[a + 1]);
        }
        return del;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { "aababbab" };
	}

	@Override
	protected Integer getDafaultResult() {
		return 2;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		return args -> minimumDeletions((String)args[0]);
	}

}
