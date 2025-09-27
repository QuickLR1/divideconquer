package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import com.myproject.util.ArrayUtils;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        if (arr == null || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        Metrics.reset();
        Metrics.startTimer();
        int result = select(arr, 0, arr.length - 1, k);
        Metrics.stopTimer();
        return result;
    }

    private static int select(int[] arr, int low, int high, int k) {
        while (true) {
            if (low == high) return arr[low];
            int pivotIndex = medianOfMedians(arr, low, high);
            int p = partition(arr, low, high, pivotIndex);
            if (k == p) return arr[k];
            else if (k < p) high = p - 1;
            else low = p + 1;
        }
    }

    private static int partition(int[] arr, int low, int high, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        ArrayUtils.swap(arr, pivotIndex, high);
        int store = low;
        for (int i = low; i < high; i++) {
            Metrics.addComparison();
            if (arr[i] < pivotValue) {
                ArrayUtils.swap(arr, store, i);
                store++;
            }
        }
        ArrayUtils.swap(arr, store, high);
        return store;
    }

    private static int medianOfMedians(int[] arr, int low, int high) {
        int n = high - low + 1;
        if (n < 5) {
            Arrays.sort(arr, low, high + 1);
            return low + n / 2;
        }
        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLow = low + i * 5;
            int subHigh = Math.min(subLow + 4, high);
            Arrays.sort(arr, subLow, subHigh + 1);
            int median = subLow + (subHigh - subLow) / 2;
            ArrayUtils.swap(arr, low + i, median);
        }
        return medianOfMedians(arr, low, low + numMedians - 1);
    }
}
