package com.youyuan.tree;

/**
 * @author zhangyu
 * @version 1.0
 * @description 顺序存储二叉树
 * <p>
 * 二叉树和数组存储的数据能相互转换
 * <p>
 * 特点：
 * 1. n代表第几个元素 从0开始 因为数组的下标是从0开始的
 * 2. 二叉树中的第n个元素的左子树节点对应数组下标为2*n+1
 * 3. 二叉树中的第n个元素的右子树节点对应数组下标为2*n+2
 * 4. 二叉树中的第n个元素的父节点为(n-1)/2
 * <p>
 * 以下来测试通过二叉树的前序、中序、后序来遍历数组
 * @date 2019/8/8 14:48
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        //二叉树前序遍历数组结果 1,2,4,5,3,6,7
        //tree.preOrder();

        //二叉树中序遍历数组结果  4,2,5,1,6,3,7
        //tree.inOrder();

        //二叉树后序遍历数组结果  4,5,2,6,7,3,1
        tree.postOrder();
    }

}

class ArrBinaryTree {
    private int[] arr;//原始数组信息

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);//从数组的第一个元素开始遍历
    }

    public void inOrder() {
        this.inOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }

    /**
     * 以二叉树前序遍历方式遍历数组
     *
     * @param index 第几个元素  从0开始 可以看成数组下标
     */
    private void preOrder(int index) {
        System.out.println("二叉树前序方式遍历数组元素");
        //根节点
        System.out.printf("第%d个元素=%d\n", index, arr[index]);
        //左子树递归
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //右子树递归
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 以二叉树的中序遍历方式遍历数组
     *
     * @param index 第几个元素 从0开始 因为数组下标从0开始
     */
    private void inOrder(int index) {
        //左子树递归遍历
        if (2 * index + 1 < arr.length) {
            inOrder(2 * index + 1);
        }
        System.out.println("二叉树中序方式遍历数组元素");
        //根节点
        System.out.printf("第%d个元素=%d\n", index, arr[index]);
        //右子树递归遍历
        if (2 * index + 2 < arr.length) {
            inOrder(2 * index + 2);
        }
    }

    /**
     * 二叉树后序遍历数组
     *
     * @param index 第几个元素 从0开始 因为数组下标从0开始
     */
    private void postOrder(int index) {
        //左子树递归遍历
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //右子树递归遍历
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.println("二叉树后序遍历数组元素");
        //根节点
        System.out.printf("第%d个元素=%d\n", index, arr[index]);
    }
}
