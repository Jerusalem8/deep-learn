package class02;

/****
 * @Author: jerusalem
 * @Description: GetMax
 * 获取最大值（递归）
 * @Date 2023-04-06 17:44
 *****/
public class GetMax {
    /***
     * 利用递归获取数组中的最大值
     * 主程序
     * @param arr
     * @return
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    /***
     * 递归方法
     * @param arr
     * @param L
     * @param R
     * @return
     */
    // arr[L..R]范围上求最大值
    public static int process(int[] arr, int L, int R) {
        // arr[L..R]范围上只有一个数，直接返回
        if (L == R) {      //递归出口
            return arr[L];
        }
        // L...R 不只一个数
        // mid = (L + R) / 2
        int mid = L + ((R - L) >> 1);   //中点
        int leftMax = process(arr, L, mid);     //左部分最大值
        int rightMax = process(arr, mid + 1, R);    //右部分最大值
        return Math.max(leftMax, rightMax);
    }
}
