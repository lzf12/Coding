package com.kingdee.algorithm.sort;

import java.util.Arrays;

/**
 * @Author 汪玉龙
 * @Date 2020/10/31 10:21
 */
public class BubbleSort {

    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        boolean flag;
        // 核心: n个数需要进行n-1躺比较, 从后面扫描, 第一层i为几表示要使得那个位置上的数在这一次比较中最小,最后检查边界
        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    flag = false;
                }
            }
            if (flag) {
                // 已经有序直接跳出循环
                break;
            }
        }
        return arr;
    }

    /**
     * 数组两个数进行交换
     *
     * @param arr 数组
     * @param j   变量下标
     * @param i   变量下标
     */
    private static void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        // 第一组测试用例,arr为null
        System.out.println("arr排序后的结果:" + Arrays.toString(bubbleSort(null)));
        // 第二组测试用例,arr = {1,4,8,0,3,9}
        int[] arr = {1, 4, 8, 0, 3, 9};
        System.out.println("arr排序后的结果:" + Arrays.toString(bubbleSort(arr)));
        // 第三组测试用例 arr = {1,4,-8,0,-3,9}
        int[] arr1 = {1, 4, -8, 0, -3, 9};
        System.out.println("arr排序后的结果:" + Arrays.toString(bubbleSort(arr1)));
    }

}
