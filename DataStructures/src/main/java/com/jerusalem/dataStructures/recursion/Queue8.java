package com.jerusalem.dataStructures.recursion;

/****
 * @Author: jerusalem
 * @Description: Queue8
 * 八皇后问题（回溯）
 * @Date 2020/10/15 15:31
 *****/
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果，arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.println();
        System.out.printf("一共判断冲突的次数%d次", judgeCount);
    }

    /***
     * 放置第n个皇后
     * 特别注意： check 是 每一次递归时，进入到check中都有 for(int i = 0; i < max; i++)，因此会有回溯
     * @param n
     */
    private void check(int n) {
        if(n == max) {  //n = 8 , 8个皇后已经放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)) { // 不冲突
                //接着放n+1个皇后,即开始递归
                check(n+1); //
            }
            //如果冲突，就继续执行 array[n] = i; 即将第n个皇后，放置在本行后移的一个位置
        }
    }

    /**
     * 判断该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     *  说明
     *   1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
     *   2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for(int i = 0; i < n; i++) {
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i]) ) {
                return false;
            }
        }
        return true;
    }

    /***
     * 打印输出皇后摆放的位置
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}