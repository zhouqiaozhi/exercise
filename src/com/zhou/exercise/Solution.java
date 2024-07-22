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
        boolean res = true;
        if(expected.getClass().isArray()) {
            var ex = (Object[]) expected;
            var re = (Object[]) result;
            for(int i = 0; i < ex.length; i++) {
                res = res && ex[i].equals(re[i]);
            }
        } else {
            res = expected.equals(result);
        }

    	print(res ? "OK" : "KO");
    }
    private void print(Object obj) {
    	print(obj, "");
    }
    
    private void print(Object obj, String space) {
        if(obj.getClass().isArray()) {
        	var array = (Object[])obj;
        	if(array[0].getClass().isArray()) {
        		System.out.println(space + "[");
        		for(var o: array) {
        			print(o, space + " ");
        		}
            	System.out.println(space + "]");
        	} else {
        		System.out.println(space + Arrays.toString((Object[]) obj));
        	}
        } else {
            System.out.println(space + obj);
        }
    }
    protected abstract Object[] getDefaultInput();
    protected abstract T getDafaultResult();
    protected abstract Function<Object[], T> getFunc();
}
