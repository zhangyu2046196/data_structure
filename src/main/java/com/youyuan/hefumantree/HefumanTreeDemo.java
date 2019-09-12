package com.youyuan.hefumantree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangyu
 * @version 1.0
 * @description 赫夫曼树
 * <p>
 * 概念：
 * 1.路径和路径长度：在一棵树中，从一个结点往下可以达到的孩子或孙子结点之间的通路，称为路径。通路中分支的数目称为路径长度。若规定根结点的层数为1，则从根结点到第L层结点的路径长度为L-1
 * 2.结点的权及带权路径长度：若将树中结点赋给一个有着某种含义的数值，则这个数值称为该结点的权。结点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积
 * 3.树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，记为WPL(weighted path length) ,权值越大的结点离根结点越近的二叉树才是最优二叉树。
 * 4.WPL最小的就是赫夫曼树
 * <p>
 * 构建赫夫曼树步骤:
 * 1、从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 2、取出根节点权值最小的两颗二叉树
 * 3、组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4、再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 * @date 2019/8/9 20:49
 */
public class HefumanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};  //原始数组

        Node root = buildHefuman(arr);

        //打印
        preOrder(root);

    }

    /**
     * 前序遍历赫夫曼树
     *
     * @param node 根节点
     */
    public static void preOrder(Node node) {
        if (node != null) {
            System.out.println(node);
            preOrder(node.getLeftNode());
            preOrder(node.getRightNode());
        }
    }

    /**
     * 构建赫夫曼树方法
     *
     * @param arr 原始数组信息
     * @return 返回构建好的赫夫曼树的根节点
     */
    public static Node buildHefuman(int[] arr) {
        //1.创建ArrayList存储Node
        List<Node> list = new ArrayList<Node>();
        //2.遍历原始数组，把数组元素转成Node存储到List中
        for (int val : arr) {
            list.add(new Node(val));
        }
        //3.因为赫夫曼树的构建是将数组排序从小到大，然后取前面两个元素作为左右节点，生成父节点，父节点
        //的值是左右子节点权值的和，然后把前两个元素删除，把新生成的元素加入在排序从小到大，循环执行
        //直到每个元素都循环过结束，结束后List里面就只剩下一个Node,这个Node就是构建好的赫夫曼树
        Node leftNode = null;
        Node rightNode = null;
        Node parent = null;
        while (list.size() > 1) {
            //4.把List元素从小到大排序
            Collections.sort(list);
            //5.取出List中第一个和第二个元素，两个最小的元素构建父节点
            leftNode = list.get(0);  //左节点
            rightNode = list.get(1);  //右节点
            parent = new Node(leftNode.getVal() + rightNode.getVal());  //构建父节点
            parent.setLeftNode(leftNode);
            parent.setRightNode(rightNode);
            //6.删除左节点和右节点
            list.remove(leftNode);
            list.remove(rightNode);
            //7.把新生成的父节点放入List
            list.add(parent);
        }
        return list.get(0);
    }

}

/**
 * 树实体对象
 * 因为需要用Collections工具类对Node节点的val属性进行比较，所以需要实现Comparable接口
 */
class Node implements Serializable, Comparable<Node> {

    private static final long serialVersionUID = 8117139453900574171L;

    //值
    private int val;
    //左节点
    private Node leftNode;
    //右节点
    private Node rightNode;

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    /**
     * 升序比较  否则下面比较的值反过来
     *
     * @param o 要比较的对象
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.val - o.getVal();
    }
}
