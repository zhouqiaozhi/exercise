package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;
/*
url: https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
*/
public class Solution1190 extends Solution<String> {

	@Override
	public Object[] getDefaultInput() {
		// TODO Auto-generated method stub
		return new Object[]{ "f(ul)ao(t(y)qbn)()sj" };
	}

	@Override
	public Function<Object[], String> getFunc() {
		// TODO Auto-generated method stub
		return (args) -> reverseParentheses((String)args[0]);
	}
	
	@Override
	public String getDafaultResult() {
		// TODO Auto-generated method stub
		return "fluaonbqytsj";
	}
	
	char[] chs;
    int i = 0;
    public String reverseParentheses(String s) {
        // dfs
        chs = s.toCharArray();
        while(i < chs.length) {
            if(chs[i] == '(') {
                i++;
                dfs();
            }
            i++;
        }
        var sb = new StringBuilder();
        for(var c: chs) {
            if(c != '(' && c != ')') {
                sb.append(c);
            }
        }
        return sb.toString();
    }



    void dfs() {
        int start = i;
        while(i < chs.length && chs[i] != ')') {
            if(chs[i] == '(') {
                i++;
                dfs();
            }
            i++;
        }
        int end = i - 1;
        if(start >= end) return; // if frist char is ')'
        reverse(start, end);
    }

    void reverse(int i, int j) {
        while(i < j) {
            if(skip(i)) {
                i++;
                continue;
            }
            if(skip(j)) {
                j--;
                continue;
            }
            swap(i++, j--);
        }
    }

    boolean skip(int i) {
        return chs[i] == '(' || chs[i] ==')';
    }

    void swap(int i, int j) {
        char t = chs[i];
        chs[i] = chs[j];
        chs[j] = t;
    }

}
