package sort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/****
 * @Author: jerusalem
 * @Description: QuickSort
 * 快速排序
 * @Date 2023-04-07 17:37
 *****/
public class QuickSort {

    /***
     * 荷兰国旗问题（快速排序的算法思想）
     * 本算法以 arr[R]作为基准值
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1;   //小于区右边界
        int more = R;   //大于区左边界
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[] { less + 1, more };
    }

    /***
     * 交换操作
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
     * 快速排序（递归）
     * 时间复杂度 O(N*logN)
     * 通过分析可知：
     * （1）划分值越靠近中间，性能越好；越靠近两边，性能越差；
     * （2）随机选一个数进行划分的目的是让好情况和差情况都变成概率事件
     * （3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
     * （4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望。
     * Q：为什么不是 O（N^2），时间复杂度不是取最差情况吗？
     * A：因为快速排序不是像插入排序那样（最差情况每次必命中），而快速排序最差情况只是一个概率事件。（？？？其实还是不懂）
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    /***
     * 快速排序非递归版本需要的辅助类
     * 记录待处理的的排序的范围
     */
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    /***
     * 快速排序（非递归版本，用栈实现）
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop(); // op.l ... op.r
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }

    /***
     * 快速排序（非递归版本，用队列实现）
     * @param arr
     */
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Queue<Op> queue = new LinkedList<>();
        queue.offer(new Op(0, el - 1));
        queue.offer(new Op(er + 1, N - 1));
        while (!queue.isEmpty()) {
            Op op = queue.poll();
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                queue.offer(new Op(op.l, el - 1));
                queue.offer(new Op(er + 1, op.r));
            }
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
     * 对数器主程序
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }
}
