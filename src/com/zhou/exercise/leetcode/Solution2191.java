package com.zhou.exercise.leetcode;

import java.util.Arrays;
import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution2191 extends Solution<Integer[]> {
	
	public Integer[] sortJumbled(Integer[] mapping, Integer[] nums) {
        int n = nums.length;
        int[][] f = new int[n][2];
        for(int i = 0; i < n; i++) {
            f[i][0] = convert(nums[i], mapping);
            f[i][1] = nums[i];
        }
        Arrays.sort(f, (a, b) -> a[0] - b[0]);
        for(int i = 0; i < n; i++) {
            nums[i] = f[i][1];
        }
        return nums;
    }

    int convert(int x, Integer[] map) {
        if(x == 0) return map[x];
        int res = 0;
        int k = 1;
        while(x != 0) {
            res += map[(x % 10)] * k;
            x /= 10;
            k *= 10;
        }
        return res;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new Integer[] {8,9,4,0,2,1,3,5,7,6}, new Integer[] {991,338,38} };
	}

	@Override
	protected Integer[] getDafaultResult() {
		return new Integer[] { 338,38,991 };
	}

	@Override
	protected Function<Object[], Integer[]> getFunc() {
		return args -> sortJumbled((Integer[]) args[0], (Integer[]) args[1]);
	}

}
