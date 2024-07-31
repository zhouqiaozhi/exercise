package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class Solution1115 extends Solution<String> {
    List<String> q = new ArrayList<>();
    public String test(Integer n) throws InterruptedException {
        FooBar fooBar = new FooBar(n);
        var latch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    q.add("foo");
                });
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    q.add("bar");
                });
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        return String.join("", q);
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{ 2 };
    }

    @Override
    protected String getDafaultResult() {
        return "foobarfoobar";
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

    class FooBar {

        // s2
        private int n;
        ReentrantLock lock;
        Condition[] C;
        boolean[] f;

        public FooBar(int n) {
            this.n = n;
            lock = new ReentrantLock();
            C = new Condition[]{ lock.newCondition(), lock.newCondition() };
            f = new boolean[2];
            f[0] = true;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                while(!f[0]) {
                    C[0].await();
                }
                printFoo.run();
                f[0] = false;
                f[1] = true;
                C[1].signal();
                lock.unlock();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                while(!f[1]) {
                    C[1].await();
                }
                printBar.run();
                f[1] = false;
                f[0] = true;
                C[0].signal();
                lock.unlock();
            }
        }

        // // s1
        // private int n;
        // Semaphore a, b;

        // public FooBar(int n) {
        //     this.n = n;
        //     a = new Semaphore(1);
        //     b = new Semaphore(0);
        // }

        // public void foo(Runnable printFoo) throws InterruptedException {

        //     for (int i = 0; i < n; i++) {
        //         a.acquire();
        //     	// printFoo.run() outputs "foo". Do not change or remove this line.
        //     	printFoo.run();
        //         b.release();
        //     }
        // }

        // public void bar(Runnable printBar) throws InterruptedException {

        //     for (int i = 0; i < n; i++) {
        //         b.acquire();
        //         // printBar.run() outputs "bar". Do not change or remove this line.
        //     	printBar.run();
        //         a.release();
        //     }
        // }
    }
}
