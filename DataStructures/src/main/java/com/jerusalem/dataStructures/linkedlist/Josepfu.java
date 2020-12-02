package com.jerusalem.dataStructures.linkedlist;

/****
 * @Author: jerusalem
 * @Description: Josepfu
 * 约瑟夫环问题
 * @Date 2020/10/11 13:32
 *****/
public class Josepfu {
    public static void main(String[] args) {
        // 构建环形链表，添加节点，并遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        //测试出圈
        circleSingleLinkedList.countBoy(1, 2, 5); // 2->4->1->5->3
    }
}

/***
 * 创建单向环形链表
 * 添加节点
 * 遍历
 * 计算出圈顺序
 */
class CircleSingleLinkedList {
    // 创建first节点,当前没有编号
    private Boy first = null;

    /***
     * 添加小孩节点，构建成一个环形的链表
     * @param nums
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);//当前节点指向新节点
                boy.setNext(first);//新节点指向第一个节点，形成环
                curBoy = boy;//当前节点后移
            }
        }
    }

    /***
     * 遍历当前的环形链表
     */
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为first不能动，使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {// 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo
     * 表示从第几个小孩开始数数
     * @param countNum
     * 表示数几下
     * @param nums
     * 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        Boy helper = first;
        // 需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次，分别移动到开始报数的节点及其前一个节点
        for(int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩报数时，first和helper指针同时移动m-1次, 然后出圈，直到圈中只有一个节点
        while(true) {
            if(helper == first) { //说明圈中只有一个节点
                break;
            }
            //first和helper指针同时移动countNum-1
            for(int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}

/***
 * 创建一个Boy类，表示一个节点
 */
class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}