package com.youyuan.linkedlist;

import java.io.Serializable;
import java.util.Stack;

/**
 * @author zhangyu
 * @version 1.0
 * @description  单向链表测试
 *
 * 背景：保存内容为水浒传人物
 * 思路：
 * 先创建一个头节点，头节点不存储任何数据项，只存储下一个节点指针
 *
 * @date 2019/7/23 22:21
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        SingleLinkedList list=new SingleLinkedList();

        HeroNode heroNode1=new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3=new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4=new HeroNode(4,"林冲","豹子头");
        HeroNode heroNode5=new HeroNode(5,"鲁智深","花和尚");

//        list.add(heroNode1);
//        list.add(heroNode2);
//        list.add(heroNode3);
//        list.add(heroNode4);
//        list.add(heroNode5);
//
        list.addByOrder(heroNode3);
        list.addByOrder(heroNode2);
        list.addByOrder(heroNode4);
        list.addByOrder(heroNode5);
        list.addByOrder(heroNode5);
        list.addByOrder(heroNode1);

        list.show();

        System.out.println("============修改后============");

        HeroNode hNode=new HeroNode(2,"小卢","玉麒麟~~");
        list.update(hNode);

        list.show();

        System.out.println("============删除后============");
        //list.delete(2);
        //list.delete(4);

        list.show();

        System.out.println("============链表有效节点数=============");

        System.out.println("链表有效节点个数:"+getLength(list.getHead()));

        System.out.println("倒数第2个元素是:"+getLastIndexNode(list.getHead(),2));

        System.out.println("============链表反转=============");
//        reversal(list.getHead());
//        list.show();

        System.out.println("============通过栈的方式倒序打印链表============");
        printLinkedListDesc(list.getHead());



    }

    /**
     * 通过栈先进后出的特点倒序打印单向链表  通过栈的方式不破坏原来链表的数据结构
     * @param head 链表头节点
     */
    public static void printLinkedListDesc(HeroNode head){
        if (head.getNext() == null){
            return;
        }
        Stack<HeroNode> stack=new Stack<HeroNode>();//定义一个栈
        //定义变量存储链表当前元素
        HeroNode current=head.getNext();
        //循环遍历链表
        while(current!=null){
            stack.push(current);  //入栈
            current=current.getNext();
        }

        printLinedListByStack(stack);

    }

    /**
     * 递归打印栈的元素
     * @param stack
     */
    public static void printLinedListByStack(Stack<HeroNode> stack){
        if (!stack.empty()){  //判断栈是否为空
            System.out.println(stack.pop());
            printLinedListByStack(stack);
        }
    }

    /**
     * 单链表反转
     * @param head  头结点
     *
     * 思路：
     *    1.因为头节点不存储数据，所以定义一个变量存储当前元素 current
     *    2.定义一个新的头节点 newHead
     *    3.循环链表的新元素current.setNext(newHead.getNext())
     *    4.newHead.setNext(current)
     *
     * @return
     */
    public static HeroNode reversal(HeroNode head){
        if (head.getNext() == null){
            return null;
        }
        //定义一个新的头节点
        HeroNode newHeroNode=new HeroNode(0,"","");
        //定义变量存储当前元素
        HeroNode current=head.getNext();
        //定义一个变量是新头节点的当前变量
        HeroNode newCurrent = null;
        while(current!=null){
            newCurrent=current;
            current=current.getNext();
            newCurrent.setNext(newHeroNode.getNext());  //循环的元素的下一个元素是新头节点的下一个元素
            newHeroNode.setNext(newCurrent);  //循环的元素作为新头节点的下一个元素
        }
        head.setNext(newHeroNode.getNext());  //将新的头节点(已反转好的)设置到原头节点的下一个节点
        return head;
    }

    /**
     * 查询链表倒数第index个元素
     * @param head 头节点，因为头节点不存储数据，所以从head.getNext节点开始查询
     * @param index 倒数第index
     *
     * 思路：计算链表有效元素数量size，获取倒数第index元素就是获取第size-index 元素
     *
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode head,int index){
        if (head.getNext()==null){
            return null;
        }
        //计算链表有效节点数
        int size=getLength(head);
        if (index<=0 || index>size){
            return null;
        }
        //定义变量记录当前元素
        HeroNode current=head.getNext();
        //size-index 是要返回的元素
        for (int i=1;i<=size-index;i++){
            current=current.getNext();
        }
        return current;
    }

    /**
     * 计算链表有效元素个数
     *
     * 思路：传递参数为头节点，因为头节点不存储数据，所以遍历链表的时候需要从头节点的next节点开始
     *
     * @param head
     * @return
     */
    public static int getLength(HeroNode head){
        if (head.getNext()==null){
            return 0;
        }
        //定义当前节点变量current 当current为空代表已经遍历完列表
        HeroNode current=head.getNext();
        //定义变量记录有效节点数
        int size=0;
        while(current!=null){
            size++;
            current=current.getNext();
        }
        return size;
    }

}

class SingleLinkedList{

    private HeroNode head;  //是一个空元素，不存储数据，只保存指向下一个元素的指针

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    public SingleLinkedList() {
        head=new HeroNode(0,"","");
    }

    /**
     * 添加
     * @param node
     */
    public void add(HeroNode node){
        HeroNode temp=head;  //当前节点变量
        while(true){
            if (temp.getNext()==null){
                temp.setNext(node);
                break;
            }
            temp=temp.getNext();
        }
    }

    /**
     * 根据应用编号按照升序
     * @param node
     */
    public void addByOrder(HeroNode node){
        HeroNode temp=head;  //参考变量
        boolean flag=false;  //标记是否已经有相同的编号节点 true 有     false否
        //循环是为了找到要添加节点的前面一个节点
        while (true){
            if (temp.getNext()==null){  //如果是最后一个节点退出循环
                break;
            }
            if (temp.getNext().getNo()>node.getNo()){
                break;
            }else if (temp.getNext().getNo()==node.getNo()){
                flag=true;
                break;
            }
            temp=temp.getNext();
        }

        if (flag){
            System.out.printf("要添加的英雄%d\t已经存在了 \n",node.getNo());
        }else {
            node.setNext(temp.getNext());
            temp.setNext(node);
        }
    }

    /**
     * 修改
     *
     * 思路：根据no排名字段修改，修改名字和昵称
     *
     * @param hNode
     */
    public void update(HeroNode hNode){
        if (hNode==null){
            System.out.println("修改的英雄为空");
        }
        boolean flag=false;  //标记  true代表要修改的节点在链表中存在，false代表要修改的节点在链表不存在
        HeroNode temp=head.getNext();
        if (temp==null){
            System.out.println("要修改的链表为空");
        }
        while(true){
            if (temp==null){
                System.out.println("已经到达链表末尾");
                break;
            }
            if (temp.getNo()==hNode.getNo()){
                flag=true;
                break;
            }
            temp=temp.getNext();
        }
        if (flag){
            temp.setName(hNode.getName());
            temp.setNickName(hNode.getNickName());
        }else {
            System.out.printf("链表中没有找到要修改的节点%d\n",hNode.getNo());
        }
    }

    /**
     * 根据序号删除链表元素
     *
     * 思路：找到要删除元素的前一个元素，让前一个元素的next指向删除元素的next
     *
     * @param no  序号
     */
    public void delete(int no){
        HeroNode temp=head;  //变量，标记当前节点
        boolean flag=false;  // true 标记要删除的节点存在  false 标记要删除的节点不存在
        if (temp.getNext()==null){
            System.out.println("链表为空~~");
            return;
        }
        while(true){
            if (temp.getNext()==null){
                System.out.println("链表已到末尾~~");
                break;
            }
            if (temp.getNext().getNo()==no){
                flag=true;
                break;
            }
            temp=temp.getNext();
        }
        if (flag){
            temp.setNext(temp.getNext().getNext());
        }else {
            System.out.printf("要删除的节点%d在链表中不存在\n",no);
        }
    }

    /**
     * 展示
     */
    public void show(){
        HeroNode temp=head.getNext();
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.getNext();
        }
    }
}

/**
 * 节点
 */
class HeroNode implements Serializable{

    private static final long serialVersionUID = -5010804539072283001L;

    private int no;  //排名
    private String name;  //名称
    private String nickName;  //昵称
    private HeroNode next;  //下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
