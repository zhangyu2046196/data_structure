package com.youyuan.avl;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 平衡二叉树
 * <p>
 * 1、左旋转思路
 * <p>
 * currentNode 当前节点
 * 定义一个新节点newNode ,newNode的值value=currentNode.getValue()  当前节点的值
 * newNode的左子树设置为当前节点的左子树   newNode.setLeftNode(currentNode.getLeftNode())
 * newNode的右子树设置为当前节点的右子树的左子树  newNode.setRightNode(currentNode.getRightNode().getLeftNode())
 * 当前节点的值设置为当前节点右子树节点的值   currentNode.setValue(currentNode.getRightNode().getValue())
 * 当前节点的左子树设置为新创建节点   currentNode.setLeftNode(newNode)
 * 当前节点的右子树设置为当前节点的右子树的右子树   currentNode.setRightNode(currentNode.getRightNode().getRightNode())
 * <p>
 * 2、右旋转思路
 * <p>
 * 当前节点currentNode
 * 定义一个新节点newNode，新节点的值为当前节点的值newNode.setValue(currentNode.getValue())
 * 新节点的右子树指向当前节点的右子树，  newNode.setRightNode(currentNode.getRightNode())
 * 新节点的左子树指向当前节点的左子树的右节点，newNode.setLeftNode(currentNode.getLeftNode().getRightNode())
 * 当前节点的值指向当前节点左子节点值， currentNode.setValue(currentNode.getLeftNode().getValue())
 * 当前节点的左子树指向当前节点左子树的左子树， currentNode.setLeftNode(currentNode.getLeftNode().getLeftNode())
 * 当前节点的右子树指向新节点， currentNode.setRightNode(newNode)
 * @date 2019/8/19 10:50
 */
public class AvlTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AvlTree avlTree = new AvlTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().getLeftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().getRightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
    }

}

/**
 * 平衡二叉树
 */
class AvlTree {
    //二叉排序树根节点
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

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
        int leftHeight = getLeftHeight();  //获取左子树节点高度
        int rightHeight = getRightHeight();  //获取右子树节点高度
        //当添加完一个节点后当(rightHeight-leftHeight>1)  左旋转
        if (rightHeight - leftHeight > 1) {
            //如果它的右子树的左子树节点高度大于右子树的右子节点高度  先进行右旋转 然后对当前节点左旋转
            if (rightNode != null && rightNode.getLeftHeight() > rightNode.getRightHeight()) {
                //右旋转
                rightNode.rightRoate();
                //左旋转
                leftRoate();
            } else {
                //左旋转
                leftRoate();
            }
            return;  //必须要
        }

        //当添加完一个节点后 如果(leftHeight()-rightHeight>1)  右旋转
        if (leftHeight - rightHeight > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (leftNode != null && leftNode.getRightHeight() > leftNode.getLeftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                leftNode.leftRoate();
                //再对当前结点进行右旋转
                rightRoate();
            } else {
                //直接进行右旋转即可
                rightRoate();
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

    /**
     * 获取左子树节点高度
     *
     * @return 返回左子树节点高度
     */
    public int getLeftHeight() {
        if (leftNode == null) {
            return 0;
        }
        return leftNode.height();
    }

    /**
     * 获取右子树节点高度
     *
     * @return 返回右子树节点高度
     */
    public int getRightHeight() {
        if (rightNode == null) {
            return 0;
        }
        return rightNode.height();
    }

    /**
     * 获取当前节点的树的高度
     *
     * @return 返回树的高度
     */
    public int height() {
        return Math.max(leftNode == null ? 0 : leftNode.height(), rightNode == null ? 0 : rightNode.height())+1;
    }

    /**
     * 左旋转
     */
    public void leftRoate() {
        //定义新节点，且节点的值指向当前节点的值
        Node newNode = new Node(this.getValue());
        //新节点的左子树指向当前节点的左子树
        newNode.setLeftNode(leftNode);
        //新节点的右子树指向当前节点右子树的左子树
        newNode.setRightNode(rightNode.getLeftNode());
        //当前节点的值指向右子节点值
        this.setValue(rightNode.getValue());
        //当前节点的的左子树指向新节点
        this.setLeftNode(newNode);
        //当前节点右子树指向右子树的右子节点
        this.setRightNode(rightNode.getRightNode());
    }

    /**
     * 右旋转
     */
    public void rightRoate() {
        //定义新节点,且新节点的值指向当前节点
        Node newNode = new Node(this.getValue());
        //新节点的右子树节点指向当前节点的右子树
        newNode.setRightNode(rightNode);
        //新节点的左子树指向当前节点的左子树的右子节点
        newNode.setLeftNode(leftNode.getRightNode());
        //当前节点的值指向当前节点的左子节点值
        this.setValue(leftNode.getValue());
        //当前节点的右子树指向新节点
        this.setRightNode(newNode);
        //当前节点的左子树指向左子树的左节点
        this.setLeftNode(leftNode.getLeftNode());
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
