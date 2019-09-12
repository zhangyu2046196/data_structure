package com.youyuan.stack;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhangyu
 * @version 1.0
 * @description  逆波兰表达式 示例
 *
 * 表达式：(3+4)×5-6   ==>  3 4 + 5 * 6 -   用空格隔开
 *
 * 思路：
 *
 *
 * 1、从左至右扫描，将3和4压入堆栈；
 * 2、遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
 * 3、将5入栈；
 * 4、接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * 5、将6入栈；
 * 6、最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 *
 *
 * 中缀表达式转后缀表达式思路分析
 *
 * 1、初始化两个栈：运算符栈s1和储存中间结果的栈s2；
 * 2、从左至右扫描中缀表达式；
 * 3、遇到操作数时，将其压s2；
 * 4、遇到运算符时，比较其与s1栈顶运算符的优先级：
 *  4.1、如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 *  4.2、否则，若优先级比栈顶运算符的高，也将运算符压入s1；
 *  4.3、否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
 * 5、遇到括号时：
 *  5.1、如果是左括号“(”，则直接压入s1
 *  5.2、如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6、重复步骤2至5，直到表达式的最右边
 * 7、将s1中剩余的运算符依次弹出并压入s2
 * 8、依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 *
 * @date 2019/7/29 17:51
 */
public class PolanNoTation {

    public static void main(String[] args) {

//        //String suffixExpression="3 4 + 5 * 6 -";  //逆波兰表达式
//
//        //4*5-8+60+8/2=  76
//        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";  //逆波兰表达式
//
//        //逆波兰表达式按照分隔符拆分存储到list
//        List<String> list=expressionToList(suffixExpression);
//        System.out.println("表达式存储到list："+list);
//
//        int res=calculate(list);
//        System.out.println("计算结果是："+res);

        //中缀表达式转后缀表达式计算步骤
        //1、中缀表达式1+((2+3)×4)-5  解析放入list  => ArrayList<String>()[1,+,(,(,2,+,3,),*,),-,5]
        //2、中缀表达式的list转成后缀表达式list  ArrayList<String>()[1,+,(,(,2,+,3,),*,),-,5]  => ArrayList<String> [1,2,3,+,4,*,+,5,–]

        //中缀表达式
        //String infixExpression="1+((2+3)*4)-5";
        String infixExpression="(2+9/3+(8*2-1))/2+32-16";
        //中缀表达式解析到list，可读性高
        List<String> infixList=toInfixExpression(infixExpression);
        System.out.println("中缀表达式解析放入list："+infixList);

        //中缀表达式转成后缀表达式
        List<String> suffixList=infixToSuffixExpression(infixList);
        System.out.println("后缀表达式list："+suffixList);

        //计算表达式的结果
        int res=calculate(suffixList);
        System.out.println("表达式:"+infixExpression+" 结果:"+res);

    }

    /**
     * 中缀表达式转成后缀表达式
     * @param infixList  中缀表达式
     * @return  返回后缀表达式
     */
    public static List<String> infixToSuffixExpression(List<String> infixList){
        Stack<String> s1=new Stack<String>();  //存储运算符栈
        //因为第八步中间结果的栈还需要倒序输出，所以此处不用中间结果栈，用List
        List<String> suffixList=new ArrayList<String>();
        //Stack<String> s2=new Stack<String>();  //存储中间结果栈

        //循环遍历中缀表达式
        for (String ele : infixList) {
            if (ele.matches("\\d+")){  //遇到操作数时，将其压s2
                suffixList.add(ele);
            }else if (ele.equals("(")){  //如果是左括号“(”，则直接压入s1
                s1.push(ele);
            }else if (ele.equals(")")){  //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    suffixList.add(s1.pop());
                }
                s1.pop();  //把 左括号 ( 弹出
            }else {  //元素为运算符

                // s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较
                while(s1.size() != 0 && OperPriority.getValue(s1.peek()) >= OperPriority.getValue(ele) ) {
                    suffixList.add(s1.pop());
                }

                s1.push(ele);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size() != 0) {
            suffixList.add(s1.pop());
        }

        return suffixList;
    }

    /**
     * 中缀表达式解析到List
     * @param expersion 表达式
     * @return  返回list
     */
    public static List<String> toInfixExpression(String expersion){
        if (StringUtils.isNotBlank(expersion)){
            List<String> list=new ArrayList<String>();  //存放解析的表达式
            int index=0;  //指针
            String ch="";      //拼接字符串
            while (index<expersion.length()){
                //因为数字对应的ACSSIC码是48-57  所以不在这个范围就是运算符
                if (expersion.charAt(index)<48 || expersion.charAt(index)>57){
                    list.add(expersion.charAt(index)+"");
                    index++;
                }else {
                    ch="";
                    while (index<expersion.length() && expersion.charAt(index)>=48 && expersion.charAt(index)<=57){  //数字就开始拼接起来
                        ch+=expersion.charAt(index);
                        index++;
                    }
                    list.add(ch);
                }
            }
            return list;
        }
        return null;
    }

    /**
     * 计算
     * @param list  数字和运算符列表
     * @return  返回计算结果
     */
    public static int calculate(List<String> list){
        Stack<String> stack=new Stack<String>();  //定义栈 用于存储数字和计算结果
        for (String item : list) {
            if (item.matches("\\d+")){  //正则表达式匹配是否是数字
                stack.push(item);
            }else {  //运算符
                int num1=Integer.valueOf(stack.pop());  //弹出栈顶元素
                int num2=Integer.valueOf(stack.pop());  //弹出次顶元素
                int res=0;
                switch (item){
                    case "+":
                        res = num2 + num1;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num2 * num1;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        break;
                }
                stack.push(res+"");
            }
        }
        return Integer.valueOf(stack.peek());
    }

    /**
     * 逆波兰表达式按照分隔符存储到list
     * @param suffixExpression  逆波兰表达式
     * @return  返回list
     */
    public static List<String> expressionToList(String suffixExpression){
        if (StringUtils.isBlank(suffixExpression)){
            return null;
        }
        List<String> list=new ArrayList<String>();
        String[] split = suffixExpression.split("\\ ");
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

}

/**
 * 运算符优先级
 */
class OperPriority{
    private static int ADD = 1;  //加
    private static int SUB = 1;  //减
    private static int MUL = 2;  //乘
    private static int DIV = 2;  //除

    /**
     * 计算优先级
     * @param oper  运算符
     * @return  返回优先级
     */
    public static int getValue(String oper){
        int res=0;
        switch (oper){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}
