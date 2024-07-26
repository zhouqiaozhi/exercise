package com.zhou.exercise.leetcode;

import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution1334 extends Solution<Integer> {
	public Integer findTheCity(Integer n, Integer[][] edges, Integer d) {
        int[][] f = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j) f[i][j] = Integer.MAX_VALUE;
            }
        }
        for(var e: edges) {
            f[e[0]][e[1]] = e[2];
            f[e[1]][e[0]] = e[2];
        }

        for(int center = 0; center < n; center++) {
            for(int i = 0; i < n; i++) {
                if(f[i][center] == Integer.MAX_VALUE) continue;
                for(int j = i; j < n; j++) {
                    if(f[center][j] != Integer.MAX_VALUE && f[i][j] > (f[i][center] + f[center][j])) {
                        f[i][j] = f[j][i] = f[i][center] + f[center][j];
                    }
                }
            }
        }

        int count = Integer.MAX_VALUE;
        int max = 0;
        for(int i = 0; i < n; i++) {
            int c = 0;
            for(int j = 0; j < n; j++) {
                if(f[i][j] <= d) {
                    c++;
                }
            }
            if(c <= count) {
                count = c;
                max = i;
            }
        }
        return max;
    }
    // // v1
    // int[][] f;
    // int[] s;
    // int N;
    // int d;
    // int minVal = Integer.MAX_VALUE;
    // int max = 0;
    // public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    //     N = n;
    //     f = new int[N][N];
    //     s = new int[N];
    //     Arrays.fill(s, Integer.MAX_VALUE);
    //     d = distanceThreshold;
    //     for(var e: edges) {
    //         f[e[0]][e[1]] = e[2];
    //         f[e[1]][e[0]] = e[2];
    //     }
    //     for(int i = 0; i < N; i++) {
    //         if(s[i] == Integer.MAX_VALUE) {
    //             s[i] = 0;
    //             dfs(i, i);
    //             calc();
    //         }
    //     }
    //     return max;
    // }

    // void calc() {
    //     for(int i = 0; i < N; i++) {
    //         if(s[i] != Integer.MAX_VALUE) {
    //             int count = 0;
    //             for(int j = 0; j < N; j++) {
    //                 if(s[j] != Integer.MAX_VALUE && Math.abs(s[j] - s[i]) <= d) {
    //                     count++;
    //                 }
    //             }
    //             if(count < minVal) {
    //                 minVal = count;
    //                 max = i;
    //             } else if(count == minVal && i > max) {
    //                 max = i;
    //             }
    //         }
    //     }
    //     for(int i = 0; i < N; i++) {
    //         if(s[i] != Integer.MAX_VALUE) {
    //             s[i] = Integer.MAX_VALUE;
    //         }
    //     }
    // }

    // void dfs(int parent, int cur) {
    //     s[cur] = s[parent] + f[parent][cur];
    //     for(int i = 0; i < N; i++) {
    //         if(f[cur][i] != 0 && s[i] > s[cur] + f[cur][i]) {
    //             dfs(cur, i);
    //         }
    //     }
    // }

	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { 4, new Integer[][]{{0,1,3},{1,2,1},{1,3,4},{2,3,1}}, 4};
	}

	@Override
	protected Integer getDafaultResult() {
		return 3;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		return args -> findTheCity((Integer)args[0], (Integer[][])args[1], (Integer)args[2]);
	}

}
