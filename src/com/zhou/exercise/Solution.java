package com.zhou.exercise;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Solution<T> {
	protected T result;
    public T exec(Object[] args) {
        print("Input:");
        for(var arg: args) {
            print(arg);
        }
        print("---");
        long start = System.currentTimeMillis();
        result = getFunc().apply(args);
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
    public void verify() {
    	boolean res = getDafaultResult().equals(result);
    	print(res ? "OK" : "KO");
    }
    public abstract Object[] getDefaultInput();
    public abstract T getDafaultResult();
    public abstract Function<Object[], T> getFunc();
}
