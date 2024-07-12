package com.zhou.exercise.leetcode;

import java.util.Stack;
import java.util.function.Function;

import com.zhou.exercise.Solution;

/*
 * url: https://leetcode.com/problems/maximum-score-from-removing-substrings/
 */
public class Solution1717 extends Solution<Integer> {
	
	public int maximumGain(String s, int x, int y) {
		// try queue/stack greed
        // aba is impossible
        return exec(s, x, y, 'b', 'a');
    }
    int exec(String s, int x, int y, char sx, char sy) {
        if(y > x) {
            return exec(s, y, x, sy, sx);
        }
        
        var q = new Stack<Character>();
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == sx && !q.isEmpty() && q.peek() == sy) {
                q.pop();
                res += x;
                continue;
            }
            q.add(s.charAt(i));
        }
        int count = 0;
        while(!q.isEmpty()) {
            char last = q.pop();
            if(count > 0 && last == sx) {
                count--;
                res += y;
                continue;
            }
            if(last == sy) {
                count++;
                continue;
            }
            count = 0;
        }
        return res;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { "cdbcbbaaabab", 4, 5 };
	}

	@Override
	protected Integer getDafaultResult() {
		return 19;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		return (args) -> maximumGain((String)args[0], (Integer)args[1], (Integer)args[2]);
	}
	
}
