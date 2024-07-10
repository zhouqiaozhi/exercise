package com.zhou.exercise;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Solution<T> {
    public T exec(Object[] args) {
        print("Input:");
        for(var arg: args) {
            print(arg);
        }
        print("---");
        long start = System.currentTimeMillis();
        var result = getFunc().apply(args);
        long end = System.currentTimeMillis();
        print("Use " + (end - start) + "ms to finish call");
        print("---");
        print("Result:");
        print(result);
        return result;
    }
    private void print(Object obj) {
        if(obj.getClass().isArray()) {
            System.out.println(Arrays.toString((Object[]) obj));
        } else {
            System.out.println(obj);
        }
    }
    public abstract Object[] getDefaultInput();
    public abstract Function<Object[], T> getFunc();
}
