package com.youyuan.stack;

/**
 * @author zhangyu
 * @version 1.0
 * @description   数组模拟栈的入栈和出栈
 *
 * 思路：
 *  maxSize : 数组的大小  栈的长度
 *  top : 栈顶指针
 *  stack : 数组 用于存放数据
 *
 * @date 2019/7/25 23:03
 */
public class ArrayStackDemo
{

    private static ArrayStack stack=new ArrayStack(100);

    public static void main(String[] args) {
        for (int i=0;i<102;i++){
             stack.push(i);
        }

        System.out.println("========打印=======");

        stack.show();

    }
}

class ArrayStack{
    private int maxSize;  //栈的大小
    private int top=-1;  //栈顶指针
    private int[] stack;  //数组

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[maxSize];
    }

    /**
     * 判断栈是否满
     * @return true 满  false否
     */
    public boolean isFull(){
        return top==maxSize-1;
    }

    /**
     * 判断栈是否空
     * @return true 空  false否
     */
    public boolean isEmpty(){
        return top==-1;
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
        stack[++top]=value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空...");
        }
        int value=stack[top];
        --top;
        return value;
    }

    /**
     * 打印栈，从栈顶到栈底顺序打印
     */
    public void show(){
        if (isEmpty()){
            System.out.println("栈空...");
            return;
        }
        for (int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
