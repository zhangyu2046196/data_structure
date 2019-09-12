package com.youyuan.stack;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author zhangyu
 * @version 1.0
 * @description  使用单向链表模拟栈
 * @date 2019/7/26 8:18
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        LinkedListStack stack=new LinkedListStack(5);
        boolean loop=true;  //标记是否循环  true 循环  false 否
        while (loop){
            System.out.println("show(展示)");
            System.out.println("push(入栈)");
            System.out.println("pop(出栈)");
            System.out.println("exit(退出)");

            Scanner scanner=new Scanner(System.in);  //获取扫描器
            String key=scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入...");
                    int value = scanner.nextInt();
                    stack.push(value);
                    System.out.printf("栈的元素个数%d\n",stack.getLength());
                    break;
                case "pop":
                    int res=stack.pop();
                    System.out.printf("出栈元素是%d\n",res);
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                default:
                    break;
            }
        }
    }

}

/**
 * 单向链表栈
 */
class LinkedListStack{
    private Node top;  //栈顶节点信息

    private int maxSize;  //栈的大小

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }



    /**
     * 判断栈是否满
     * @return  true 满  false  否
     */
    public boolean isFull(){
        return getLength() == maxSize;
    }

    /**
     * 判断栈是否空
     * @return  true 空  false  否
     */
    public boolean isEmpty(){
        return top == null;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        if (isFull()){
            System.out.println("栈满...");
            return;
        }
        Node node=new Node(value);
        node.setNext(top);
        top=node;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if (isEmpty()){
            System.out.println("栈空...");
            return -1;
        }
        int res=top.getData();
        top=top.getNext();
        return res;
    }

    /**
     * 展示
     */
    public void show(){
        if (isEmpty()){
            System.out.println("栈空~");
            return;
        }
        Node current=top;
        while (true){
            System.out.printf("栈的元素%d\n",current.getData());
            current=current.getNext();
            if (current == null){
                break;
            }
        }
    }

    /**
     * 获取栈的有效记录数
     * @return
     */
    public int getLength(){
        Node current=top;
        if (current == null){
            return 0;
        }
        int count=0;
        while (true){
            count++;
            current=current.getNext();
            if (current == null){
                break;
            }
        }
        return count;
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }
}

/**
 * 节点
 */
class Node implements Serializable{
    private static final long serialVersionUID = -4425673502412176032L;
    private int data;  //数据
    private Node next;  //下一个元素

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
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
                "data=" + data +
                '}';
    }
}
