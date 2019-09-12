package com.youyuan.recorsion;

/**
 * @author zhangyu
 * @version 1.0
 * @description  递归应用场景 迷宫的回溯算法
 *
 * 思路：
 * 用二维数组模拟迷宫
 * 1、map表示地图
 * 2、i j 表示地图的横从坐标  表示从哪个位置开始
 * 3、如果小球能到map[6][5] 位置表示通路找到
 * 4、约定： 当map[i][j]=0代表没有走过  1代表墙或挡板不能走 2代表走过路是通的  3代表路走过不通
 * 5、策略： 走的顺序是下=>右=>上=>左
 *
 * @date 2019/7/30 21:38
 */
public class MiGong {

    public static void main(String[] args) {
        //定义map  8行 7列的二维数组
        int[][] map=new int[8][7];
        //初始化墙和挡板
        //最上最下两行设置墙
        for (int i=0;i<map[0].length;i++){
            map[0][i]=1;
            map[7][i]=1;
        }

        //设置最左最右两列为
        for (int i=0;i<map[0].length;i++){
            map[i][0]=1;
            map[i][6]=1;
        }

        //设置挡板
        map[3][1]=1;
        map[3][2]=1;

        System.out.println("迷宫初始化图...");
        for (int i=0;i<map.length;i++){
            for (int j=0;j<map[i].length;j++){
                System.out.printf(map[i][j]+" ");
            }
            System.out.println("");
        }

        int i=1;  //开始坐标位置
        int j=1;  //开始坐标位置
        System.out.printf("开始走迷宫,开始位置map[%d][%d]",i,j);

//        setWay(map,i,j);  //走路顺序 下=>右=>上=>左

        setWay2(map,i,j);  //走路顺序  上=>右=>下=>左

        System.out.println("迷宫走完路径图..");
        for (int a=0;a<map.length;a++){
            for (int b=0;b<map[a].length;b++){
                System.out.printf(map[a][b]+" ");
            }
            System.out.println("");
        }

    }

    /**
     * 通过递归实现迷宫功能
     * @param map  初始化迷宫 二维数组
     * @param i  开始横坐标位置
     * @param j  开始从坐标位置
     * @return  true 能走通  false 不能走通
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){  //走到终点
            return true;
        }else {
            //当map[i][j]=0代表没有走过  1代表墙或挡板不能走 2代表走过路是通的  3代表路走过不通
            //走的顺序是下=>右=>上=>左
            if (map[i][j]==0){  //如果当前位置是0代表没有走过能走
                map[i][j]=2;
                if (setWay(map,i+1,j)){  //向下走
                    return true;
                }else if (setWay(map,i,j+1)){  //向右走
                    return true;
                }else if (setWay(map,i-1,j)){  //向上走
                    return true;
                }else if (setWay(map,i,j-1)){  //向左走
                    return true;
                }else {  //都走不通设置为3
                    map[i][j]=3;
                }
            }
        }
        return false;
    }

    /**
     * 通过递归实现迷宫功能
     * @param map  初始化迷宫 二维数组
     * @param i  开始横坐标位置
     * @param j  开始从坐标位置
     * @return  true 能走通  false 不能走通
     *
     * 修改走路顺序策略  上=>右=>下=>左
     *
     */
    public static boolean setWay2(int[][] map,int i,int j){
        if (map[6][5]==2){  //走到终点
            return true;
        }else {
            //当map[i][j]=0代表没有走过  1代表墙或挡板不能走 2代表走过路是通的  3代表路走过不通
            if (map[i][j]==0){  //如果当前位置是0代表没有走过能走
                map[i][j]=2;
                if (setWay2(map,i-1,j)){  //向上走
                    return true;
                }else if (setWay2(map,i,j+1)){  //向右走
                    return true;
                }else if (setWay2(map,i+1,j)){  //向下走
                    return true;
                }else if (setWay2(map,i,j-1)){  //向左走
                    return true;
                }else {  //都走不通设置为3
                    map[i][j]=3;
                }
            }
        }
        return false;
    }

}
