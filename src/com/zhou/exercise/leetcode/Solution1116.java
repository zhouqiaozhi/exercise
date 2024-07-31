package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class Solution1116 extends Solution<String> {
    public String test(Integer n) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        var latch = new CountDownLatch(2);
        StringBuilder sb = new StringBuilder();
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(sb::append);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(sb::append);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(sb::append);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        return sb.toString();
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{ 5 };
    }

    @Override
    protected String getDafaultResult() {
        return "0102030405";
    }

    @Override
    protected Function<Object[], String> getFunc() {
        return args -> {
            try {
                return test((Integer)args[0]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    class ZeroEvenOdd {
        // s1
        private int n;
        Semaphore z, o, e;
        public ZeroEvenOdd(int n) {
            this.n = n;
            z = new Semaphore(1);
            o = new Semaphore(0);
            e = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for(int i = 0; i < n; i++) {
                z.acquire();
                printNumber.accept(0);
                if(i % 2 == 0) {
                    o.release();
                } else {
                    e.release();
                }


            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for(int i = 2; i <= n; i += 2) {
                e.acquire();
                printNumber.accept(i);
                z.release();
            }

        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for(int i = 1; i <= n; i += 2) {
                o.acquire();
                printNumber.accept(i);
                z.release();
            }
        }
    }
}
