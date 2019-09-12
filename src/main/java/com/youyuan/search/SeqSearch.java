package com.youyuan.search;

/**
 * @author zhangyu
 * @version 1.0
 * @description  查找算法之顺序查找
 *
 * 思路：
 * 循环遍历匹配
 *
 * @date 2019/8/5 16:01
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr={1,90,31,89,-22,8,16};

        int index=search(arr,31);
        System.out.println("index:"+index);

    }

    /**
     * 顺序查找
     * @param arr  数组
     * @param value 要查找的数字
     * @return 返回查找到的数组下标
     */
    public static int search(int[] arr,int value){
        for (int i=0;i<arr.length;i++){
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }

}
