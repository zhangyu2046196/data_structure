package com.youyuan.hash;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author zhangyu
 * @version 1.0
 * @description 自定义HashTable
 * <p>
 * 思路：
 * 数组加链表的方式实现
 * @date 2019/8/6 19:32
 */
public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab=new HashTab(7);
        //获取scanner用来读取控制台输入
        Scanner scanner=new Scanner(System.in);
        String key="";  //接收控制台输入
        while (true){
            System.out.println("=========控制台开始==========");
            System.out.println("add    添加");
            System.out.println("list   遍历");
            System.out.println("find   查找");
            System.out.println("delete 删除");
            System.out.println("exit   退出");
            key=scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入id");
                    int id=scanner.nextInt();
                    System.out.println("请输入名称");
                    String name=scanner.next();
                    hashTab.add(new Emp(id,name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    id=scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "delete":
                    System.out.println("请输入id");
                    id=scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
            System.out.println("=========控制台结束==========");
        }
    }

}

/**
 * 自定义hashtable
 */
class HashTab{
    private int maxSize;  //数组大小长度
    private EmpLinkedList[] empListArray;  //数组链表

    public HashTab(int maxSize) {
        this.maxSize = maxSize;
        empListArray=new EmpLinkedList[maxSize];
        //给 数组中 添加链表对象，否则使用时空指针
        for (int i=0;i<maxSize;i++){
            empListArray[i]=new EmpLinkedList();
        }
    }

    /**
     * 添加员工到hashtable
     * @param emp  员工
     */
    public void add(Emp emp){
        int arrayIndex=hash(emp.getId());
        empListArray[arrayIndex].add(emp);
    }

    /**
     * 遍历hashtable
     */
    public void list(){
        for (int i=0;i<empListArray.length;i++){
            empListArray[i].list(i+1);
        }
    }

    /**
     * 根据员工主键查询
     * @param id  主键
     */
    public void findById(int id){
        //根据id获取hashtable数组下标
        int no=hash(id);
        Emp emp = empListArray[no].findById(id);
        System.out.println("查询的员工:"+emp);
    }

    /**
     * 根据员工id删除员工
     * @param id
     */
    public void deleteById(int id){
        //根据id获取hashtable数组下标
        int no=hash(id);
        Emp emp=empListArray[no].deleteById(id);
        System.out.println("删除的员工:"+emp);
    }

    /**
     * 根据员工id计算hash值，计算存放数组的下标
     * @param id  员工id
     * @return  返回数组下标
     */
    private int hash(int id){
        return id%maxSize;
    }
}

/**
 * 自定义链表
 */
class EmpLinkedList {
    private Emp head;

    /**
     * 添加
     *
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //临时变量指向当前对象
        Emp curEmp = head;
        while (true) {
            if (curEmp.getNext() == null) {
                curEmp.setNext(emp);
                break;
            }
            curEmp = curEmp.getNext();
        }
    }

    /**
     * 遍历打印链表
     * @param no  链表序号数字
     */
    public void list(int no){
        if (head==null){
            System.out.println("第"+no+"链表为空...");
            return;
        }
        System.out.printf("第"+no+"链表数据 ");
        //定义临时变量存储当前
        Emp curEmp=head;
        while (curEmp!=null){
            System.out.printf(" => id=%d,name=%s\t",curEmp.getId(),curEmp.getName());
            curEmp=curEmp.getNext();
        }
        System.out.println("");
    }

    /**
     * 根据id 查找
     * @param id  主键
     * @return  返回员工对象
     */
    public Emp findById(int id){
        if (head==null){
            return null;
        }
        //定义临时变量存储
        Emp curEmp=head;
        while (curEmp!=null){
            if (id==curEmp.getId()){
                break;
            }
            curEmp=curEmp.getNext();
        }
        return curEmp;
    }

    /**
     * 根据主键删除
     * @param id  主键
     * @return  返回要删除的员工
     */
    public Emp deleteById(int id){
        if (head==null){
            return null;
        }
        //定义临时变量存储
        Emp curEmp=head;
        //如果删除的是头节点
        if (curEmp.getId()==id){
            if (curEmp.getNext()!=null){
                head=curEmp.getNext();
            }else {
                head=null;
            }
            return curEmp;
        }
        //删除的员工不是头节点
        while (true){
            if (id==curEmp.getNext().getId()){  //找到id
                curEmp.setNext(curEmp.getNext().getNext());
                break;
            }
            curEmp=curEmp.getNext();
        }
        return curEmp;
    }
}

/**
 * 实体对象bean
 */
class Emp implements Serializable {

    private static final long serialVersionUID = 771506812046216341L;

    //主键id
    private int id;
    //名称
    private String name;
    //下一个对象
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
