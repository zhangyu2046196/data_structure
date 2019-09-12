package com.youyuan.tree;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 二叉树
 * <p>
 * 思路：
 * 1.前序遍历：根节点=>左子树=>右子树
 * 2.中序遍历：左子树=>根节点=>右子树
 * 3.后序遍历：左子树=>右子树=>根节点
 * @date 2019/8/7 17:07
 */
public class BinaryTreeDemo {


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        binaryTree.setRoot(node1);

        //前序遍历
        binaryTree.prefixOrder();  //1,2,3,5,4

        //中序遍历
        binaryTree.infixOrder();  //2,1,5,3,4

        //后序遍历
        binaryTree.postfixOrder();  //2,5,4,3,1

        //以下为查询no是5的节点，并且分别使用前序遍历递归查找、中序遍历递归查找、后序遍历递归查找，还要统计查找次数

        HeroNode node = null;
        //前序遍历递归查找
//        node=  binaryTree.prefixOrderSearch(5);
//        if (node!=null){
//            System.out.printf("前序遍历递归查找找到no=%d的节点,name=%s\n",node.getNo(),node.getName());
//        }else {
//            System.out.printf("前序遍历递归查找没有查找到no=%d的节点\n",5);
//        }
        //中序遍历递归查找
//        node = binaryTree.infixOrderSearch(5);
//        if (node != null) {
//            System.out.printf("中序遍历递归查找找到no=%d的节点,name=%s\n", node.getNo(), node.getName());
//        } else {
//            System.out.printf("中序遍历递归查找没有查找到no=%d的节点\n", 5);
//        }

//        //后序遍历递归查找
//        node = binaryTree.postfixOrderSearch(5);
//        if (node != null) {
//            System.out.printf("后序遍历递归查找找到no=%d的节点,name=%s\n", node.getNo(), node.getName());
//        } else {
//            System.out.printf("后序遍历递归查找没有查找到no=%d的节点\n", 5);
//        }

        int no=3;
        if (binaryTree.getRoot().getNo()==no){
            System.out.printf("要删除的节点为root节点%d,name=%s\n",no,binaryTree.getRoot().getName());
            binaryTree.setRoot(null);
        }else {
            HeroNode delNode=binaryTree.deleteByNo(binaryTree.getRoot(),no);
            System.out.printf("要删除的节点序号是%d,name=%s\n",no,delNode.getName());
        }

        //前序遍历
        binaryTree.prefixOrder();

    }

}

/**
 * 二叉树
 */
class BinaryTree {
    //根节点
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void prefixOrder() {
        System.out.println("前序遍历");
        root.prefixOrder();
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        System.out.println("中序遍历");
        root.infixOrder();
    }

    /**
     * 后序遍历
     */
    public void postfixOrder() {
        System.out.println("后序遍历");
        root.postfixOrder();
    }

    /**
     * 前序遍历递归查找
     *
     * @param no 序号
     * @return 找到返回节点 否则返回  null
     */
    public HeroNode prefixOrderSearch(int no) {
        return root.prefixOrderSearch(no);
    }

    /**
     * 中序遍历递归查找
     *
     * @param no 序号
     * @return 找到返回节点 否则返回  null
     */
    public HeroNode infixOrderSearch(int no) {
        return root.infixOrderSearch(no);
    }

    /**
     * 删除节点操作
     * 因为二叉树是单向关联关系，所以比较要删除的节点的时候是通过子节点去比较
     *
     * @param parentNode 删除节点的父节点
     * @param no         要删除节点的序号
     * @return 查找到就返回删除的节点  否则返回null
     */
    public HeroNode deleteByNo(HeroNode parentNode, int no) {
        HeroNode node = null;
        //  比较当前节点的左子树节点是否是否是要删除的节点
        if (parentNode.getLeft() != null && parentNode.getLeft().getNo() == no) {
            node = parentNode.getLeft();
            parentNode.setLeft(null);
            return node;
        }
        //  比较当前节点的右子树节点是否是要删除的节点
        if (parentNode.getRight() != null && parentNode.getRight().getNo() == no) {
            node = parentNode.getRight();
            parentNode.setRight(null);
            return node;
        }
        //向左子树递归查询删除
        if (parentNode.getLeft() != null) {
            node = deleteByNo(parentNode.getLeft(), no);
        }
        if (node != null) {
            return node;
        }
        //向右子树递归查询删除
        if (parentNode.getRight() != null) {
            node = deleteByNo(parentNode.getRight(), no);
        }
        return node;
    }

    /**
     * 后序遍历递归查找
     *
     * @param no 序号
     * @return 找到返回节点 否则返回  null
     */
    public HeroNode postfixOrderSearch(int no) {
        return root.postOrderSearch(no);
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
