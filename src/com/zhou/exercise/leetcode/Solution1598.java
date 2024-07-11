package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;

/*
    url: https://leetcode.com/problems/crawler-log-folder/
 */
public class Solution1598 extends Solution<Integer> {
    @Override
    public Object[] getDefaultInput() {
        return new Object[]{ new String[]{"d1/","d2/","../","d21/","./"} };
    }
    
    @Override
	public Integer getDafaultResult() {
		// TODO Auto-generated method stub
		return 2;
	}

    @Override
    public Function<Object[], Integer> getFunc() {
        return (args) -> minOperations((String[])args[0]);
    }

    public int minOperations(String[] logs) {
        // Looks like queue/stack
        int n = logs.length;
        Stack<String> q = new Stack<>();
        for(var x: logs) {
            if(x.length() == 2 && x.charAt(0) == '.') { // skip ./
                continue;
            } else if(x.length() == 3 && x.charAt(0) == '.' && x.charAt(1) == '.') { // exec ../
                if(!q.isEmpty()) {
                    q.pop();
                }
            } else { // add x/
                q.add(x);
            }
        }
        return q.size();
    }

}
