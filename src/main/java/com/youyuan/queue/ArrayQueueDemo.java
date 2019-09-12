package com.youyuan.queue;

import java.util.Scanner;

/**
 * @author zhangyu
 * @version 1.0
 * @description  简单的数组模拟队列
 *
 * 思路：从尾部放入数据，放入数据的时候rear+1,取数据的时候从头部取 front+1
 *
 * @date 2019/7/22 21:56
 */
public class ArrayQueueDemo
{
    public static void main(String[] args) {
        ArrayQueue queue=new ArrayQueue(3);
        boolean bool=true;
        Scanner scanner=new Scanner(System.in);

        while (bool){
            System.out.println("s(show) 显示队列");
            System.out.println("g(get) 获取队列第一个元素");
            System.out.println("a(add) 添加队列");
            System.out.println("h(head) 显示头部队列元素");
            System.out.println("e(exit) 退出队列");
            char key=scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'g':
                    try {
                        System.out.printf("读取的数据%d\n",queue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        int value=scanner.nextInt();
                        queue.addQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'h':
                    try {
                        System.out.println(queue.headQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    break;
                default:
                    break;
            }

        }
    }
}

class ArrayQueue{
    private int maxSize;//队列的长度
    private int front;//队列的头部索引位置 默认-1，不包含有数据的索引
    private int rear;//队列尾部索引位置  默认-1，包含有数据的索引
    private int[] arr;//存储数据的数组

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front=-1;
        rear=-1;
        arr=new int[maxSize];
    }

    /**
     * 判断队列是否满
     * @return  true满 false否
     */
    public boolean isFull(){
        return rear==maxSize-1;
    }

    /**
     * 判断是否为空
     * @return true空 flase否
     */
    public boolean isEmpty(){
        return front==rear;
    }

    /**
     * 添加队列
     * @param n  要添加的数据
     */
    public void addQueue(int n){
        if (isFull()){
            throw new RuntimeException("队列已满,不能添加~");
        }
        arr[++rear]=n;
    }

    /**
     * 获取数据
     * @return
     */
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能取数据...");
        }
        return arr[++front];
    }

    /**
     * 显示队列内容
     */
    public void showQueue(){
        for (int i=0;i<maxSize;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 显示队列头部元素
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能取数据...");
        }
        return arr[front];
    }
}
