package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class Solution1226 extends Solution<Boolean> {
    public Boolean test() throws InterruptedException {
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        var latch = new CountDownLatch(4);
        Runnable pickLeftFork = () -> {

        };
        Runnable pickRightFork = () -> {

        };
        Runnable eat = () -> {

        };
        Runnable putLeftFork = () -> {

        };
        Runnable putRightFork = () -> {

        };
        new Thread(() -> {
            try {
                diningPhilosophers.wantsToEat(1, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                diningPhilosophers.wantsToEat(3, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                diningPhilosophers.wantsToEat(2, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);

                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                diningPhilosophers.wantsToEat(4, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        return Boolean.TRUE;
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{ 15 };
    }

    @Override
    protected Boolean getDafaultResult() {
        return Boolean.TRUE;
    }

    @Override
    protected Function<Object[], Boolean> getFunc() {
        return args -> {
            try {
                return test();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    class DiningPhilosophers {

        Semaphore s;

        public DiningPhilosophers() {
            s = new Semaphore(1);
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            s.acquire();
            pickLeftFork.run();
            pickRightFork.run();
//            System.out.println(philosopher + " eat");
            eat.run();
            putLeftFork.run();
            putRightFork.run();
            s.release();
        }
    }
}
