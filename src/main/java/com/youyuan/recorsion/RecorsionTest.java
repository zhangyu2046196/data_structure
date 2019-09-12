package com.youyuan.recorsion;

/**
 * @author zhangyu
 * @version 1.0
 * @description  递归测试示例信息
 *
 * 简单的说: 递归就是方法自己调用自己,每次调用时传入不同的变量.递归有助于编程者解决复杂的问题,同时可以让代码变得简洁
 *
 * @date 2019/7/30 17:12
 */
public class RecorsionTest {

    public static void main(String[] args) {
        print(5);
        int res=factorial(6);
        System.out.println("res："+res);
    }

    /**
     * 递归 解决打印问题
     * @param n  参数
     */
    public static void print(int n){
        if (n>2){
            print(n-1);
        }
        System.out.println("n="+n);
    }

    /**
     * 递归 解决阶乘问题
     * @param n 参数
     * @return  返回结果
     */
    public static int factorial(int n){
        if (n==1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }

}
