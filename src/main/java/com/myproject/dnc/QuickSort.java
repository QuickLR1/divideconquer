package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    public static void sort(int[] arr) {
        Metrics.reset();
        Metrics.startTimer();
        quickSort(arr, 0, arr.length - 1);
        Metrics.stopTimer();
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            Metrics.enter();
            int p = randomizedPartition(arr, low, high);
            if (p - low < high - p) {
                quickSort(arr, low, p - 1);
                quickSort(arr, p + 1, high);
            } else {
                quickSort(arr, p + 1, high);
                quickSort(arr, low, p - 1);
            }
            Metrics.exit();
        }
    }

    private static int randomizedPartition(int[] arr, int low, int high) {
        int pivotIndex = low + random.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);
        return partition(arr, low, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            Metrics.addComparison();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            Metrics.addSwap();
        }
    }
}
