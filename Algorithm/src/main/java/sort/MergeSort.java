package sort;

/****
 * @Author: jerusalem
 * @Description: MergeSort
 * 归并排序
 * @Date 2023-04-07 14:34
 *****/
public class MergeSort {

    /***
     * 归并排序（递归）
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    /***
     * 归并排序算法核心部分（递归）
     * 时间复杂度分析：
     * T(N) = 2 * T(N/2) + O(N)     merge方法的时间复杂度为O(N)
     * 根据公式，归并排序时间复杂度为O(N*logN)
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr, int L, int R) {
        if (L == R) { //递归出口
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    /***
     * 归并排序：合并两个有序的部分
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            //左右两部分都不越界时，谁小copy谁，放到辅助数组中
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界，要么p2越界
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    /***
     * 归并排序（非递归实现）
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长（逐步倍增）
        int mergeSize = 1;  //当前有序的左组长度
        while (mergeSize < N) {
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {     //L：左组左边界
                if (mergeSize >= N - L) { //不够左组，直接退出，因为肯定剩下的部分有序，都是merge过的
                    break;
                }
                int M = L + mergeSize - 1;  //M：左组右边界
                int R = M + Math.min(mergeSize, N - M - 1); //R：右组右边界；右组最后可能出现不够步长的情况
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 功能上没有实质性作用，但可以防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;    //步长乘2
        }
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
     * 数组是否相等
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
     * 对数器主程序
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
