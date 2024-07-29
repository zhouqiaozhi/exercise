package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.function.Function;

public class Solution1395 extends Solution<Integer> {
    public int numTeams(Integer[] R) {
        int n = R.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int x: R) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        var l = new BTree(min, max);
        var r = new BTree(min, max);
        for(int x: R) {
            r.update(x, 1);
        }
        int res = 0;
        for(int i = 0; i < n; i++) {
            int x = R[i];
            r.update(x, -1);
            int lmn = l.query(x - 1);
            int rmn = r.query(x - 1);
            int lmx = i - lmn;
            int rmx = (n - i - 1) - rmn;
            res += lmn * rmx + lmx * rmn;
            l.update(x, 1);
        }
        return res;
    }
    class BTree {
        int[] f;
        int off;
        BTree(int min, int max) {
            off = min;
            f = new int[max - min + 2];
        }
        int query(int x) {
            int res = 0;
            x = x - off + 1;
            while(x != 0) {
                res += f[x];
                x -= x & -x;
            }
            return res;
        }
        void update(int x, int v) {
            x = x - off + 1;
            while(x < f.length) {
                f[x] += v;
                x += x & -x;
            }
        }
    }
    // v1
    // public int numTeams(int[] R) {
    //     int n = R.length;
    //     int[][] f = new int[3][n];
    //     Arrays.fill(f[0], 1);
    //     for(int i = 1; i < n; i++) {
    //         for(int k = 1; k <= 2; k++) {
    //             for(int j = 0; j < i; j++) {
    //                 if(R[j] < R[i]) {
    //                     f[k][i] += f[k - 1][j];
    //                 }
    //             }
    //         }
    //     }
    //     int res = Arrays.stream(f[2]).sum();
    //     Arrays.fill(f[1], 0);
    //     Arrays.fill(f[2], 0);
    //     for(int i = 1; i < n; i++) {
    //         for(int k = 1; k <= 2; k++) {
    //             for(int j = 0; j < i; j++) {
    //                 if(R[j] > R[i]) {
    //                     f[k][i] += f[k - 1][j];
    //                 }
    //             }
    //         }
    //     }
    //     res += Arrays.stream(f[2]).sum();
    //     return res;
    // }
    @Override
    protected Object[] getDefaultInput() {
        return new Object[] { new Integer[]{2,5,3,4,1} };
    }

    @Override
    protected Integer getDafaultResult() {
        return 3;
    }

    @Override
    protected Function<Object[], Integer> getFunc() {
        return (args) -> numTeams((Integer[]) args[0]);
    }
}
