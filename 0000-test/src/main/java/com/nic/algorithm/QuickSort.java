package com.nic.algorithm;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = generateRandomArray(10);
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            long randNum = Math.round((Math.random() * 100));
            arr[i] = (int) randNum;
        }
        return arr;
    }

    public static void quickSort(int[] arr, int beginPos, int endPos) {
        int pivotPos = beginPos + (endPos - beginPos) / 2;
        int pivotElem = arr[pivotPos];

        int i = beginPos;
        int j = endPos;

        while (i <= j) {
            while (arr[i] < pivotElem) {
                i++;
            }

            while (arr[j] > pivotElem) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        System.out.println("[" + pivotPos + ",    " + pivotElem + "]  i=" + i + ",    j=" + j);
        System.out.println(Arrays.toString(arr));

        if (j > beginPos) {
            quickSort(arr, beginPos, j);
        }

        if (i < endPos) {
            quickSort(arr, i, endPos);
        }

    }

    public static void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }
}
