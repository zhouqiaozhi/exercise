package com.zhou.exercise;

public class Main {
    public static void main(String[] args) throws Exception {
        // reminder
        // btree: 1395
        // concurrency: 1114,1115,1116,1117,1195,1226
        String site = "leetcode";
        String exercise = "Solution1105";
        Class clazz = Class.forName("com.zhou.exercise." + site + "." + exercise);
        Solution x = (Solution) clazz.getDeclaredConstructors()[0].newInstance();
        x.exec();
        x.verify();
    }
}
