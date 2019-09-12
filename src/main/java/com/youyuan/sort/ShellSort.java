package com.youyuan.sort;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 希尔排序
 * <p>
 * 思路:
 * <p>
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * @date 2019/8/1 19:40
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前");

        System.out.println(Arrays.toString(arr));

        sort(arr);

        System.out.println("排序后");

        System.out.println(Arrays.toString(arr));

    }

    /**
     * 排序
     *
     * @param arr 原始数组数据
     */
    public static void sort(int[] arr) {
        int temp = 0;  //存储临时数据 用于交换数据
        int count=0;  //标记分组循环次数
        //gap 步长 代表分成几组，也就是每组两个元素之间的间隔数
        //第一层循环是分成几组来循环
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //循环每一个组
            for (int i = gap; i < arr.length; i++) {
                //循环每组的元素  进行对比交互或移动  gap步长 代表前面元素和后面元素间隔元素个数
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {  //如果前边元素大于后面元素 则互换位置
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }

            System.out.println("第"+(++count)+"组排序后");

            System.out.println(Arrays.toString(arr));

        }
    }

}
