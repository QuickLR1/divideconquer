package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeSortTest {

    private static final int N = 5000;
    private static final Random rnd = new Random();

    @Test
    public void testRandomArray() {
        int[] a = rnd.ints(N, -10000, 10000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
        assertTrue(Metrics.maxDepth <= (int)(Math.log(N) / Math.log(2)) + 5,
                "Depth is too large for MergeSort");
    }

    @Test
    public void testSortedArray() {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = i;
        int[] expected = a.clone();

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testReversedArray() {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = N - i;
        int[] expected = a.clone();
        Arrays.sort(expected);

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testAllEqualElements() {
        int[] a = new int[N];
        Arrays.fill(a, 42);
        int[] expected = a.clone();

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }
}
