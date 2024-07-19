package com.zhou.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution1380 extends Solution<List<Integer>> {
	
	public List<Integer> luckyNumbers (Integer[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] v = new int[m];
        int[] h = new int[n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                v[i] = v[i] == 0 ? matrix[i][j] : Math.min(v[i], matrix[i][j]);
                h[j] = Math.max(h[j], matrix[i][j]);
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(v[i] == matrix[i][j] && h[j] == matrix[i][j]) {
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new Integer[][] {{7,8},{1,2}} };
	}

	@Override
	protected List<Integer> getDafaultResult() {
		return List.of(7);
	}

	@Override
	protected Function<Object[], List<Integer>> getFunc() {
		return args -> luckyNumbers((Integer[][])args[0]);
	}

}
