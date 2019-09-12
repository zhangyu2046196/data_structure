package com.youyuan.stack;

/**
 * @author zhangyu
 * @version 1.0
 * @description  栈应用场景  模拟表达式计算
 *
 * 3+2*6-2
 *
 *
 * 1、定义两个栈，numStack存储表达式中的数字   operStack存储表达式中云算法
 *
 * 2、定义一个指针index，从左到右遍历表达式
 *
 * 3、当遍历到是数字时push到numStack
 *
 * 4、当遍历到是运算符时 判断operStack是否为空，如果为空就直接push operStack，如果operStack不为空判断当前的运算符的优先级，如果当前运算符优先级小于等于operStatc的优先级，就将numStack 数字栈中pop出两个数然后在从operStack 运算符栈中pop出一个运算符，然后计算，将计算结果在push到numStack中，并且将当前运算符push到operStack
 *
 * 5、当当前运算符优先级大于operStack的栈顶元素的优先级时，就将当前运算符push 到 operStack
 *
 * 6、当表达式扫描完成后就顺序从numStack中pop出两个数，然后从operStack中pop出一个运算符计算，将计算结果在push 到 numStack中
 *
 * 7、最后numStack中只有存在一个数据就是计算的结果
 *
 * @date 2019/7/29 11:19
 */
public class Calculator {

    public static void main(String[] args) {
        String operation="70-20+6*2";
        ArrayStackCalclator numStack=new ArrayStackCalclator(10);  //定义栈存储数字
        ArrayStackCalclator operStack=new ArrayStackCalclator(10);  //定义栈存储运算符
        int index=0;  //指针
        char ch=' ';  //每次扫描的结果保存到ch中
        String keepNum="";  //保存数字，用于拼接多位数
        while(true){  //遍历表达式
            ch=operation.substring(index,index+1).charAt(0);  //扫描一个元素
            if (operStack.isOper(ch)){  //判断扫描的元素是否是运算符  true运算符  false 否
                if (!operStack.isEmpty()){
                    int peekPriority=operStack.priority(operStack.peek());
                    int chPriority=operStack.priority(ch);
                    if (chPriority<=peekPriority){  //当前运算符的优先级小于等于栈里运算符的优先级
                        int num1=numStack.pop();
                        int num2=numStack.pop();
                        int oper=operStack.pop();
                        int res=numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {  //扫描的元素不是运算符  是数字
                //numStack.push(ch-48);  //因为对应ASCII 需要减48

                //如果多位数加减乘除 需要循环向后判断是否是运算符，不是运算符需要拼接到一起入栈    70-20*3-12
                //此时index不进行加减，指定判断下一位是否是运算符，是运算符就入栈，不是就继续循环

                keepNum+=ch;
                if (index==operation.length()-1){  //最后一个字符就直接入栈
                    numStack.push(Integer.valueOf(keepNum));
                }else {
                    if (numStack.isOper(operation.substring(index+1,index+2).charAt(0))){  //如果下一位是运算符就入栈
                        numStack.push(Integer.valueOf(keepNum));
                        keepNum="";
                    }
                }
            }

            index++;  //指针后移一位
            if (index>=operation.length()){
                break;
            }
        }

        //遍历 数字栈和运算符栈 计算
        while (!operStack.isEmpty()){
            int num1=numStack.pop();
            int num2=numStack.pop();
            int opera=operStack.pop();
            int res=numStack.cal(num1,num2,opera);
            numStack.push(res);
        }

        System.out.println("结果 : "+numStack.peek());

    }

}

class ArrayStackCalclator{
    private int maxSize;  //栈的大小
    private int top=-1;  //栈顶指针
    private int[] stack;  //数组

    public ArrayStackCalclator(int maxSize) {
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

    /**
     * 判断是否是运算符
     * @param oper 运算符
     * @return  true 是  false 否
     */
    public boolean isOper(int oper){
        if (oper=='+' || oper=='-' || oper=='*' || oper=='/'){
            return true;
        }
        return false;
    }

    /**
     * 查询栈顶元素
     * @return  返回栈顶元素
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 返回运算符优先级
     * @param oper 运算符
     * @return  返回数字 数字越大优先级越高
     */
    public int priority(int oper){
        if (oper=='*' || oper=='/'){
            return 2;
        }else if (oper=='+' || oper=='-'){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * 运算
     * @param num1 参数  num2比num1先入栈的数据
     * @param num2 参数
     * @param oper 运算符
     * @return 运算结果
     */
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '*':
                res = num2*num1;
                break;
            case '/':
                res = num2/num1;
                break;
            case '+':
                res = num2+num1;
                break;
            case '-':
                res = num2-num1;
                break;
            default:
                break;
        }
        return res;
    }

}
