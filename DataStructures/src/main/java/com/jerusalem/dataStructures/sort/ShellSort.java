package com.jerusalem.dataStructures.sort;

import javax.lang.model.element.Element;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/****
 * @Author: jerusalem
 * @Description: ShellSort
 * 希尔排序（缩小增量排序） //TODO 待理解
 * @Date 2020/10/18 15:31
 *****/
public class ShellSort {
    public static void main(String[] args) {
        //创建长度为80000的随机数组，测试希尔排序的速度
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

//        //交换方式
//        shellSort(arr);
        //移位方式
        shellSort2(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间为：" + date2Str);

        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));
    }

    /***
     * 希尔排序方法（交换方式）
     * 速度较慢
     * @param arr
     */
    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，步长gap）
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
    }

    /***
     * 希尔排序（移位方式）(改进)
     * 速度较快
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        // 增量gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
