package com.myproject.dnc;

import com.myproject.metrics.Metrics;

public class QuickSort {

    public static void sort(int[] arr) {
        Metrics.reset();
        Metrics.startTimer();
        quickSort(arr, 0, arr.length - 1);
        Metrics.stopTimer();
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            Metrics.enter();
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
            Metrics.exit();
        }
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
