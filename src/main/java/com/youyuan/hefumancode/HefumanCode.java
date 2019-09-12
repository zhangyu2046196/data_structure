package com.youyuan.hefumancode;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

/**
 * @author zhangyu
 * @version 1.0
 * @description 赫夫曼编码类
 * <p>
 * 思路：
 * 1.把需要编码的字符串转成byte数组
 * 2.把byte数组遍历放入map中统计每个byte值对应的出现次数
 * 3.创建node类，包含data和weight属性，data存储byte值，weight存储出现的次数  实现Comparable接口，重写ComparseTo接口用于排序
 * 4.遍历map,每个k-v生成一个node放到list中
 * 5.遍历list ,去除list.get(0)和list.get(1),取出两个node的weight加在一起生成新的Node,新的node的leftNode指向get(0),rightNode指向get(1)
 * 6.然后从list中删除 leftNode 和 rightNode
 * 7.把新生成的Node放入list 在重复执行上面循环list操作(5 6 7)步骤
 * 8.最后list只剩下一个Node，就是生成的赫夫曼树
 * @date 2019/8/12 16:25
 */
public class HefumanCode {

    //存储字节对应的编码  例如:key:91 value:100   key是字节  value是编码，赫夫曼树遍历时编码规则是向左是0 向右是1
    private static Map<Byte, String> hefumanMap = new HashMap<Byte, String>();
    //存储追加的编码
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        //把原始字符串转成字节数组
//        byte[] bytes = str.getBytes();
//        System.out.println("bytes:" + bytes);
//        //通过字节数组构建node并放入list
//        List<Node> nodes = buildList(bytes);
//        System.out.println("nodes:" + nodes);
//        //构建赫夫曼树
//        Node root = buildHefumanTree(nodes);
//        //   打印赫夫曼树
//        root.preOrder();
//
//        //遍历赫夫曼树把字节转成编码
//        Map<Byte, String> codeMap = getCodes(root);
//        System.out.println("codeMap:" + codeMap);
//
//        zip(bytes);

        //获取赫夫曼编码字节数组
//        byte[] bytes = hefumanEncode(str);
//
//        //获取赫夫曼解码的原始字符串
//        String resource = hefumanDecode(bytes, hefumanMap);  //hefumanMap 赫夫曼编码表在编码的时候给成员变量赋值
//
//        System.out.println("解码后的字符串：" + resource);

        //测试赫夫曼编码压缩文件
        String srcFile = "E:\\word.txt";
        String dstFile = "E:\\word.zip";

        zipFile(srcFile, dstFile);
        System.out.println("压缩完成...");
//
//        String zipFile = "E:\\sport_car.zip";
//        String dstFile = "E:\\sport_car2.jpg";
//
//        unZipFile(zipFile, dstFile);
//        System.out.println("解压完成...");

    }

    /**
     * 原始字符串 获取每个字节在字符串中出现次数，拼装成node放入list
     *
     * @param bytes 字节数组
     * @return 返回拼装好的list
     */
    public static List<Node> buildList(byte[] bytes) {
        //存储字节转成的Node
        List<Node> nodes = new ArrayList<Node>();
        //统计byte在字符中出现的次数 通过遍历字节数组然后放入map中统计
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (Byte aByte : bytes) {
            Integer count = map.get(aByte);
            if (count == null) {
                map.put(aByte, 1);
            } else {
                map.put(aByte, count + 1);
            }
        }

        //循环遍历map将字节信息存储到Node放到List
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    /**
     * 赫夫曼编码方式压缩文件
     *
     * @param srcFile 原始文件全路径
     * @param dstFile 压缩文件存储全路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        //定义文件时输入流
        FileInputStream fis = null;
        //定义文件输出流
        FileOutputStream fos = null;
        //定义对象输出流   把压缩后的文件字节码数组以对象的方式写入到压缩文件中
        ObjectOutputStream oos = null;
        try {
            if (StringUtils.isEmpty(srcFile)) {
                return;
            }
            //创建文件输入流
            fis = new FileInputStream(srcFile);
            //创建字节数组用于接收输入文件  字节数组长度就是文件长度
            byte[] bytes = new byte[fis.available()];
            //输入流读取文件到byte数组
            fis.read(bytes);
            //后去压缩后的赫夫曼编码
            byte[] hefumanBytes = hefumanEncodeZipFile(bytes);
            //创建文件输出流
            fos = new FileOutputStream(dstFile);
            //创建对象输出流  把压缩后的文件以对象方式输出
            oos = new ObjectOutputStream(fos);
            //把压缩后的赫夫曼编码字节数组以对象的方式写入到压缩文件
            oos.writeObject(hefumanBytes);
            //把赫夫曼编码表写入到压缩文件  用于解压缩使用
            oos.writeObject(hefumanMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                fos.close();
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 赫夫曼解压文件
     *
     * @param zipFile 压缩文件全路径名
     * @param dstFile 解压保存的文件全路径名
     */
    public static void unZipFile(String zipFile, String dstFile) {
        if (StringUtils.isEmpty(zipFile)) {
            return;
        }
        //创建文件输入流
        FileInputStream fis = null;
        //创建对象输入流 用于读取压缩文件对象
        ObjectInputStream ois = null;
        //创建文件输出流
        FileOutputStream fos = null;
        try {
            //创建文件输入流
            fis = new FileInputStream(zipFile);
            //创建对象输入流  读取压缩文件对象
            ois = new ObjectInputStream(fis);
            //读取压缩文件的字节码数组    压缩的时候把字节码数组文件作为对象写入到了压缩文件中
            byte[] hefumanCodeByte = (byte[]) ois.readObject();
            //读取赫夫曼编码表  压缩的时候把赫夫曼编码表作为对象写入到了压缩文件中 读取顺序和写入顺序要一致
            Map<Byte, String> hefumanCodeMap = (Map<Byte, String>) ois.readObject();
            //调用解压接口得到赫夫曼解压后的字节数组
            byte[] hefumanDecode = hefumanDecodeToByte(hefumanCodeByte, hefumanCodeMap);
            //创建文件输入流, 把解压的字节码数组写入文件
            fos = new FileOutputStream(dstFile);
            fos.write(hefumanDecode);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                fos.close();
                ois.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 获取赫夫曼编码(压缩)
     *
     * @param resource 原始字符串
     * @return 返回赫夫曼编码字节数组
     */
    public static byte[] hefumanEncode(String resource) {
        if (StringUtils.isEmpty(resource)) {
            return null;
        }
        //1.把原始字符串的值和在字符串中出现次数作为节点Node的data和weight,构建List
        List<Node> listNode = buildList(resource.getBytes());
        //2.构建赫夫曼树
        Node root = buildHefumanTree(listNode);
        //3.创建赫夫曼编码表,存储全局变量hefumanMap
        getCodes(root);
        //4.构建赫夫曼编码字节数组
        byte[] bytes = zip(resource.getBytes());
        return bytes;
    }

    /**
     * 获取赫夫曼编码(压缩)
     *
     * @param bytes 原始字节数组
     * @return 返回赫夫曼编码字节数组
     */
    public static byte[] hefumanEncodeZipFile(byte[] bytes) {
        //1.把原始字符串的值和在字符串中出现次数作为节点Node的data和weight,构建List
        List<Node> listNode = buildList(bytes);
        //2.构建赫夫曼树
        Node root = buildHefumanTree(listNode);
        //3.创建赫夫曼编码表,存储全局变量hefumanMap
        getCodes(root);
        //4.构建赫夫曼编码字节数组
        return zip(bytes);
    }

    /**
     * 赫夫曼解码
     *
     * @param hefumanBytes 赫夫曼编码字节数组
     * @param hefumanMap   赫夫曼编码表
     * @return 返回解码字符串
     */
    public static String hefumanDecode(byte[] hefumanBytes, Map<Byte, String> hefumanMap) {
        StringBuilder stringBuilder = new StringBuilder();  //拼接赫夫曼编码字节数组对应的二进制字符串
        //1.遍历赫夫曼编码字节数组，转成二进制字符串
        for (int i = 0; i < hefumanBytes.length; i++) {
            boolean flag = (i == hefumanBytes.length - 1);  //true为最后一个字节
            stringBuilder.append(byteToBitString(!flag, hefumanBytes[i]));
        }
        System.out.println("赫夫曼解码中编码的字节数组对应的二进制字符串：" + stringBuilder.toString());
        //赫夫曼编码表反转  反转后是key:编码  value:字节
        Map<String, Byte> transactionMap = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : hefumanMap.entrySet()) {
            transactionMap.put(entry.getValue(), entry.getKey());
        }
        //存储解码后的字节
        List<Byte> transCodeList = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 0;  //标记取几个字符，因为要把取出来的字符串去transactionByte匹配
            boolean flag = true;  //true 循环  false退出
            while (flag) {
                if (transactionMap.get(stringBuilder.substring(i, i + count)) != null) {  //在编码表中存在
                    transCodeList.add(transactionMap.get(stringBuilder.substring(i, i + count)));
                    flag = false;
                } else {  //编码表中不存在
                    count++;
                }
            }
            i += count;
        }

        //存储解码后的字节
        byte[] resourceBytes = new byte[transCodeList.size()];
        for (int i = 0; i < transCodeList.size(); i++) {
            resourceBytes[i] = transCodeList.get(i);
        }
        return new String(resourceBytes);
    }

    /**
     * 赫夫曼解码
     *
     * @param hefumanBytes 赫夫曼编码字节数组
     * @param hefumanMap   赫夫曼编码表
     * @return 返回解码后的字节数组
     */
    public static byte[] hefumanDecodeToByte(byte[] hefumanBytes, Map<Byte, String> hefumanMap) {
        StringBuilder stringBuilder = new StringBuilder();  //拼接赫夫曼编码字节数组对应的二进制字符串
        //1.遍历赫夫曼编码字节数组，转成二进制字符串
        for (int i = 0; i < hefumanBytes.length; i++) {
            boolean flag = (i == hefumanBytes.length - 1);  //true为最后一个字节
            stringBuilder.append(byteToBitString(!flag, hefumanBytes[i]));
        }
        //System.out.println("赫夫曼解码中编码的字节数组对应的二进制字符串：" + stringBuilder.toString());
        //赫夫曼编码表反转  反转后是key:编码  value:字节
        Map<String, Byte> transactionMap = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : hefumanMap.entrySet()) {
            transactionMap.put(entry.getValue(), entry.getKey());
        }
        //存储解码后的字节
        List<Byte> transCodeList = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 0;  //标记取几个字符，因为要把取出来的字符串去transactionByte匹配
            boolean flag = true;  //true 循环  false退出
            while (flag) {
                if (i + count < stringBuilder.length()) {
                    if (transactionMap.get(stringBuilder.substring(i, i + count)) != null) {  //在编码表中存在
                        transCodeList.add(transactionMap.get(stringBuilder.substring(i, i + count)));
                        flag = false;
                    } else {  //编码表中不存在
                        count++;
                    }
                } else {
                    flag = false;
                }
            }
            i += count;
        }

        //存储解码后的字节
        byte[] resourceBytes = new byte[transCodeList.size()];
        for (int i = 0; i < transCodeList.size(); i++) {
            resourceBytes[i] = transCodeList.get(i);
        }
        return resourceBytes;
    }

    /**
     * 根据赫夫曼编码表生成赫夫曼编码字符串，根据字符串生成编码字节数组  一个字节8位
     * <p>
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     *
     * @param resByte 原始字符串对应的字节数组
     * @return 返回赫夫曼编码字节数组
     */
    private static byte[] zip(byte[] resByte) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : resByte) {
            stringBuilder.append(hefumanMap.get(b));
        }
        String hefumanCodeStr = stringBuilder.toString();
        System.out.println("生成的赫夫曼编码字符串是:" + hefumanCodeStr);
        //根据生成的赫夫曼编码字符串转成字节数组，
        int len;  //字节数组长度
        if (hefumanCodeStr.length() % 8 == 0) {  //因为一个字节存储8位
            len = hefumanCodeStr.length() / 8;
        } else {
            len = hefumanCodeStr.length() / 8 + 1;
        }
        //创建字节数组
        byte[] bytes = new byte[len];

        //截取赫夫曼编码字符串放入字节数组
        int index = 0;  //字节数组下标
        for (int i = 0; i < hefumanCodeStr.length(); i += 8) {
            if ((i + 8) < hefumanCodeStr.length()) {
                bytes[index] = (byte) Integer.parseInt(hefumanCodeStr.substring(i, i + 8), 2);  //十进制转成二进制
            } else {
                bytes[index] = (byte) Integer.parseInt(hefumanCodeStr.substring(i), 2);  //十进制转二进制
            }
            index++;
        }
        System.out.println("赫夫曼编码字符串转成的字节数组:" + Arrays.toString(bytes));
        return bytes;
    }

    /**
     * 构建赫夫曼树
     *
     * @param nodes 原始字节转成的node集合
     * @return 返回赫夫曼树
     */
    public static Node buildHefumanTree(List<Node> nodes) {
        // 因为最后list只会剩下赫夫曼树的根节点，所以根据list集合中节点数量判断
        while (nodes.size() > 1) {
            //从小到大排序list集合
            Collections.sort(nodes);
            //获取第一个节点为左节点
            Node leftNode = nodes.get(0);
            //获取第二个节点为右节点
            Node rightNode = nodes.get(1);
            //生成新的父节点
            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeftNode(leftNode);
            parent.setRightNode(rightNode);
            //删除上面去除的leftNode和RightNode
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把新生成节点放入list
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 获取赫夫曼编码表
     *
     * @param root 赫夫曼树根节点
     * @return 返回赫夫曼编码表
     */
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.getLeftNode(), "0", stringBuilder);
        //处理root的右子树
        getCodes(root.getRightNode(), "1", stringBuilder);
        return hefumanMap;
    }

    /**
     * 把字节转成二进制字符串
     *
     * @param flag true代表需要补高位  false代表不需要补高位  如果是最后一个字节，无需补高位
     * @param b    字节
     * @return 返回b对应的二进制字符串
     */
    private static String byteToBitString(boolean flag, byte b) {
        String res = "";
        int temp = b;  //转成int 因为Integer有方法将int转成二进制字符串
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        res = Integer.toBinaryString(temp);
        if (flag) {
            return res.substring(res.length() - 8);
        } else {
            return res;
        }
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { //如果node == null不处理
            //判断当前node 是叶子结点还是非叶子结点
            if (node.getData() == null) { //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.getLeftNode(), "0", stringBuilder2);
                //向右递归
                getCodes(node.getRightNode(), "1", stringBuilder2);
            } else { //说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                hefumanMap.put(node.getData(), stringBuilder2.toString());
            }
        }
    }

    /**
     * 前序遍历节点
     *
     * @param root 根节点
     */
    private static void preOrder(Node root) {
        root.preOrder();
    }

}

/**
 * 节点
 */
class Node implements Serializable, Comparable<Node> {

    private static final long serialVersionUID = -4260403442941627064L;

    //byte值
    private Byte data;
    //权重  就是字节在原始串中出现的次数
    private Integer weight;
    //左节点
    private Node leftNode;
    //右节点
    private Node rightNode;

    public Node(Byte data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * 前序遍历节点
     */
    public void preOrder() {
        System.out.println(this);
        //左子树
        if (this.getLeftNode() != null) {
            this.getLeftNode().preOrder();
        }
        //右子树
        if (this.getRightNode() != null) {
            this.getRightNode().preOrder();
        }
    }

    /**
     * 比较 排序用到的
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.getWeight() - o.getWeight();
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
