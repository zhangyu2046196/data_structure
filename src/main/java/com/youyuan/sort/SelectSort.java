package com.youyuan.sort;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 选择排序
 * <p>
 * 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：
 * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，
 * 与arr[1]交换，第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，第i次从arr[i-1]~arr[n-1]中选取
 * 最小值，与arr[i-1]交换，…, 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共
 * 通过n-1次，得到一个按排序码从小到大排列的有序序列
 * <p>
 * <p>
 * 步骤:
 * 循环次数为数组长度减1
 * 第一次循环的时候先从arr[0]到arr[n-1]找到最小的元素，然后用最小的元素和arr[0]互换位置
 * 第二次循环的时候从arr[1]到arr[n-1]找到最小的元素，然后用最小的元素和arr[1]互换位置
 * @date 2019/8/1 11:44
 */
public class SelectSort {

    static int count = 0;  //外层循环次数

    public static void main(String[] args) {
        int[] arr = {101, 24, 119, 1, 20, -36, -36};

        System.out.println("排序前的结果...");

        System.out.println(Arrays.toString(arr));

        sort(arr);

        System.out.println("排序后的结果...");

        System.out.println(Arrays.toString(arr));

        System.out.println("外层循环次数:" + count);
    }

    /**
     * 选择排序
     *
     * @param arr 原始数组
     */
    public static void sort(int[] arr) {

        //开始循环 i代表最小元素下标，里面循环一次得到最小元素后进行第二次循环i+1
        int minIndex = 0;  //最小索引
        int temp = 0;  //最小索引值
        for (int i = 0; i < arr.length; i++) {
            count++;
            minIndex = i;
            temp = arr[i];
            //循环得到最小元素，循环一次把最小元素放到i的位置，第二次从i+1的位置开始循环
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < temp) {
                    minIndex = j; //找到最小元素的索引值
                    temp = arr[minIndex];
                }
            }

            int resTemp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = resTemp;
            System.out.println("最小索引值:" + minIndex);
            System.out.println(Arrays.toString(arr));
        }
    }

}
