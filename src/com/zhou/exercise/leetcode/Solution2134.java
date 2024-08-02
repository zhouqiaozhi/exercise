package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution2134 extends Solution<Integer>{
	
	public Integer minSwaps(Integer[] nums) {
        int n = nums.length;
        int total = 0;
        for(int x: nums) {
            total += x;
        }
        if(total == n) return 0;
        int i = 0;
        int count = 0;
        for(; i < total; i++) {
            count += nums[i];
        }
        int res = total - count;
        for(; i < n + total - 1; i++) {
            count -= nums[i - total];
            count += nums[i % n];
            res = Math.min(res, total - count);
        }
        return res;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] {new Integer[] {1,1,0,0,1}};
	}

	@Override
	protected Integer getDafaultResult() {
		return 0;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		return args -> minSwaps((Integer[])args[0]);
	}

}
