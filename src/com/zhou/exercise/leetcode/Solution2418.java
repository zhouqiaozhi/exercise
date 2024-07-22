package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.Arrays;
import java.util.function.Function;

public class Solution2418 extends Solution<String[]> {
    public String[] sortPeople(String[] names, Integer[] heights) {
        int n = names.length;
        Integer[] f = new Integer[n];
        for(int i = 0; i < n; i++) {
            f[i] = i;
        }
        Arrays.sort(f, (a, b) -> Integer.compare(heights[b], heights[a]));
        for(int i = 0; i < n; i++) {
            int a = i;
            while(f[a] != -1 && f[f[a]] != -1) {
                swap(names, a, f[a]);
                int t = f[a];
                f[a] = -1;
                a = t;
            }
            f[a] = -1;
        }
        return names;
    }

    public String[] sortPeoplev1(String[] names, Integer[] heights) {
        int n = names.length;
        Integer[] f = new Integer[n];
        for(int i = 0; i < n; i++) {
            f[i] = i;
        }
        Arrays.sort(f, (a, b) -> Integer.compare(heights[b], heights[a]));
        var A = new String[n];
        for(int i = 0; i < n; i++) {
            A[i] = names[f[i]];
        }
        return A;
    }

    void swap(String[] A, int i, int j) {
        var t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[] { new String[]{"Mary","John","Emma"}, new Integer[]{180,165,170} };
    }

    @Override
    protected String[] getDafaultResult() {
        return new String[] {"Mary","Emma","John"};
    }

    @Override
    protected Function<Object[], String[]> getFunc() {
        return (args) -> sortPeople((String[]) args[0], (Integer[]) args[1]);
    }
    // [11578,89340,73785,12096,55734,89484,59775,72652]
    //    0.    1.    2.    3.    4.    5.    6.    7
    //    5.    1.    2.    7     6.    4.    3.    1

    //    [0, 5]
    //    [5, 4]
    //    [4, 6]
    //    [6, 3]
    //    [3, 7]
    //    [7, 1]
    //    1 == 1
    //    2 == 2
    //    complete

    // // ["GXLVEHVABFOGSFXUYYR","TUHxnsxmu","X","OOYBLVKmzlaeaxbprc","ARNLAPtfvmutkfsa","XPMKPDUWOQEEILtbdjip","QICEutjbr","R"]
    //               0.               1.      2.          3.                  4.                 5.                  6.      7
    // // ["XPMKPDUWOQEEILtbdjip","TUHxnsxmu","X","R","QICEutjbr","ARNLAPtfvmutkfsa","OOYBLVKmzlaeaxbprc","GXLVEHVABFOGSFXUYYR"]
    //               0.               1.       2.  3.     4.             5.                     6.                   7.
}
