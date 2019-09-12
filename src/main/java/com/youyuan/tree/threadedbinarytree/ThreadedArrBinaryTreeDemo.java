package com.youyuan.tree.threadedbinarytree;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线索二叉树
 * <p>
 * 每个二叉树的节点都有会指向一个left节点和一个right节点，一个二叉树链表会有n+1个空的指针，n代表节点个数，空指针代表节点left为空或right为空
 * 如果left指针为空则指向前驱节点，如果right指针为空则指向后继节点
 * n个结点的二叉链表中含有n+1  【公式 2n-(n-1)=n+1】 个空指针域。利用二叉链表中的空指针域，存放指向该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
 * 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 * 一个结点的前一个结点，称为前驱结点
 * 一个结点的后一个结点，称为后继结点
 * @date 2019/8/8 20:27
 */
public class ThreadedArrBinaryTreeDemo {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedArrBinaryTree threadedBinaryTree = new ThreadedArrBinaryTree();
        threadedBinaryTree.infixTranstion(root);

        //测试: 以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10号结点的后继结点是=" + rightNode); //1

        //中序遍历线索二叉树
        threadedBinaryTree.threadedList(root);

    }

}

/**
 * 线索二叉树
 */
class ThreadedArrBinaryTree {

    private HeroNode preNode;  //前继节点

    /**
     * 中序遍历线索二叉树
     *
     * @param node 开始遍历节点
     */
    public void threadedList(HeroNode node) {
        while (node != null) {
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();

        }
    }

    /**
     * 中序组装线索二叉树
     *
     * @param node
     */
    public void infixTranstion(HeroNode node) {
        if (node == null) {
            return;
        }
        //左子树遍历组装线索二叉树
        if (node.getLeft() != null) {
            infixTranstion(node.getLeft());
        }

        //组装线索二叉树逻辑
        if (node.getLeft() == null) {
            node.setLeftType(1);  //存储前驱节点
            node.setLeft(preNode);
        }

        if (preNode != null && preNode.getRight() == null) {
            preNode.setRightType(1);  //存储后继节点
            preNode.setRight(node);
        }

        //前驱节点赋值
        preNode = node;


        //右子树遍历组装线索二叉树
        if (node.getRight() != null) {
            infixTranstion(node.getRight());
        }
    }
}

/**
 * 英雄实体对象
 */
class HeroNode implements Serializable {

    private static final long serialVersionUID = 7893512829829197301L;

    //序号
    private int no;
    //名称
    private String name;
    //左子节点
    private HeroNode left;
    //右子节点
    private HeroNode right;

    //左子节点类型  0存储的节点  1存储前驱节点
    private int leftType;

    //右子节点类型  0存储的节点  1存储的后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    /**
     * 前序遍历
     * 顺序：根节点=>左子树节点=>右子树节点
     */
    public void prefixOrder() {
        System.out.println(this);
        if (this.left != null) {  //前序遍历左子树
            this.left.prefixOrder();
        }
        if (this.right != null) {  //前序遍历右子树
            this.right.prefixOrder();
        }
    }

    /**
     * 中序遍历
     * <p>
     * 顺序：左子树=>根节点=>右子树
     */
    public void infixOrder() {
        if (this.left != null) {  //中序遍历左子树
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {  //中序遍历右子树
            this.right.infixOrder();
        }

    }

    /**
     * 后序遍历
     * <p>
     * 顺序：左子树=>右子树=>根节点
     */
    public void postfixOrder() {

        if (this.left != null) {  //后序遍历左子树
            this.left.postfixOrder();
        }

        if (this.right != null) {  //后序遍历右子树
            this.right.postfixOrder();
        }

        System.out.println(this);

    }

    /**
     * 前序遍历查找节点
     * <p>
     * 前序遍历顺序
     * 根节点=>左子树节点=>右子树节点
     *
     * @param no 序号
     * @return 如果查找到返回节点，否则返回  null
     */
    public HeroNode prefixOrderSearch(int no) {
        System.out.println("前序遍历递归查找...");
        HeroNode node = null;
        if (this.getNo() == no) {//判断当前节点是否是要查找的节点
            return this;
        }
        if (this.getLeft() != null) {
            node = this.getLeft().prefixOrderSearch(no);  //左子树前序递归查找
        }
        //必须判断node是否为空，如果左子树前序遍历查找到了，就不进行后面右子树前序遍历查找
        if (node != null) {
            return node;
        }
        if (this.getRight() != null) {
            node = this.getRight().prefixOrderSearch(no); //右子树前序递归遍历查找
        }
        return node;
    }

    /**
     * 中序遍历查找节点
     * 顺序：左子树=>根节点=>右子树
     *
     * @param no 序号
     * @return 查找到返回节点  否则返回 null
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode node = null;
        if (this.getLeft() != null) {
            node = this.getLeft().infixOrderSearch(no);
        }
        //  必须判断，过左节点中序递归查找到就不进行后面递归查找
        if (node != null) {
            return node;
        }
        System.out.println("中序遍历递归查找...");
        //  判断当前节点
        if (this.getNo() == no) {
            return this;
        }
        if (this.getRight() != null) {
            node = this.getRight().infixOrderSearch(no);
        }
        return node;
    }

    /**
     * 后序遍历查找节点
     * 顺序
     * 左子树=>右子树=>根节点
     *
     * @param no 序号
     * @return 如果找到返回  否则返回null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode node = null;
        if (this.getLeft() != null) {  //左子树后序遍历递归查找
            node = this.getLeft().postOrderSearch(no);
        }
        //必须判断，查找到就不会进行后面遍历
        if (node != null) {
            return node;
        }
        if (this.getRight() != null) {  //右子树后序遍历递归查找
            node = this.getRight().postOrderSearch(no);
        }
        System.out.println("后序遍历递归查找...");
        //必须判断，查找到就不会进行后面判断
        if (this.getNo() == no) {
            return this;
        }
        return node;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

