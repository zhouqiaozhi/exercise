package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution1636 extends Solution<Integer[]> {
	int[] f = new int[201];
    int[] tmp;
    public Integer[] frequencySort(Integer[] nums) {
        int n = nums.length;
        tmp = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] += 100;
            f[nums[i]]++;
        }
        sort(nums, 0, n - 1);
        for(int i = 0; i < n; i++) {
            nums[i] -= 100;
        }
        return nums;
    }

    void sort(Integer[] nums, int l, int r) {
        if(l == r) return;
        int m = l + (r - l) / 2;
        sort(nums, l, m);
        sort(nums, m + 1, r);
        merge(nums, l, m, m + 1, r);
    }

    void merge(Integer[] nums, int a, int b, int c, int d) {
        int s = a;
        int start = s;
        while(start <= d) {
            if(a > b) {
                while(c <= d) {
                    tmp[start++] = nums[c++];
                }
                break;
            }
            if(c > d) {
                while(a <= b) {
                    tmp[start++] = nums[a++];
                }
                break;
            }
            int v1 = nums[a];
            int v2 = nums[c];
            if(f[v1] < f[v2]) {
                tmp[start] = v1;
                a++;
            } else if(f[v1] > f[v2]) {
                tmp[start] = v2;
                c++;
            } else {
                tmp[start] = v1 >= v2 ? v1 : v2;
                if(tmp[start] == v1) {
                    a++;
                } else {
                    c++;
                }
            }
            start++;
        }
        for(; s <= d; s++) {
            nums[s] = tmp[s];
        }
    }

//    public int[] frequencySortv1(int[] nums) {
//        int n = nums.length;
//        int[] f = new int[201];
//        Integer[] copy = new Integer[n];
//        for(int i = 0; i < n; i++) {
//            copy[i] = nums[i] + 100;
//            f[copy[i]]++;
//        }
//        Arrays.sort(copy, (a, b) -> {
//            if(f[a] == f[b]) {
//                return b - a;
//            }
//            return f[a] - f[b];
//        });
//        for(int i = 0; i < n; i++) {
//            nums[i] = copy[i] - 100;
//        }
//        return nums;
//    }
	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new Integer[] {1, 1, 2, 2, 2, 3}};
	}

	@Override
	protected Integer[] getDafaultResult() {
		return new Integer[] {3, 1, 1, 2, 2, 2};
	}

	@Override
	protected Function<Object[], Integer[]> getFunc() {
		return args -> frequencySort((Integer[]) args[0]);
	}

}
