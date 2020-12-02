package com.jerusalem.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/****
 * @Author: jerusalem
 * @Description: SelectSort
 * 选择排序
 * @Date 2020/10/17 16:10
 *****/
public class SelectSort {
    public static void main(String[] args) {
        //创建长度为80000的随机数组，测试选择排序的速度
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

        selectSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间为：" + date2Str);

        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));
    }

    /***
     * 选择排序算法
     * 时间复杂度 O(n^2)
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值，并不是最小
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex
                }
            }
            // 将最小值，放在arr[i], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第"+(i+1)+"轮后的情况");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
