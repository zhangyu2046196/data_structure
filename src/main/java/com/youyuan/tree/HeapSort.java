package com.youyuan.tree;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 二叉树应用场景 堆排序
 * <p>
 * 堆排序是通过堆的数据结构进行排序的一种排序算法，属于选择排序，是基于完全二叉树进行的排序。
 * 大顶堆：如果每个节点都大于或等于它的左右子节点，则这个二叉树叫大顶堆
 * 小顶堆：如果每个节点都小于或等于它的左右子节点，则这个二叉树叫小顶堆
 * <p>
 * 实现思路
 * 1.待排序的序列构成一个大顶堆
 * 2.此时大顶堆的堆顶元素就是最大值
 * 3.然后把堆顶的元素与数组的最后一个元素替换位置，这时最大的元素就在数组的最后位置
 * 4.然后把剩下的n-1个元素在构造成一个大顶堆，重复上面的步骤，然后待排序的数组元素每次都会减少1个，直到排成一个有序数组
 * @date 2019/8/9 14:06
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        System.out.println("排序前:" + Arrays.toString(arr));
        //堆排序
        sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));

    }

    /**
     * 排序
     *
     * @param arr 原始数组
     */
    public static void sort(int[] arr) {
        //循环调用方法将数组调整成一个大顶堆  因为二叉树是有多层级的
        //首先让i指向最后一个非叶子节点的下标
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //循环完就形成了一个完整的大顶堆二叉树

        //把大顶堆二叉树第0个元素(最大的元素)放到数组的后边，以此类推
        int temp;  //存储临时变量
        //j指向数组的最后一个下标，循环后是次最后下标
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //上面代码替换完成

            //下面继续循环  因为二叉树有多层
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个无序数组(二叉树) 调整成一个大顶堆
     *
     * @param arr    原始数组
     * @param i      数组非叶子节点的数组下标   顺序是从左到右  从下到上
     * @param length 要调整的数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];  //临时存储数组的非叶子节点值

        //目的是将非叶子节点和左右子节点比较大小，值大的与非叶子节点互换
        //从非叶子节点的左节点开始遍历  k代表非叶子节点左节点在数组下标
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {  //左节点小于右节点
                k++;
            }
            //上面逻辑后得到的k是左右节点中值大的节点的下标

            //比较子节点与当前节点值的大小，如果子节点的值大于当前节点的值就互换
            if (arr[k] > temp) {
                arr[i] = arr[k];  //当前节点值替换成子节点大的值
                i = k;  //当前节点的下标指向子节点值大的下标
            } else {
                break;
            }
        }
        //循环结束后已经把i为父节点的子树的最大值放到了数的顶节点(局部)
        //此时i下标指向的是子节点大的下标
        arr[i] = temp;
    }

}
