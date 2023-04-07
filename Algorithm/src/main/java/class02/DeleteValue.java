package class02;

/****
 * @Author: jerusalem
 * @Description: DeleteGivenValue
 * 删除给定值
 * @Date 2023-04-05 20:16
 *****/
public class DeleteValue {
    /***
     * 单链表结构
     */
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /***
     * 删除链表中等于给定值的节点
     * @param head
     * @param num
     * @return
     */
    public static Node removeValue(Node head, int num) {
        //先遍历，直到head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;  //pre指向上一个不等于给定值的节点
            }
            cur = cur.next;
        }
        return head;
    }
}
