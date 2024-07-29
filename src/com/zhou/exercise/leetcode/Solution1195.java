package com.zhou.exercise.leetcode;

import com.zhou.exercise.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class Solution1195 extends Solution<List<String>> {
    public List<String> test(Integer n) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        List<String> list = new ArrayList<>();
        var latch = new CountDownLatch(4);
        new Thread(() -> {
            try {
                fizzBuzz.number((x) -> {
                    list.add(String.valueOf(x));
                });
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> {
                    list.add("fizz");
                });
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> {
                    list.add("buzz");
                });
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> {
                    list.add("fizzbuzz");
                });
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
        return list;
    }

    @Override
    protected Object[] getDefaultInput() {
        return new Object[]{ 15 };
    }

    @Override
    protected List<String> getDafaultResult() {
        return List.of("1","2","fizz","4","buzz","fizz","7","8","fizz","buzz","11","fizz","13","14","fizzbuzz");
    }

    @Override
    protected Function<Object[], List<String>> getFunc() {
        return args -> {
            try {
                return test((Integer) args[0]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    class FizzBuzz {
        private int n;
        Semaphore f, b, fb, num;

        public FizzBuzz(int n) {
            this.n = n;
            f = new Semaphore(0);
            b = new Semaphore(0);
            fb = new Semaphore(0);
            num = new Semaphore(0);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            int cur = 3;
            while(cur <= n) {
                f.acquire();
                printFizz.run();
                cur += 3;
                if(cur % 5 == 0) {
                    cur += 3;
                }
                num.release();
            }

        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            int cur = 5;
            while(cur <= n) {
                b.acquire();
                printBuzz.run();
                cur += 5;
                if(cur % 3 == 0) {
                    cur += 5;
                }
                num.release();
            }

        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            int cur = 15;
            while(cur <= n) {
                fb.acquire();
                printFizzBuzz.run();
                cur += 15;
                num.release();
            }

        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for(int i = 1; i <= n; i++) {
                if(i % 3 == 0 && i % 5 == 0) {
                    fb.release();
                } else if(i % 3 == 0) {
                    f.release();
                } else if(i % 5 == 0) {
                    b.release();
                } else {
                    printNumber.accept(i);
                    continue;
                }
                num.acquire();
            }
        }
    }
}
