package com.youyuan.search;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 斐波那契 查找算法
 * <p>
 * 思路：
 * 斐波那契查找算法依赖斐波那契数列   斐波那契数列规则是arr[0]=1  arr[1]=1  从第三位数开始值是前两个值的和  arr[k]=arr[k-1]+arr[k-2]
 * 斐波那契数列示例 数组信息  {1, 1, 2, 3, 5, 8, 13, 21, 34, 55 }
 * <p>
 * 斐波那契查找原理与前两种相似，仅仅改变了中间结点（mid）的位置，mid不再是中间或插值得到，而是位于黄金分割点附近，即mid=left+F(k-1)-1（F代表斐波那契数列）  left代表数组左下标  right代表数组右下标
 * 对F(k-1)-1的理解：
 * 由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1 。该式说明：只要顺序表的长度为F[k]-1，则可以将该表分成长度为F[k-1]-1和F[k-2]-1的两段，即如上图所示。从而中间位置为mid=low+F(k-1)-1
 * 类似的，每一子段也可以用相同的方式分割
 * 但顺序表长度n不一定刚好等于F[k]-1，所以需要将原来的顺序表长度n增加至F[k]-1。这里的k值只要能使得F[k]-1恰好大于或等于n即可，由以下代码得到,顺序表长度增加后，新增的位置（从n+1到F[k]-1位置），都赋为n位置的值即可。
 * @date 2019/8/6 15:34
 */
public class FibonaciSearch {

    private static int maxSize = 20;  //斐波那契数列长度

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int findVal = 89;

        int index = search(arr, fib(maxSize), findVal);

        System.out.println("index:" + index);

    }

    /**
     * 初始化斐波那契数列
     *
     * @param maxSize 斐波那契数列长度
     * @return 返回斐波那契数列
     */
    public static int[] fib(int maxSize) {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 查找数字
     *
     * @param arr     原始数组
     * @param fib     斐波那契数列
     * @param findVal 查找的元素
     * @return 返回查找元素的下标
     */
    public static int search(int[] arr, int[] fib, int findVal) {
        int left = 0;  //左下标
        int right = arr.length - 1;  //右下标
        int k = 0;  //斐波那契数组下标
        int mid = 0;  //存放计算出的黄金分割点
        //计算斐波那契数组下标
        while (right > fib[k] - 1) {
            k++;
        }

        //因为 fib[k] 值 可能大于 arr 的 长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, fib[k]);
        //实际上需求使用arr数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234}
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }

        while (left <= right) {
            mid = left + fib[k - 1] - 1;  //根据斐波那契数列计算的黄金分割点
            if (findVal < temp[mid]) {  //向左查找
                right = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. fib[k] = fib[k-1] + fib[k-2]
                //因为 前面有 fib[k-1]个元素,所以可以继续拆分 fib[k-1] = fib[k-2] + fib[k-3]
                //即 在 fib[k-1] 的前面继续查找 k--
                //即下次循环 mid = fib[k-1-1]-1
                k--;
            } else if (findVal > temp[mid]) {  //向右查找
                left = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. fib[k] = fib[k-1] + fib[k-2]
                //3. 因为后面我们有fib[k-2] 所以可以继续拆分 fib[k-1] = fib[k-3] + fib[k-4]
                //4. 即在fib[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = fib[k - 1 - 2] - 1
                k -= 2;
            } else {
                //需要确定，返回的是哪个下标
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }

        return -1;
    }

}
