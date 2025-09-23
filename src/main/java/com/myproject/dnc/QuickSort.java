package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1, 0);
    }

    private static void quicksort(int[] arr, int low, int high, int depth) {
        while (low < high) {
            Metrics.recordDepth(depth);
            int pivotIndex = partition(arr, low, high);
            int leftSize = pivotIndex - low;
            int rightSize = high - pivotIndex;

            // рекурсивно идём в меньшую часть
            if (leftSize < rightSize) {
                quicksort(arr, low, pivotIndex - 1, depth + 1);
                low = pivotIndex + 1; // tail recursion elimination
            } else {
                quicksort(arr, pivotIndex + 1, high, depth + 1);
                high = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = low + random.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            Metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        Metrics.incrementSwaps();
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
