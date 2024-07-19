package com.zhou.exercise;

public class Main {
    public static void main(String[] args) throws Exception {
        String site = "leetcode";
        String exercise = "Solution1380";
        Class clazz = Class.forName("com.zhou.exercise." + site + "." + exercise);
        Solution x = (Solution) clazz.getDeclaredConstructors()[0].newInstance();
        x.exec();
        x.verify();
    }
}
