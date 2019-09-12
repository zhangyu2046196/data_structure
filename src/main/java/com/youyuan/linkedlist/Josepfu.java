package com.youyuan.linkedlist;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 单向环形链表应用场景  约瑟夫问题
 *
 *  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 *    提示：用一个不带头结点的循环链表来处理Josephu 问题：先构成一个有n个结点的单循环链表，然后由k结点起从1开始计数，计到m时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从1开始计数，直到最后一个结点从链表中删除算法结束。
 *
 * @date 2019/7/25 15:57
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLikedList list=new CircleSingleLikedList();
        list.show();

        System.out.println("=======单向环形链表加入元素=======");

        list.add(5);

        list.show();

        System.out.println("=======约瑟夫出圈逻辑=======");

        list.count(1,2,5);

    }

}

class CircleSingleLikedList{
    Node first=null;  //头节点 不能动

    /**
     * 批量添加单向环形链表
     * @param num  数量
     */
    public void add(int num){
        if (num<1){
            System.out.println("添加单向环形链表参数不合法,num:"+num);
            return;
        }

        for (int i=1;i<=num;i++){
            Node n=new Node(i);
            if (first == null){
                first=n;
                n.setNext(first);
            }else {
                Node current=first;
                while (true){
                    if (current.getNext()==first){//循环到最后一个节点
                        current.setNext(n);
                        n.setNext(first);
                        break;
                    }
                    current=current.getNext();
                }
            }
        }
    }

    /**
     * 展示
     */
    public void show(){
        Node current=first;  //临时变量

        if (current==null){
            System.out.println("单向环形链表为空~~~");
            return;
        }

        while (true){
            System.out.printf("这是第%d个节点\n",current.getNo());
            if (current.getNext()==first){
                break;
            }
            current=current.getNext();
        }
    }

    /**
     * 约瑟夫出圈实现代码
     * @param startNo  开始位置
     * @param countNum 数几次
     * @param nums  单向环形链表元素数
     *
     *
     *
     */
    public void count(int startNo,int countNum,int nums){
        if (startNo<1 || nums<startNo){
            System.out.println("参数不合法~~~");
            return;
        }

        Node end=first;  //变量，标记最后一个节点

        //循环链表，找到最后一个节点
        while (true){
            if (end.getNext()==first){
                break;
            }
            end=end.getNext();
        }

        //出圈逻辑
        while (true){
            if (first==end){
                System.out.println("剩在单向环形链表的唯一元素是:"+first);
                break;
            }

            //将first移动到开始数数的节点
            for (int i=0;i<startNo-1;i++){
                first=first.getNext();
                end=end.getNext();
            }

            //将first移动到要出圈的节点
            for (int i=0;i<countNum-1;i++){
                first=first.getNext();
                end=end.getNext();
            }

            //此时first为要出圈的元素，修改指针
            System.out.println("出圈元素为:"+first);
            first=first.getNext();
            end.setNext(first);
        }

    }

}

/**
 * 节点
 */
class Node implements Serializable{

    private static final long serialVersionUID = -2427095052522776105L;

    private int no;  //编号

    private Node next;  //下一个节点

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
