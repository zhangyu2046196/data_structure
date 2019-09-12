package com.youyuan.sort;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 冒泡排序测试
 * <p>
 * 思路：
 * 1、从左至右循环，比较两个相邻的元素的大小，如果前面元素大于后面元素就交换位置，使大的元素向后移动每循环一次确定一个大数并移动到后面
 * 2、大的循环次数是数组大小减1
 * 3、因为每循环一次大数都向后移动，所以大的循环次数在减少
 * @date 2019/7/31 22:09
 */
public class BubblSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        System.out.println("原始数据为" + Arrays.toString(arr));

        //调用排序接口
        sort(arr);
    }

    /**
     * 排序
     *
     * @param arr 原始数组信息
     */
    public static void sort(int[] arr) {
        int temp = 0;  //定义临时变量存储交换大的数        //大的循环次数是数组长度减1
        boolean flag = false;  //标记是否交换过,true交换过  false 否  如果没有交换代表已经排序号，下面的循环可以跳出不执行
        for (int i = 0; i < arr.length - 1; i++) {
            //因为每经过一次大循环就会将当前前面大的数或次大的数向后移动到指定位置，所以循环次数经过外面大的循环一次就减1
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;  //如果交换过 重新设置为false 用于下次循环判断
            }

            System.out.println("第" + (i + 1) + "次排序循环,结果" + Arrays.toString(arr));
        }
    }

}
