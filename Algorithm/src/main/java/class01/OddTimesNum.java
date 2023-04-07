package class01;

/****
 * @Author: jerusalem
 * @Description: printOddTimesNum
 * 寻找数组中出现奇数次的数字并打印
 * @Date 2023-04-04 20:18
 *****/
public class OddTimesNum {

    /***
     * 寻找数组中出现奇数次的数字（一个）并打印
     * @param arr
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0 ;
        for (int i = 0; i < arr.length; i++){
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /***
     * 寻找数组中出现奇数次的数字（两个）并打印
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++){
            eor ^= arr[i];
        }
        //异或操作之后，剩下的一定为那两个出现奇数次的数字a和b，最终eor = a ^ b
        //eor != 0这是一定的，所以eor必然有一个位置为1，a和b在这个位置上一个为0，一个为1，则可以利用这个区别进行分流
        int rightOne = eor & (~eor + 1); //这就需要用到异或操作的另一个应用，提取整型数字最右的1
        int onlyOne = 0;    //eor`
        for (int i = 0; i < arr.length; i++){
            //见笔记1中&(与)运算的规则，就能明白下面这个判断条件
            if ((arr[i] & rightOne) != 0){
                //将所有在那个位置上为1的数异或起来
                onlyOne ^= arr[i];
            }
        }
        //打印a和b
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    /***
     * 计算一个整型数字的二进制中1的个数
     * @param n
     */
    public static int bitOneCounts(int n) {
        int count = 0;
        while (n != 0){
            int rightOne = n & ((~n) + 1);  //提取最右的1
            count++;    //每提取出一个就加1
            //再把最右的1抹掉
            n ^= rightOne;  //负数只能异或
        }
        return count;
    }

    /***
     * 测试主程序
     * @param args
     */
    public static void main(String[] args) {
        int[] arr1 = {7,9,2,9,8,2,1,7,1,3,8,2,2,3,8,8,3};
        int[] arr2 = {7,9,2,9,8,2,1,7,3,8,2,2,3,8,8,3};
        printOddTimesNum1(arr1);
        printOddTimesNum2(arr2);

        int a = 15;
        int counts = bitOneCounts(a);
        System.out.println(counts);
    }
}
