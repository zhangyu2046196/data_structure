package com.youyuan.linkedlist;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description  双向链表测试
 *
 * 1、查询思路分析
 *因为是双向链表，所以查询的时候可以从前向后查询，也可以从后向前查询
 *2、添加思路分析 (添加到链表尾部)
 *定义变量temp代表循环的当前节点，当temp.getNext==null时代表遍历到了尾部，temp.setNext=newNode    newNode.setPre=temp
 *3、修改思路分析
 *定义变量temp代表循环的当前节点，直接修改temp的属性值就可以
 *4、删除思路分析  (根据no序号删除)
 *定义变量temp代表循环的当前节点，temp.getNo=delNode.getNo  时代表找到要删除的节点
 *
 *如果删除的不是头节点也不是尾节点时，判断方式如下


 *temp.getPre.setNext=temp.getNext
 *temp.getNext.setPre=temp.getPre
 *
 *如果删除的是头节点


 *temp.getPre==null  代表头节点  判断方式如下
 *temp.getNext.setPre=null
 *
 *如果删除的是尾节点


 *temp.getNext ==null 代表尾节点  判断方式如下
 *temp.getPre.setNext=null
 *
 * @date 2019/7/24 22:32
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 heroNode1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 heroNode2=new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 heroNode3=new HeroNode2(3,"无用","智多星");
        HeroNode2 heroNode4=new HeroNode2(4,"林冲","豹子头");
        HeroNode2 heroNode5=new HeroNode2(5,"鲁智深","花和尚");

        DoubleLinkedList list=new DoubleLinkedList();

        list.addByOrder(heroNode3);
        list.addByOrder(heroNode2);
        list.addByOrder(heroNode5);
        list.addByOrder(heroNode1);
        list.addByOrder(heroNode4);

        System.out.println("========初始化展示=========");

        list.show();

        System.out.println("========修改后显示结果=========");

        //list.update(new HeroNode2(2,"小卢","玉麒麟~~"));

        //list.show();

        System.out.println("========删除后显示内容========");

        //list.delet(2);

        //list.show();

    }

}

/**
 * 链表
 */
class DoubleLinkedList{

    private HeroNode2 head=null;  //头节点不存储数据，只存储下一个节点的指针

    public DoubleLinkedList() {
        head=new HeroNode2(0,"","");
    }

    /**
     * 添加到尾部
     * @param node 要添加的节点
     */
    public void add(HeroNode2 node){
        HeroNode2 currnet=head;  //头结点不能改变
        boolean flage=false;     //true 标记有相同编号的元素  false没有
        while (true){
            if (currnet.getNext() == null){
                break;
            }
            if (currnet.getNo() == node.getNo()){
                flage=true;
                break;
            }
            currnet=currnet.getNext();
        }
        if (flage){
            System.out.println("要添加的元素已经存在:"+node);
        }else {
            currnet.setNext(node);
            node.setPre(currnet);
        }
    }

    /**
     * 排序添加
     * @param node  要添加元素
     */
    public void addByOrder(HeroNode2 node){
        HeroNode2 current=head;
        boolean flag=false;  //标记是否存在 true存在  false不存在
        while (true){

            if (current.getNext()==null){
                break;
            }

            if (current.getNext().getNo()>node.getNo()){  //添加的元素序号小于当前链表的下一个元素
                break;
            }else if (current.getNext().getNo()==node.getNo()){  //添加的元素在链表存在
                flag=true;
                break;
            }
            current=current.getNext();
        }
        if (flag){
            System.out.println("要添加的元素已经存在"+node);
        }else{
            node.setNext(current.getNext());
            current.setNext(node);
            node.setPre(current);
            if (node.getNext()!=null){
                node.getNext().setPre(node);
            }
        }

    }

    /**
     * 显示
     */
    public void show(){
        HeroNode2 current=head.getNext();
        while (current!=null){
            System.out.println(current+" 前一个:"+current.getPre());
            current=current.getNext();
        }
    }

    /**
     * 修改
     * @param node  要修改的元素
     */
    public void update(HeroNode2 node){
        HeroNode2 current = head.getNext();
        while (current!=null){
            if (current.getNo() == node.getNo()){
                current.setName(node.getName());
                current.setNickName(node.getNickName());
                break;
            }
            current=current.getNext();
        }
    }

    /**
     * 根据no删除
     * @param no  要删除节点序号
     */
    public void delet(int no){
        HeroNode2 current=head.getNext();
        boolean flage=false;  //true标记链表找到要删除的元素  false不存在
        while (true){
            if (current == null){
                System.out.println("删除元素在链表不存在或链表为空");
                break;
            }
            if (current.getNo() == no){
                flage=true;
                break;
            }

            current=current.getNext();

        }
        if (flage){
            if (head.getNext() == current){//第一个节点
                if (current.getNext()==null){//只有一个节点
                    head.setNext(null);
                }else{
                    current.getNext().setPre(head);
                    head.setNext(current.getNext());
                }
            }else if (current.getNext()==null){//最后一个节点
                current.getPre().setNext(null);
            }else {
                current.getPre().setNext(current.getNext());
                current.getNext().setPre(current.getPre());
            }
        }

    }
}

/**
 * 节点
 */
class HeroNode2 implements Serializable{

    private static final long serialVersionUID = -5010804539072283001L;

    private int no;  //英雄排序序号
    private String name;  //英雄名字
    private String nickName;  //英雄昵称
    private HeroNode2 next;   //下一个元素
    private HeroNode2 pre;  //上一个元素

    public HeroNode2(int no, String name, String nickName) {
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

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
