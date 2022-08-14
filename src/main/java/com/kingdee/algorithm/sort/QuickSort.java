package com.kingdee.algorithm.sort;

/**
 * @author : yuLong_wang
 * @title 快速排序
 * @Date: 2021-08-29 17:52
 */
public class QuickSort {

    public static void main(String[] args) {
        Thread t = new Thread();
        /*t.getState()*/
    }

    /**
     * 快速排序
     */
    public int[] quickSortArray(int[] arr, int startIndex, int endIndex) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int mid = startIndex + (endIndex - startIndex) / 2;
        quickSortPartition(arr, startIndex, mid);
        quickSortPartition(arr, mid + 1, endIndex);
        return null;
    }

    /**
     * 快速排序具体逻辑
     *
     * @param arr   原始数组
     * @param left  左边距
     * @param right 右边距
     */
    private void quickSortPartition(int[] arr, int left, int right) {

        while (left < right) {

        }
    }
}
