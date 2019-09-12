package com.youyuan.search;

/**
 * @author zhangyu
 * @version 1.0
 * @description 插值查找
 * <p>
 * 思路：
 * 1、插值查找算法类似于二分查找，不同的是插值查找每次从自适应mid处开始查找。
 * 2、将折半查找中的求mid 索引的公式 优化自适应 ，查找值的本身也参与计算
 * 3、求数组中间索引值公式：int mid = left + (right – left) * (findVal – arr[left]) / (arr[right] – arr[left])
 * @date 2019/8/5 22:51
 */
public class InsertValSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 16, 89, 1298};
        int findVal = 16;
        int index = search(arr, 0, arr.length - 1, findVal);
        System.out.println("index:"+index);
    }

    /**
     * 插值查找算法
     *
     * @param arr     原数组
     * @param left    左下标
     * @param right   右下标
     * @param findVal 要查找的值
     * @return 返回查找到的元素的下标  -1代表没有查找到
     */
    public static int search(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //  中间索引位置公式
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal == arr[mid]) {  //找到元素的下标
            return mid;
        } else if (findVal > midVal) {  //向右递归
            return search(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return search(arr, left, mid - 1, findVal);
        }
        return -1;
    }

}
