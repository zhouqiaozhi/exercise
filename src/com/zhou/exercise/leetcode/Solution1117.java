package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class Solution1117 extends Solution<String> {
    public String test(String s) throws InterruptedException {
        H2O h2o = new H2O();
        var latch = new CountDownLatch(s.length());
        StringBuilder sb = new StringBuilder();
        for(var c: s.toCharArray()) {
            if(c == 'H') {
                new Thread(() -> {
                    try {
                        h2o.hydrogen(() -> {
                            sb.append("H");
                        });
                        latch.countDown();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        h2o.oxygen(() -> {
                            sb.append("O");
                        });
                        latch.countDown();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
        latch.await();
        return sb.toString();
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{ "OOHHHH" };
    }

    @Override
    protected String getDafaultResult() {
        return "HHOHHO";
    }

    @Override
    protected Function<Object[], String> getFunc() {
        return args -> {
            try {
                return test((String) args[0]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    class H2O {
        Semaphore h, o;

        public H2O() {
            h = new Semaphore(2, true);
            o = new Semaphore(0, true);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            h.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            o.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            o.acquire(2);
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            h.release(2);
        }
    }
}
