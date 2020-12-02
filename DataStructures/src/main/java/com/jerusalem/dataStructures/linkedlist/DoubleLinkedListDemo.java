package com.jerusalem.dataStructures.linkedlist;

/****
 * @Author: jerusalem
 * @Description: DoubleLinkedListDemo
 * 双向链表
 * @Date 2020/10/9 19:44
 *****/
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试：");
        // 创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建双向链表，添加节点
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
        //根据编号添加节点
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        //显示链表
        doubleLinkedList.list();
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况：");
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况：");
        doubleLinkedList.list();
    }
}

/***
 * 双向链表的类
 * 实现增删改查
 */
class DoubleLinkedList {

    // 初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    /***
     * 显示链表[遍历]
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 头节点不能动，设辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // temp后移
            temp = temp.next;
        }
    }

    /***
     * 方式一：添加一个节点到双向链表的最后
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后，形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /***
     * 方法二：添加节点时，根据编号将节点插入到指定位置
     * @param newHeroNode
     */
    public void addByOrder(HeroNode2 newHeroNode) {
        //头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //我们找的temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {
                //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > newHeroNode.no) {
                //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == newHeroNode.no) {
                //说明希望添加的heroNode的编号已然存在
                flag = true;
                break;
            }
            temp = temp.next; //后移，遍历当前链表
        }
        //判断flag 的值
        if(flag) {
            //说明编号存在，不能添加
            System.out.printf("待插入的英雄编号 %d 已存在, 无法插入\n", newHeroNode.no);
        } else {
            //插入到链表中, temp的后面
            newHeroNode.next = temp.next;
            temp.next = newHeroNode;
            newHeroNode.pre = temp;
        }
    }

    /***
     * 修改节点的信息
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /***
     * 从双向链表中删除一个节点（自我删除）
     * @param no
     */
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next; // 辅助变量(指针)
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                // 找到的待删除节点
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        if (flag) {
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}

/***
 * 定义HeroNode2 ， 每个HeroNode对象就是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点, 默认为null
    public HeroNode2 pre; // 指向前一个节点, 默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
