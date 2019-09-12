package com.youyuan.sort;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 基数排序
 *
 * 思路：
 * 把所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列
 * 排序的次数是待排序数组中最大数字位数  如：数组中最大数字为298  最大位数是百位，所以排序三次
 * 共有10个桶（ 每个桶是一个一维数组）,桶的排序从0开始，共0-9个桶
 * 将数组 {53, 3, 542, 748, 14, 214} 使用基数排序, 进行升序排序
 *
 * @date 2019/8/2 13:53
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

        System.out.println("排序前元素");

        System.out.println(Arrays.toString(arr));

        sort(arr);

        System.out.println("排序后元素");

        System.out.println(Arrays.toString(arr));


    }

    /**
     * 排序
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int max = arr[0];  //默认为第0位元素为最大的，然后去和数组中的元素对比，存储待排序数组中最大数，为了计算位数，用于决定循环次数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //通过字符串巧妙的方式计算最大数的位数
        int maxLength = (max + "").length();

        //定义一个二维数组，用来代表10个桶(每一行为一个桶 也就是一个一维数组)
        int[][] bucketArray = new int[10][arr.length];  //10代表10行  一行是一个桶  arr.length代表桶的长度也就是二维数组的列数

        //定义一个一维数组  用来存储每一个桶的存储的元素的个数 也就是默认是0 存储一个就加1
        int[] bucketElementCount = new int[10];

        //开始循环  n代表计算位数的基数，第一次循环是1 代表个位  第二次循环是10 代表十位
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //把待排序数组放到桶中
            for (int j = 0; j < arr.length; j++) {
                //计算余数 得到放到哪个桶 也就是二维数组的哪一行
                int bucketRow = arr[j] /n % 10;

                //把数据放入桶中
                bucketArray[bucketRow][bucketElementCount[bucketRow]] = arr[j];
                //把记录桶的记录数加1
                bucketElementCount[bucketRow]++;
            }

            //循环二维数组(桶)内容放入原始数组中
            // 标记从第一个桶开始循环
            //定义变量index 用于往原始数组插入数据的下标
            int index=0;
            for (int k=0;k<bucketArray.length;k++){
                //循环每个桶的元素下标
                for (int p=0;p<bucketElementCount[k];p++){
                    arr[index++]=bucketArray[k][p];
                }

                bucketElementCount[k]=0;  //把记录每个桶的数量重置，为了下次循环使用
            }


            System.out.println("第"+(i+1)+"次排序后的结果");

            System.out.println(Arrays.toString(arr));
        }

    }

}
