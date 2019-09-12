package com.youyuan.recorsion;

/**
 * @author zhangyu
 * @version 1.0
 * @description  递归应用场景 八皇后问题(回溯算法)
 *
 * 思路：
 * 1、第一个皇后先放第一行第一列
 * 2、第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3、继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4、当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5、然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 *
 * 说明：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题. arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3} //对应arr 下标 表示第几行，即第几个皇后，arr[i] = val , 表示第i+1个皇后在i+1行的val+1列上   (因为下标是从0开始)
 *
 * @date 2019/7/31 16:08
 */
public class Queue8 {

    //定义数组大小 用于存储皇后的列的位置及数量
    private int maxSize=8;
    //定义数组存储八皇后的方案
    private int[] array=new int[maxSize];

    //统计有多少种方案
    private static int count;

    //统计回溯判断冲突计算数量
    private static int judgeCount;

    public static void main(String[] args) {
        Queue8 queue=new Queue8();
        queue.check(0);  //从第1行1列的位置开始放皇后

        System.out.printf("共有%d种解决方案,共判断冲突数量%d",count,judgeCount);
        System.out.println("");
    }

    /**
     * 递归计算八皇后问题的解决方案
     * @param n  第几个皇后
     */
    public void check(int n){
        if (n==maxSize){  //代表第八个皇后已经放好
            println();  //打印存储方案
            return;
        }else {
            for (int i=0;i<maxSize;i++){
                array[n]=i;  //先把当前的皇后放到这个列的问题，这决定每个皇后在所在行的每一列都会循环一遍
                if (judge(n)){  //判断当第n个皇后放到第n-1列上是否冲突  true 不冲突  false 冲突
                    check(n+1);
                }
                //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行得 后移的一个位置
            }
        }
    }

    /**
     * 判断皇后摆放 位置是否冲突
     * 任意两个皇后都不能处于同一行、同一列或同一斜线上
     * @param n  第几个皇后
     * @return  true 不冲突  false 冲突
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i=0;i<n;i++){
            // array[i]==array[n] 代表在同一列上 冲突
            // 因为n是递增的所以不用判断是否在同一行上
            // Math.abs(n-i) == Math.abs(array[n] - array[i])  标识判断第n个皇后和第i个皇后是否在同一斜线
            if (array[i]==array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印八皇后方案
     */
    private void println(){
        count++;
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println("");  //换行
    }

}
