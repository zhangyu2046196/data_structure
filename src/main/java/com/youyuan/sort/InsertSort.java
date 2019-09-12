package com.youyuan.sort;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description  插入排序
 *
 * 思路：
 * 待排序的n个数的数组可以看成一个有序表和一个无序表，有序表中初始化时有一个元素，是原始数组的第一个元素，无序表中
 * 有n-1个元素，是去除数组中第一个元素的剩余元素，从无序表拿一个元素与有序表比较，如果比有序表里的元素小，则把有序表里
 * 大的元素位置后移
 *
 * @date 2019/8/1 15:34
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr={32,18,98,-6,100,19,27};

        System.out.println("排序前元素");
        System.out.println(Arrays.toString(arr));

        sort(arr);

        System.out.println("排序后元素");
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 排序
     * @param arr 原始数组信息
     */
    public static void sort(int[] arr){
        if (arr.length>1){
            for (int i=1;i<arr.length;i++){  //从第二个元素开始遍历
                //比较当前元素与前面元素的大小，如果小于前面元素，则前面元素后移，当前元素插入指定位置，这里倒序一个一个元素的比较
                int value=arr[i];  //临时当前比较的元素，因为可能前面元素后移覆盖当前元素索引位置
                int beforeIndex=i-1;  //初始化要比较的元素的前一个元素的索引位置
                while (beforeIndex>=0 && value<arr[beforeIndex]){
                    arr[beforeIndex+1]=arr[beforeIndex];  //前面元素后移
                    beforeIndex--;  //指针前移  比较
                }

                //上面循环结束beforeIndex就是就是要插入的元素前一个位置
                //找到插入位置赋值
                arr[beforeIndex+1]=value;

                System.out.println("第"+i+"次排序结果");
                System.out.println(Arrays.toString(arr));
            }
        }
    }

}
