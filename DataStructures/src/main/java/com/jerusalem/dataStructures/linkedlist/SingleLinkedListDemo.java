package com.jerusalem.dataStructures.linkedlist;

import java.util.Stack;

/****
 * @Author: jerusalem
 * @Description: SingleLinkedList
 * 单链表（测试样例为水浒英雄）
 * @Date 2020/10/6 21:14
 *****/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //将节点加入链表(直接添加到链表尾部)
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        //将节点加入链表（按照编号的顺序）
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //显示链表
        singleLinkedList.list();

		//修改节点的信息
//		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~");
//		singleLinkedList.update(newHeroNode);
//		System.out.println("修改后的链表：");
//		singleLinkedList.list();

//		//删除节点
//		singleLinkedList.del(1);
//		singleLinkedList.del(4);
//		System.out.println("删除后的链表：");
//		singleLinkedList.list();

		//单链表中有效节点的个数
		System.out.println("有效的节点个数 = " + getLength(singleLinkedList.getHead()));

		//查找单链表中的倒数第k个结点
		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
		System.out.println("result = " + res);

        //单链表的反转功能
        System.out.println("反转单链表：");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();

        //逆序打印单链表
        System.out.println("逆序打印单链表（不改变链表的结构）：");
        reversePrint(singleLinkedList.getHead());
    }

    /***
     * 获取到单链表有效节点的个数(如果是带头结点的链表，需求不统计头节点)
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if(head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    /***
     * 查找单链表中的倒数第k个结点【新浪面试题】
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if(head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历到 size-index 位置，即倒数的第K个节点
        //先做一个index的校验
        if(index <=0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for(int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /***
     * 单链表的反转【腾讯面试题】【偏难】
     * @param head
     */
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null) {
            return ;
        }
        //定义一个辅助的指针(变量)，遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while(cur != null) {
            //TODO 疑难点
            next = cur.next;//暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    /***
     * 逆序打印单链表【百度】
     * 方式一：先反转单链表，然后打印（见腾讯面试题）（此方法会破坏单链表结构，不建议）
     * 方式二：利用栈的数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现逆序打印的效果（如下）
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return;
        }
        //创建栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while(cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移
        }
        //将栈中的节点 pop 出栈，并进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

/***
 * 定义SingleLinkedList 管理我们的英雄
 * 1.添加节点
 * 2.修改节点
 * 3.删除节点
 */
class SingleLinkedList {
    //初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    /***
     * 方法一：添加节点到单向链表（直接添加到链表尾部，不考虑编号顺序）
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true) {
            //找到链表的最后
            if(temp.next == null) {
                break;
            }
            //如果没有找到最后, 将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    /***
     * 方法二：添加节点时，根据编号将节点插入到指定位置
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //我们找的temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {
                //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no) {
                //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明希望添加的heroNode的编号已然存在
                flag = true;
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag 的值
        if(flag) {
            //说明编号存在，不能添加
            System.out.printf("待插入的英雄编号 %d 已存在, 无法插入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /***
     * 修改节点的信息, 根据no编号来修改
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while(true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if(temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号 %d 的节点，无法修改\n", newHeroNode.no);
        }
    }

    /***
     * 删除节点（根据节点编号删除）
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if(flag) {
            //找到，可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    /***
     * 显示链表[遍历]
     */
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true) {
            //判断是否到链表最后
            if(temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}

/***
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点
    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}

