package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution912 extends Solution<Integer[]> {
	
	Integer[] nums;
	int[] tmp;
    public Integer[] sortArray(Integer[] nums) {
        int n = nums.length;
        this.nums = nums;
        tmp = new int[n];
        sort(0, n - 1);
        return nums;
    }

    void sort(int i, int j) {
        if(i == j) return;
        int m = i + (j - i) / 2;
        sort(i, m);
        sort(m + 1, j);
        merge(i, m, m + 1, j);
    }

    void merge(int a, int b, int c, int d) {
        int s = a;
        int start = s;
        while(start <= d) {
            if(c > d) {
                while(a <= b) {
                    tmp[start++] = nums[a++];
                }
                break;
            } else if(a > b) {
                while(c <= d) {
                    tmp[start++] = nums[c++];
                }
                break;
            }
            int aa = nums[a];
            int cc = nums[c];
            if(aa <= cc) {
                tmp[start++] = nums[a++];
            } else {
                tmp[start++] = nums[c++];
            }
        }
        for(; s <= d; s++) {
            nums[s] = tmp[s];
        }
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new Integer[] { 5, 2, 3, 1 } };
	}

	@Override
	protected Integer[] getDafaultResult() {
		return new Integer[] { 1, 2, 3, 5 };
	}

	@Override
	protected Function<Object[], Integer[]> getFunc() {
		return args -> sortArray((Integer[])args[0]);
	}
	
}
