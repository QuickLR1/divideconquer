package com.myproject.metrics;

public class Metrics {
    public static long comparisons = 0;
    public static long allocations = 0;
    private static int currentDepth = 0;
    public static int maxDepth = 0;

    private static long startTime = 0;
    private static long endTime = 0;

    public static void reset(){
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }
    public static void enter(){
        currentDepth++;
        if(currentDepth > maxDepth)maxDepth = currentDepth;
    }
    public static void exit(){
        if(currentDepth > 0)currentDepth--;
    }

    public static void addComparison() {
        comparisons++;

    }
    public static void addAllocation(){
        allocations++;
    }
    public static void startTimer(){
        startTime = System.nanoTime();
    }
    public static void stopTimer(){
        endTime = System.nanoTime();
    }
    public static long getElapsedMillis(){
        return (endTime - startTime) / 1_000_000;
    }

    public static String summary() {
        return String.format("comparisons=%d, allocations=%d, maxDepth=%d, time=%d ms",
                comparisons, allocations, maxDepth, getElapsedMillis());
    }

}
