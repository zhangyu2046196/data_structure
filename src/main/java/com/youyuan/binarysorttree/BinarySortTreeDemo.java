package com.youyuan.binarysorttree;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 二叉排序树
 * <p>
 * 二叉排序树特点：小于当前节点的节点放在当前节点的左子树，大于当前节点的节点放在当前节点的右子树
 * <p>
 * 中序遍历二叉排序树  排序的结果是有序的
 * @date 2019/8/14 17:22
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};

        BinarySortTree bstree = new BinarySortTree();

        //添加二叉排序树
        for (int r : arr) {
            bstree.add(new Node(r));
        }

        //中序递归遍历二叉排序树
        bstree.infixOrder();

        System.out.println("开始删除节点操作...");

        //删除二叉树节点
        bstree.delNode(9);
        bstree.delNode(12);
        bstree.delNode(7);
        bstree.delNode(3);
        bstree.delNode(12);
        bstree.delNode(5);
        bstree.delNode(2);
        bstree.delNode(10);
        //bstree.delNode(1);


        bstree.infixOrder();

    }

}

/**
 * 二叉排序树
 */
class BinarySortTree {
    //二叉排序树根节点
    private Node root;

    /**
     * 二叉排序树添加节点
     *
     * @param node 要添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }

    }

    /**
     * 删除节点接口
     *
     * @param value 要删除节点的值
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        //查找要删除节点
        Node targetNode = root.search(value);
        if (targetNode == null) {
            return;
        }
        //查找要删除节点的父节点
        Node parent = root.searchParent(value);
        //删除的节点为叶子节点
        if (targetNode.getLeftNode() == null && targetNode.getRightNode() == null) {
            if (parent == null) {  //删除的节点是根节点，并且二叉树中只有一个根节点
                root = null;
            } else {
                if (parent.getLeftNode() == targetNode) {
                    parent.setLeftNode(null);
                } else {
                    parent.setRightNode(null);
                }
            }
        } else if (targetNode.getLeftNode() != null && targetNode.getRightNode() != null) {//删除节点是有两个子节点的节点
            int min = delRightMinTree(targetNode.getRightNode());  //删除右子树最小节点并把最小节点值返回
            targetNode.setValue(min);
        } else {//删除的节点是有一个子节点的节点
            if (parent == null) {
                if (targetNode.getLeftNode() != null) {
                    root = targetNode.getLeftNode();
                } else {
                    root = targetNode.getRightNode();
                }
            } else {
                if (parent.getLeftNode() == targetNode) {//要删除的节点是父节点的左子节点
                    if (targetNode.getLeftNode() != null) {  //要删除节点有左子树节点
                        parent.setLeftNode(targetNode.getLeftNode());
                    } else {  //要删除节点有右子树节点
                        parent.setLeftNode(targetNode.getRightNode());
                    }
                } else {//要删除的节点是父节点的右子树节点
                    if (targetNode.getLeftNode() != null) {  //要删除的节点有左子树节点
                        parent.setRightNode(targetNode.getLeftNode());
                    } else {  //要删除的节点有右子树节点
                        parent.setRightNode(targetNode.getRightNode());
                    }
                }
            }
        }
    }

    /**
     * 删除当前节点的右子树最小的节点 并且把最小节点的值返回
     *
     * @param node 当前节点
     * @return 返回当前节点右子树最小节点的值
     */
    public int delRightMinTree(Node node) {
        Node currnetNode = node;//临时变量
        while (currnetNode.getLeftNode() != null) {
            currnetNode = currnetNode.getLeftNode();  //找到最小节点
        }
        //此时删除的是一个叶子节点 因current值是最小节点
        delNode(currnetNode.getValue());
        return currnetNode.getValue();
    }

    /**
     * 中序遍历二叉排序树
     */
    public void infixOrder() {
        if (root == null) {
            return;
        }
        root.infixOrder();
    }
}

/**
 * 节点对象类
 */
class Node implements Serializable {

    private static final long serialVersionUID = 6570117123122252014L;

    //值
    private int value;
    //左子节点
    private Node leftNode;
    //右子节点
    private Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    /**
     * 添加节点
     *
     * @param node 要添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.getValue() < this.getValue()) {
            if (this.getLeftNode() == null) {
                this.setLeftNode(node);
            } else {
                this.getLeftNode().add(node);
            }
        } else {
            if (this.getRightNode() == null) {
                this.setRightNode(node);
            } else {
                this.getRightNode().add(node);
            }
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value 要查找删除节点的值
     * @return 返回要删除的节点
     */
    public Node search(int value) {
        if (this.getValue() == value) {
            return this;
        }
        if (value < this.getValue()) {
            //向左子树递归查找
            if (this.getLeftNode() != null) {
                return this.getLeftNode().search(value);
            }
        } else {
            //向右子树递归查找
            if (this.getRightNode() != null) {
                return this.getRightNode().search(value);
            }
        }
        return null;
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要删除节点的值
     * @return 返回要删除节点的父节点
     */
    public Node searchParent(int value) {
        if ((this.getLeftNode() != null && this.getLeftNode().getValue() == value) || (this.getRightNode() != null && this.getRightNode().getValue() == value)) {
            return this;
        }
        if (value < this.getValue()) {
            //向左子树递归查找
            if (this.getLeftNode() != null) {
                return this.getLeftNode().searchParent(value);
            }
        } else {
            //向右子树递归查找
            if (this.getRightNode() != null) {
                return this.getRightNode().searchParent(value);
            }
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        //左子树递归遍历
        if (this.getLeftNode() != null) {
            this.getLeftNode().infixOrder();
        }
        System.out.println(this);
        //右子树递归遍历
        if (this.getRightNode() != null) {
            this.getRightNode().infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
