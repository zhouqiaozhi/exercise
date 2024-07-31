package com.zhou.exercise.leetcode;

import java.util.Arrays;
import java.util.function.Function;

import com.zhou.exercise.Solution;

public class Solution1105 extends Solution<Integer> {
	public int minHeightShelves(Integer[][] books, Integer W) {
        int n = books.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for(int i = 1; i <= n; i++) {
            int w = 0;
            int h = 0;
            for(int j = i - 1; j >= 0; j--) {
                w += books[j][0];
                if(w > W) break;
                h = Math.max(h, books[j][1]);
                f[i] = Math.min(f[i], f[j] + h);
            }
        }
        return f[n];
    }
    // mistake: book must in order
    // class Node {
    //     int w;
    //     int h;
    //     Node prev;
    //     Node next;
    //     Node() {

    //     }
    //     Node(int[] v) {
    //         w = v[0];
    //         h = v[1];
    //         prev = null;
    //         next = null;
    //     }
    // }
    // public int minHeightShelves(int[][] books, int W) {
    //     Arrays.sort(books, (a, b) -> b[1] == a[1] ? (b[0] - a[0]) : (b[1] - a[1]));
    //     var head = new Node();
    //     var cur = head;
    //     for(var book: books) {
    //         cur.next = new Node(book);
    //         cur.next.prev = cur;
    //         cur = cur.next;
    //     }
    //     int res = 0;
    //     while(head.next != null) {
    //         cur = head.next;
    //         int h = cur.h;
    //         int w = W;
    //         while(w > 0 && cur != null) {
    //             if(cur.w <= w) {
    //                 w -= cur.w;
    //                 var next = cur.next;
    //                 cur.prev.next = next;
    //                 if(next != null) {
    //                     next.prev = cur.prev;
    //                 }
    //                 cur = next;
    //             } else {
    //                 cur = cur.next;
    //             }
    //         }
    //         res += h;
    //     }
    //     return res;
    // }
	@Override
	protected Object[] getDefaultInput() {
		return new Object[] { new Integer[][] {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}} , 4};
	}

	@Override
	protected Integer getDafaultResult() {
		return 6;
	}

	@Override
	protected Function<Object[], Integer> getFunc() {
		return args -> minHeightShelves((Integer[][]) args[0], (Integer)args[1]);
	}

}
