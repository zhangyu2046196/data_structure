package com.youyuan.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangyu
 * @version 1.0
 * @description 图
 * <p>
 * 分类：
 * 1、无向图
 * 2、有向图
 * 3、带权图
 * <p>
 * 图的表示方式
 * 1、二维数组(邻接矩阵)
 * 2、链表(邻接表)
 * @date 2019/8/20 15:29
 */
public class Graph {

    //存储节点信息
    private List<String> vertexList = null;
    //定义一个数组标记节点是否被访问
    private boolean[] isVisite;
    //存储边
    private int[][] edges;
    //边的数量
    private int edgesCount;

    public static void main(String[] args) {
//        String[] strArray = new String[]{"A", "B", "C", "D", "E"};
        String[] strArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(strArray.length);
        //添加节点
        for (String s : strArray) {
            graph.insertVertex(s);
        }

        //添加边  A-B  A-C  B-C  B-D  B-E
//        graph.insertEdges(0, 1, 1);
//        graph.insertEdges(0, 2, 1);
//        graph.insertEdges(1, 2, 1);
//        graph.insertEdges(1, 3, 1);
//        graph.insertEdges(1, 4, 1);

        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);


        //遍历
        graph.show();
        //节点数
//        System.out.println(graph.getVertexSize());
        //边数量
//        System.out.println(graph.getEdgesSize());

        //图的深度优先遍历
        System.out.println("图的深度遍历算法");
        graph.dfs();
        System.out.println();
        System.out.println("图的广度遍历算法");
        graph.bfs();

    }

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        isVisite = new boolean[n];
    }

    /**
     * 根据当前节点下标 获取当前节点的第一个邻接节点下标
     *
     * @param index 当前节点下标
     * @return 返回邻接节点下标
     */
    public int getFirstVertex(int index) {
        for (int j = index; j < getVertexSize(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点获取下一个邻接节点
     *
     * @param v1 前一个节点的下标
     * @param v2 前一个节点的下标
     * @return 返回下一个邻接节点的下标
     */
    public int getNextVertex(int v1, int v2) {
        for (int j = v2 + 1; j < getVertexSize(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 图的深度优先遍历算法  DFS  回溯
     */
    public void dfs() {
        isVisite = new boolean[getVertexSize()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisite[i]) {
                dfs(isVisite, i);
            }
        }
    }

    /**
     * 图的深度优先遍历
     *
     * @param isVisite 是否被访问标记
     * @param i        当前节点下标
     */
    private void dfs(boolean[] isVisite, int i) {
        //打印当前节点
        System.out.print(getValueByIndex(i) + "->");
        //标记当前节点为已读
        isVisite[i] = true;
        //获取当前节点的第一个邻接节点下标
        int w = getFirstVertex(i);
        //循环遍历
        while (w != -1) {
            if (!isVisite[w]) {
                dfs(isVisite, w);
            }
            //获取前一个邻接节点的下一个邻接节点下标
            w = getNextVertex(i, w);
        }
    }

    /**
     * 图的广度遍历算法
     */
    public void bfs() {
        isVisite = new boolean[getVertexSize()];
        for (int i = 0; i < getVertexSize(); i++) {
            if (!isVisite[i]) {
                bfs(isVisite, i);
            }
        }
    }

    /**
     * 图的广度优先遍历算法  BFS
     *
     * @param isVisite 是否访问节点数组
     * @param i        当前节点下标
     */
    private void bfs(boolean[] isVisite, int i) {
        int u;//队列头节点
        int w;//节点u的第一个邻接节点
        //打印
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisite[i] = true;
        //创建链表，模拟队列，用链表的addLast、removeFirst方法
        LinkedList<Integer> queue = new LinkedList<Integer>();
        //当前节点放入队列
        queue.addLast(i);

        //遍历队列  针对一个元素 也就是二维数组的一行遍历
        while (!queue.isEmpty()) {
            //取出队列的头节点下标
            u = queue.removeFirst();
            //得到头节点的第一个邻接节点下标
            w = getFirstVertex(u);
            while (w != -1) {
                if (!isVisite[w]) {
                    //打印
                    System.out.print(getValueByIndex(w) + "->");
                    //标记已读
                    isVisite[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //得到前一个节点的下一个邻接节点  比如此时节点i 是A  需要找到  C
                w = getNextVertex(u, w);
            }
        }


    }

    /**
     * 添加节点信息
     *
     * @param vertexValue 节点名称信息
     */
    public void insertVertex(String vertexValue) {
        vertexList.add(vertexValue);
    }

    /**
     * 添加边
     *
     * @param v1     节点的下标，A->0  B->1
     * @param v2     节点的下标, A->0  B->1
     * @param weight 权重  1连接 0未连接
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgesCount++;
    }

    /**
     * 获取节点的数量
     *
     * @return 返回节点数量
     */
    public int getVertexSize() {
        return vertexList.size();
    }

    /**
     * 获取边的数量
     *
     * @return 返回边的数量
     */
    public int getEdgesSize() {
        return edgesCount;
    }

    /**
     * 根据下标获取节点
     *
     * @param index 下标
     * @return 返回节点信息
     */
    public String getValueByIndex(int index) {
        if (index < 0 || index >= getVertexSize()) {
            return "";
        }
        return vertexList.get(index);
    }

    /**
     * 遍历图
     */
    public void show() {
        for (int[] edge : edges) {
            System.err.println(Arrays.toString(edge));
        }
    }


}
