package com.myproject.dnc;

public class Metrics {
    public static long comparisons = 0;
    public static long allocations = 0;
    private static int currentDepth = 0;
    public static int maxDepth = 0;

    public static void reset(){
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
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

    public static String summary() {
        return String.format("comparisons=%d, allocations=%d, maxDepth=%d",
                comparisons, allocations, maxDepth);
    }

}
