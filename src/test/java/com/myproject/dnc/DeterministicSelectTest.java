package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 9, 4, 1};
        assertEquals(1, DeterministicSelect.select(Arrays.copyOf(arr, arr.length), 0));
        assertEquals(4, DeterministicSelect.select(Arrays.copyOf(arr, arr.length), 2));
        assertEquals(9, DeterministicSelect.select(Arrays.copyOf(arr, arr.length), 4));
    }

    @Test
    void testRandomArray() {
        int n = 1000;
        int[] arr = ThreadLocalRandom.current().ints(n, 0, 10000).toArray();
        int k = n / 2;
        int result = DeterministicSelect.select(Arrays.copyOf(arr, arr.length), k);
        Arrays.sort(arr);
        assertEquals(arr[k], result);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(3, DeterministicSelect.select(Arrays.copyOf(arr, arr.length), 2));
    }

    @Test
    void testMetrics() {
        int[] arr = {5, 3, 8, 6, 2};
        DeterministicSelect.select(Arrays.copyOf(arr, arr.length), 2);
        System.out.println("Metrics: " + Metrics.summary());
    }
}
