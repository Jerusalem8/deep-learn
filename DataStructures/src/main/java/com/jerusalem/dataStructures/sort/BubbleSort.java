package com.jerusalem.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/****
 * @Author: jerusalem
 * @Description: BubbleSort
 * 冒泡排序
 * @Date 2020/10/17 15:04
 *****/
public class BubbleSort {
    public static void main(String[] args) {
        //创建长度为80000的随机数组，测试冒泡排序的速度
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

        bubbleSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间为：" + date2Str);

        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));
    }

    /***
     * 冒泡排序算法
     * 时间复杂度 O(n^2)
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println("第" + (i + 1) + "趟排序后的数组为：");
            //System.out.println(Arrays.toString(arr));
            if (!flag) { // 一趟排序中未发生过交换，表明此时已为有序，跳出循环，排序完成
                break;
            } else {
                flag = false; // 重置flag, 进行下次判断
            }
        }
    }
}
