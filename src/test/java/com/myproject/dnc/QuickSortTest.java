package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testRandomArray() {
        int[] arr = new Random().ints(1000, -10000, 10000).toArray();
        int[] copy = Arrays.copyOf(arr, arr.length);
        QuickSort.sort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
        System.out.println("Random array: " + Metrics.summary());
    }

    @Test
    public void testReverseAdversarial() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = arr.length - i;
        int[] copy = Arrays.copyOf(arr, arr.length);
        QuickSort.sort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
        System.out.println("Reverse adversarial: " + Metrics.summary());
    }

    @Test
    public void testSortedAdversarial() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) arr[i] = i;
        int[] copy = Arrays.copyOf(arr, arr.length);
        QuickSort.sort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
        System.out.println("Sorted adversarial: " + Metrics.summary());
    }

    @Test
    public void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] copy = Arrays.copyOf(arr, arr.length);
        QuickSort.sort(arr);
        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
        System.out.println("Small array: " + Metrics.summary());
    }

    @Test
    public void testRecursionDepthBounded() {
        int n = 1000;
        int[] arr = new Random().ints(n, -10000, 10000).toArray();
        QuickSort.sort(arr);
        int logBound = 2 * (int) (Math.log(n) / Math.log(2)) + 10;

        assertTrue(Metrics.maxDepth <= logBound,
                "Recursion depth too high: " + Metrics.maxDepth + " > " + logBound);
    }
}
