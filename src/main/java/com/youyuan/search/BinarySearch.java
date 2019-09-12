package com.youyuan.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * @version 1.0
 * @description 二分查找
 * <p>
 * 二分查找的原数组必须是有序数组
 * <p>
 * 思路：
 * <p>
 * 定义mid索引标记为数组的中间索引  mid=(left+right)/2
 * <p>
 * 如果arr[mid]==findVal  就返回mid，mid是查找元素下标
 * 如果findVal>arr[mid]   向右递归查找
 * 如果findVal<arr[mid]   向左递归查找
 * 如果left>right 标记为没有找到要查找的元素，返回-1
 * @date 2019/8/5 21:27
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 16, 89, 89, 89, 89, 89, 89, 1298};
        int findVal = 89;
//        int index = search(arr, 0, arr.length - 1, findVal);
//        System.out.println("index:" + index);

        List<Integer> list = searchPlus(arr, 0, arr.length - 1, findVal);
        System.out.println("list:" + list);
    }

    /**
     * 二分查找数组元素下标
     *
     * @param arr     原数组
     * @param left    左下标
     * @param right   右下标
     * @param findVal 查找元素
     * @return 返回查找元素数组下标  -1 标记没有找到
     */
    public static int search(int[] arr, int left, int right, int findVal) {
        //已经遍历完元素 没有找到要查找的元素
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;  //中间索引下标
        int midVal = arr[mid];  //中间索引下标内容
        if (midVal == findVal) {  //找到元素下标
            return mid;
        } else if (findVal > midVal) {  //向右递归查找
            return search(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归查找
            return search(arr, left, mid - 1, findVal);
        }
        return -1;
    }

    /**
     * 课后作业  将数组中查找的相同的元素的所有下标返回
     * <p>
     * 思路：
     * 找到元素后，创建一个List集合，然后向左递归找到相同元素的下标放到集合中
     * 然后向右递归找到相同元素的下标放到集合中
     *
     * @param arr     原数组
     * @param left    左下标
     * @param right   右下标
     * @param findVal 查找的元素
     * @return 返回一个List存储所有相同元素的下标集合
     */
    public static List<Integer> searchPlus(int[] arr, int left, int right, int findVal) {
        //没有找到元素
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;  //数组中查找元素的中间索引下标
        int midVal = arr[mid];  //中间所以下标内容
        if (midVal == findVal) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(mid);

            int tempIndex = mid - 1;  //找到的元素下标，然后向左向右循环
            while (true) {  //向左循环
                if (tempIndex < 0 || findVal != arr[tempIndex]) {
                    break;
                }
                list.add(tempIndex);
                tempIndex -= 1;
            }

            tempIndex = mid + 1;
            while (true) {  //向右循环
                if (tempIndex > arr.length - 1 || arr[tempIndex] != findVal) {
                    break;
                }
                list.add(tempIndex);
                tempIndex += 1;
            }
            return list;
        } else if (findVal > midVal) {  //向右递归
            return searchPlus(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return searchPlus(arr, left, mid - 1, findVal);
        }
        return new ArrayList<Integer>();
    }

}
