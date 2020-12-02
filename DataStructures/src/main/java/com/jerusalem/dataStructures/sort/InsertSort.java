package com.jerusalem.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/****
 * @Author: jerusalem
 * @Description: InsertSort
 * 插入排序
 * @Date 2020/10/17 17:06
 *****/
public class InsertSort {
    public static void main(String[] args) {
        //创建长度为80000的随机数组，测试插入排序的速度
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        System.out.println("排序前的数组：");
        System.out.println(Arrays.toString(arr));

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间为：" + date1Str);

        insertSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间为：" + date2Str);

        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));
    }

    /***
     * 直接插入排序算法
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for(int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;
            // 说明
            // 1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 退出while循环时，说明找到插入位置, insertIndex + 1
            if(insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第"+i+"轮插入后的情况：");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
