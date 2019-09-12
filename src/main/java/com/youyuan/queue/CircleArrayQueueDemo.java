package com.youyuan.queue;

import java.util.Scanner;

/**
 * @author zhangyu
 * @version 1.0
 * @description 数组实现环形队列
 *
 * 思路：
 * front=0  队列头索引
 * rear=0  队列尾索引，插入一条数据后rear执行下一个元素的索引
 * maxSize  数组长度
 * 判断队列已满公式：(rear+1)%maxSize=front
 * 判断队列为空公式：front=rear
 * 判断队列有效数据数公式：(rear+maxSize-front)%maxSize
 *
 * @date 2019/7/23 17:39
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {


        CircleArrayQueue circleArrayQueue=new CircleArrayQueue(3);

        boolean bool=true;
        while (bool){
            System.out.println("a(addQueue) 添加元素");
            System.out.println("g(getQueue) 获取元素");
            System.out.println("h(headQueue) 显示第一个元素");
            System.out.println("s(showQueue) 循环元素");
            System.out.println("e(exit) 退出");

            Scanner scanner=new Scanner(System.in);

            char key=scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    circleArrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    System.out.println(circleArrayQueue.getQueue());
                    break;
                case 'h':
                    System.out.println(circleArrayQueue.headQueue());
                    break;
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    bool=false;
                    break;
                default:
                    break;
            }
        }
    }

}

class CircleArrayQueue{
    private int maxSize;  //队列长度
    private int front;  //队列头位置
    private int rear;  //队列尾位置
    private int[] arr;  //数组

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }

    /**
     * 添加队列
     * @param n
     */
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;  //添加后将队列尾的位置指向下一个队列元素位置
    }

    /**
     * 从队列头获取元素
     * @return
     */
    public int getQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return 0;
        }
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }

    /**
     * 显示队列头元素
     * @return
     */
    public int headQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return 0;
        }
        return arr[front];
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }

        for (int i=front;i<(front+size());i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    /**
     * 判断队列是否为空
     * @return true 空  false 否
     */
    public boolean isEmpty(){
        return rear==front;
    }

    /**
     * 判断队列是否满
     * @return true 满  false 否
     */
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    /**
     * 取出队列元素数
     * @return
     */
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
}
