package com.zhou.exercise;

import java.util.Arrays;
import java.util.function.Function;

public abstract class Solution<T> {
	protected T result;
	public T exec() {
		return exec(getDefaultInput());
	}
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
    public void verify() {
    	verify(null);
    }
    public void verify(T expected) {
    	expected = expected == null ? getDafaultResult() : expected;
    	boolean res = expected.equals(result);
    	print(res ? "OK" : "KO");
    }
    private void print(Object obj) {
        if(obj.getClass().isArray()) {
            System.out.println(Arrays.toString((Object[]) obj));
        } else {
            System.out.println(obj);
        }
    }
    protected abstract Object[] getDefaultInput();
    protected abstract T getDafaultResult();
    protected abstract Function<Object[], T> getFunc();
}
