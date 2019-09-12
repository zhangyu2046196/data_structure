package com.youyuan.sparesarray;

/**
 * @author zhangyu
 * @version 1.0
 * @description 稀疏数组测试例子信息
 * 背景：模拟棋盘的存盘和续盘功能接口  11*11的二维数组
 * @date 2019/7/22 15:27
 */
public class sparesarray {

    public static void main(String[] args) {
        //初始化原二维数组
        int[][] lessArr = initLessarr();
        //将原二维数组转为稀疏数组
        int[][] spareArr = lessArrTransSpraseArr(lessArr);
        //将稀疏数组转为二维数组
        sparseArrTransLessArr(spareArr);
    }

    /**
     * 稀疏数组转为二维数组
     * @param spareArr 稀疏数组信息
     */
    private static void sparseArrTransLessArr(int[][] spareArr) {
        //稀疏数组转为二维数组
        //1、根据稀疏数组的第一行初始化二维数组
        int[][] lessArr2=new int[spareArr[0][0]][spareArr[0][1]];
        //2、从第二行开始遍历稀疏数组，然后将元素插入二维数组
        for (int i=1;i<spareArr.length;i++){
            lessArr2[spareArr[i][0]][spareArr[i][1]]=spareArr[i][2];
        }
        //3、打印二维数组
        System.out.println("--------------原二维数组--------------");
        for (int[] rowData:lessArr2){  //根据行循环
            for (int data:rowData){
                System.out.printf("%d\t",data);  //输出行元素
            }
            System.out.println();  //换行
        }
    }

    /**
     * 二维数组转换为稀疏数组
     * @param lessArr 二维数组
     * @return
     */
    private static int[][] lessArrTransSpraseArr(int[][] lessArr) {
        //将二维数组转成稀疏数组
        //1、计算二维数组的行数和列数
        int row=lessArr.length;  //行数
        int col=lessArr[0].length;  //列数
        //2、计算二维数组有效元素个数  元素不等于0为有效元素
        int num = 0; //初始化元素个数
        //循环遍历二维数组
        for (int i=0;i<lessArr.length;i++){
            for (int j=0;j<lessArr[i].length;j++){
                if (lessArr[i][j]!=0){
                    num++;
                }
            }
        }
        //3、初始化稀疏数组和第一行赋值
        int[][] spareArr=new int[num+1][3];
        spareArr[0][0]=row;
        spareArr[0][1]=col;
        spareArr[0][2]=num;
        //4、遍历二维数组往稀疏数组插入有效元素
        //设置行的变量
        int rowNum=0;
        for (int i=0;i<lessArr.length;i++){
            for (int j=0;j<lessArr[i].length;j++){
                if (lessArr[i][j]!=0){
                    rowNum++;
                    spareArr[rowNum][0]=i;
                    spareArr[rowNum][1]=j;
                    spareArr[rowNum][2]=lessArr[i][j];
                }
            }
        }
        //5、打印稀疏数组
        System.out.println("--------------稀疏数组--------------");
        for (int[] rowData:spareArr){//根据行循环
            for (int data:rowData){
                System.out.printf("%d\t",data);  //输出元素信息
            }
            System.out.println();  //换行
        }
        return spareArr;
    }

    /**
     * 初始化二维数组
     * @return
     */
    private static int[][] initLessarr() {
        //初始化二维数组
        int[][] lessArr= new int[11][11];
        lessArr[1][2]=1;  //1代表黑子
        lessArr[2][3]=2;  //2代表蓝子
        lessArr[4][5]=2;
        //打印初始化的二维数组
        System.out.println("--------------初始化二维数组--------------");
        for (int[] rowData:lessArr){//根据行循环
            for (int data:rowData){//循环一行中的元素
                System.out.printf("%d\t",data);   //格式化
            }
            System.out.println(); //换行
        }
        return lessArr;
    }

}
