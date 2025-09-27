package com.myproject.util;

import com.myproject.metrics.Metrics;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public final class ArrayUtils {

    private ArrayUtils() {}

    public static void swap(int[] a, int i, int j) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        Metrics.addSwap();
    }

    public static void shuffle(int[] a) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            swap(a, i, j);
        }
    }

    public static int partitionLomuto(int[] a, int low, int high) {
        if (a == null) throw new IllegalArgumentException("Array is null");
        if (low < 0 || high >= a.length || low > high) throw new IllegalArgumentException("Invalid range");
        int pivotIndex = low + ThreadLocalRandom.current().nextInt(high - low + 1);
        swap(a, pivotIndex, high);
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            Metrics.addComparison();
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    public static int partitionHoare(int[] a, int low, int high) {
        if (a == null) throw new IllegalArgumentException("Array is null");
        if (low < 0 || high >= a.length || low > high) throw new IllegalArgumentException("Invalid range");
        int pivotIndex = low + ThreadLocalRandom.current().nextInt(high - low + 1);
        int pivot = a[pivotIndex];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                i++;
                Metrics.addComparison();
            } while (a[i] < pivot);
            do {
                j--;
                Metrics.addComparison();
            } while (a[j] > pivot);
            if (i >= j) return j;
            swap(a, i, j);
        }
    }

    public static void guardRange(int[] a, int low, int high) {
        if (a == null) throw new IllegalArgumentException("Array is null");
        if (low < 0 || high >= a.length || low > high) {
            throw new IllegalArgumentException("Invalid range: [" + low + "," + high + "] for length " + a.length);
        }
    }

    public static boolean sameMultiset(int[] a, int[] b) {
        if (a == null || b == null) return a == b;
        if (a.length != b.length) return false;
        int[] ac = Arrays.copyOf(a, a.length);
        int[] bc = Arrays.copyOf(b, b.length);
        Arrays.sort(ac);
        Arrays.sort(bc);
        return Arrays.equals(ac, bc);
    }
}
