package class01;

import java.util.Arrays;

/****
 * @Author: jerusalem
 * @Description: SelectionSort
 * 选择排序
 * @Date 2023-04-03 15:57
 *****/
public class SelectionSort {
    /***
     * 选择排序算法
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ N-1  找到最小值，在哪，放到0位置上
        // 1 ~ N-1  找到最小值，在哪，放到1位置上
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { // i ~ N-1 上找最小值的下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    /***
     * 交换操作（实用，不一定非得用异或运算）
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /***
     * 比较器（对数器用来做对比的方法B）
     * @param arr
     */
    public static void comparator(int[] arr) {
        //系统提供的排序方法，肯定正确
        Arrays.sort(arr);
    }

    /***
     * 随机数组生成器
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random()  等概率返回一个[0,1)中的小数
        // Math.random()*N  等概率返回一个[0,N)中的小数
        // (int)(Math.random()*N)  等概率返回一个[0, N-1]中的整数
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];//数组长度随机
        for (int i = 0; i < arr.length; i++) {
            //数组的值随机，做一个减法可以保证随机数的范围为[-X，+X]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /***
     * 数组复制
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /***
     * 数组比较
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数组打印
     * @param arr
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();//换行
    }

    /***
     * 对数器
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 500000;    //测试次数
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }
}
