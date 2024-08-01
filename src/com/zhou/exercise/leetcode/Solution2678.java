package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution2678 extends Solution<Integer> {
	
	public Integer countSeniors(String[] details) {
        int res = 0;
        for(var x: details) {
            if(x.charAt(11) == '6' && x.charAt(12) > '0') {
                res++;
            } else if(x.charAt(11) > '6') {
                res++;
            }
        }
        return res;
    }
// 10|1|2|2
// phoneNumber|gender|age|seat

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new String[] { "7868190130M7522","5303914400F9211","9273338290F4010" } };
	}

	@Override
	protected Integer getDafaultResult() {
		return 2;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		// TODO Auto-generated method stub
		return args -> countSeniors((String[]) args[0]);
	}

}
