package class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/****
 * @Author: jerusalem
 * @Description: HashMapAndSortedMap
 * 哈希表和有序表
 * @Date 2023-04-06 19:56
 *****/
public class HashMapAndSortedMap {

    /***
     * 普通结点结构（模拟非基础数据类型，下面用得到）
     */
    public static class Node {
        public int value;

        public Node(int v) {
            value = v;
        }
    }

    public static void main(String[] args) {
        /***
         * 关于Java中哈希表的使用
         * 在使用哈希表时，增、删、改、查，时间复杂度均为O（1）
         * HashMap =》 key-value
         * HashSet =》 只有key，没有value
         * 有无值是HashMap和HashSet的唯一区别
         */
        System.out.println("关于哈希表测试开始：");

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "我是1");
        map.put(2, "我是2");
        map.put(3, "我是3");
        map.put(4, "我是4");
        map.put(5, "我是5");
        map.put(100000, "我是111111");
        System.out.println(map.containsKey(1));
        System.out.println(map.containsKey(10));
        System.out.println(map.get(100000));
        System.out.println(map.get(10));

        map.put(4, "他是4");
        System.out.println(map.get(4));

        map.remove(4);
        System.out.println(map.get(4));

        HashSet<String> set = new HashSet<>();
        set.add("abc");
        set.contains("abc");
        set.remove("abc");

        System.out.println("=====================");

        /***
         * 关于Java中的基本数据类型和引用数据类型
         * 基本数据类型 int double float     按值传递
         * 引用数据类型 Integer Double Float     按引用传递（但在哈希表中是按值传递）
         */
        System.out.println("关于数据类型测试开始：");

        int a = 100000;
        int b = 100000;
        System.out.println(a == b); //true，基本数据类型按值传递，仅比较值
        Integer c = 127; // -128 ~ 127
        Integer d = 127;
        Integer m = 128;
        Integer n = 128;
        System.out.println(c == d); //true，引用数据类型-128~127之间按值传递
        System.out.println(m == n); //false，超过上述范围，引用数据类型按引用传递
        System.out.println(c.equals(d)); //true，仅比较值
        System.out.println(m.equals(n)); //true，仅比较值

        /***
         * 在哈希表中存储东西
         * 基础类型的按值传递，内存占用是这个东西的大小
         * 非基础类型的按引用传递，即仅存储地址（8字节）
         */
        HashMap<Node, String> map2 = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = node1;
        map2.put(node1, "我是node1");
        map2.put(node2, "我是node1");
        System.out.println(map2.size());  //size=1，因为node1和node2指向同一块内存区域

        System.out.println("======================");

        /***
         * TreeMap 有序表：接口名
         * 有序表的基本功能和哈希表一样，
         * 优点是有序，缺点是O(logN)
         * 时间复杂度O(logN)，与其底层实现的结构有关 =》红黑树、avl、sb树、跳表
         */
        System.out.println("关于有序表测试开始：");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "我是3");
        treeMap.put(4, "我是4");
        treeMap.put(8, "我是8");
        treeMap.put(5, "我是5");
        treeMap.put(7, "我是7");
        treeMap.put(1, "我是1");
        treeMap.put(2, "我是2");
        System.out.println(treeMap.containsKey(1));
        System.out.println(treeMap.containsKey(10));
        System.out.println(treeMap.get(4));
        System.out.println(treeMap.get(10));

        treeMap.put(4, "他是4");
        System.out.println(treeMap.get(4));

        treeMap.remove(4);
        System.out.println(treeMap.get(4));

        System.out.println("与哈希表不同的是有序表会按照Key自动排序：");
        System.out.println(treeMap.firstKey()); //第一个Key
        System.out.println(treeMap.lastKey());  //最后一个Key
        System.out.println(treeMap.floorKey(4));  //<=4且离4最近
        System.out.println(treeMap.ceilingKey(4));  // >=4离4最近
    }
}
