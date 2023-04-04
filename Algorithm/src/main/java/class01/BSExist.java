package class01;

import java.util.Arrays;

/****
 * @Author: jerusalem
 * @Description: BSExist
 * 二分法查找某个数是否存在
 * @Date 2023-04-04 15:37
 *****/
public class BSExist {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) { // L..R 至少两个数的时候
            //mid = L + ((R - L) >> 1) 等价于 mid = (L + R)/2 这种写法当L、R很大时，有越界的可能
            //优化一：mid = L + (R - L)/2 =》 可以理解为L加上L、R距离的一半，这样就不会有越界的可能（因为是大数-小数）
            //又因为，N/2 在二进制运算中等价于 N >> 1 （N带符号右移一位），这就有了下面的版本
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }

    /***
     * 比较器：普通的查找某个数方法（遍历）
     * @param sortedArr
     * @param num
     * @return
     */
    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
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
     * 对数器
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);//先排序
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
