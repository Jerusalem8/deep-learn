package sort;

import java.util.Arrays;

/****
 * @Author: jerusalem
 * @Description: BubbleSort
 * 冒泡排序
 * @Date 2023-04-03 16:15
 *****/
public class BubbleSort {
    /***
     * 冒泡排序算法
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //相邻位置两两比较
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    /***
     * ★利用异或运算交换两个数的值（性质：满足交换律和结合律）★
     * 例 a = 甲，b = 乙
     * 然后执行如下三行代码：
     * （1）a = a ^ b;
     * （2）b = a ^ b;
     * （3）a = a ^ b;
     * 代码分析：
     * （1）行代码执行完毕后，a = 甲^乙，b = 乙；
     * （2）行代码执行完毕后，a = 甲^乙，b = 甲^乙^乙 = 甲^0 = 甲；（两个相同的数异或结果为0；任何数与0异或结果不变）
     * （3）行代码执行完毕后，a = 甲^乙^甲 = 甲^甲^乙 = 0^乙 = 乙，b = 甲，至此两个数交换完成。
     * 需要注意的是，i和j是一个位置的话，会出错，数字会变成0，因为是一块内存区域；两个数可以相等，但不能是一个数
     * 也就是说，需要两块内存空间才能实现交换
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];//（1）
        arr[j] = arr[i] ^ arr[j];//（2）
        arr[i] = arr[i] ^ arr[j];//（3）
    }

    /***
     * 测试利用异或运算交换两个数的值
     * @param args
     */
//    public static void main(String[] args) {
//        int a = 88;
//        int b = -10;
//
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//
//        System.out.println(a);
//        System.out.println(b);
//    }

    /***
     * 比较器：工具包提供的排序算法
     * @param arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /***
     * 随机数组生成器
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
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

    /***
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
        System.out.println();
    }

    /***
     * 对数器
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
