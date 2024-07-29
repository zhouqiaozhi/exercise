package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class Solution1114 extends Solution<Integer[]> {
    List<Integer> q = new ArrayList<>();
    public Integer[] test() throws InterruptedException {
        Foo foo = new Foo();
        var latch = new CountDownLatch(3);
        new Thread(() -> {
            try {
                foo.second(() -> {
                    q.add(2);
                    latch.countDown();
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                foo.first(() -> {
                    q.add(1);
                    latch.countDown();
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();


        new Thread(() -> {
            try {
                foo.third(() -> {
                    q.add(3);
                    latch.countDown();
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        return q.toArray(new Integer[0]);
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{};
    }

    @Override
    protected Integer[] getDafaultResult() {
        return new Integer[]{ 1, 2, 3 };
    }

    @Override
    protected Function<Object[], Integer[]> getFunc() {
        return args -> {
            try {
                return test();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    class Foo {
        // s4
        Semaphore a, b;

        public Foo() {
            a = new Semaphore(0);
            b = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            a.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            a.acquire();
            printSecond.run();
            b.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            b.acquire();
            printThird.run();
        }
        // // s4
        // CountDownLatch a, b;

        // public Foo() {
        //     a = new CountDownLatch(1);
        //     b = new CountDownLatch(1);
        // }

        // public void first(Runnable printFirst) throws InterruptedException {
        //     printFirst.run();
        //     a.countDown();
        // }

        // public void second(Runnable printSecond) throws InterruptedException {
        //     a.await();
        //     printSecond.run();
        //     b.countDown();
        // }

        // public void third(Runnable printThird) throws InterruptedException {
        //     b.await();
        //     printThird.run();
        // }

        // // s3
        // ReentrantLock lock;
        // Condition[] C;
        // boolean[] canStart;
        // public Foo() {
        //     lock = new ReentrantLock();
        //     C = new Condition[]{ lock.newCondition(), lock.newCondition() };
        //     canStart = new boolean[2];

        // }

        // public void first(Runnable printFirst) throws InterruptedException {
        //     lock.lock();
        //     printFirst.run();
        //     canStart[0] = true;
        //     C[0].signal();
        //     lock.unlock();
        // }

        // public void second(Runnable printSecond) throws InterruptedException {
        //     lock.lock();
        //     while(!canStart[0]) {
        //         C[0].await();
        //     }
        //     printSecond.run();
        //     canStart[1] = true;
        //     C[1].signal();
        //     lock.unlock();
        // }

        // public void third(Runnable printThird) throws InterruptedException {
        //     lock.lock();
        //     while(!canStart[1]) {
        //         C[1].await();
        //     }
        //     printThird.run();
        //     lock.unlock();
        // }

        // // s2
        // private int a = 1;
        // private final Object obj = new Object();
        // public Foo() {
        // }

        // public void first(Runnable printFirst) throws InterruptedException {
        //     synchronized(obj) {
        //         printFirst.run();
        //         a++;
        //         obj.notifyAll();
        //     }
        // }

        // public void second(Runnable printSecond) throws InterruptedException {
        //     synchronized(obj) {
        //         while(a != 2) {
        //             obj.wait();
        //         }
        //         printSecond.run();
        //         a++;
        //         obj.notifyAll();
        //     }

        // }

        // public void third(Runnable printThird) throws InterruptedException {
        //     synchronized(obj) {
        //         while(a != 3) {
        //             obj.wait();
        //         }
        //         printThird.run();
        //         obj.notifyAll();
        //     }

        // }
        // // s1
        // AtomicInteger aint;

        // public Foo() {
        //     aint = new AtomicInteger(1);
        // }

        // public void first(Runnable printFirst) throws InterruptedException {
        //     // printFirst.run() outputs "first". Do not change or remove this line.
        //     printFirst.run();
        //     aint.incrementAndGet();
        // }

        // public void second(Runnable printSecond) throws InterruptedException {
        //     while(aint.get() != 2) {}
        //     // printSecond.run() outputs "second". Do not change or remove this line.
        //     printSecond.run();
        //     aint.incrementAndGet();
        // }

        // public void third(Runnable printThird) throws InterruptedException {
        //     while(aint.get() != 3) {}
        //     // printThird.run() outputs "third". Do not change or remove this line.
        //     printThird.run();
        //     aint.incrementAndGet();
        // }
    }
}
