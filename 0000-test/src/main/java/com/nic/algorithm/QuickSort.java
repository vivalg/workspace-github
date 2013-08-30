package com.nic.algorithm;

public class QuickSort {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + i / 2);
        }
    }

    public void quickSort(int[] arr, int beginPos, int len) {
        if (len <= 1) {
            return;
        }
        int pivotPos = beginPos + len / 2;
        int pivotElem = arr[pivotPos];
    }


    public void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }
}
