import com.sun.istack.internal.NotNull;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Shear;

import javax.swing.*;

/**
 * Created by wenxi on 2017/9/24.
 * 实现在 Ｏ(n)时间内反转  单链表
 */

class Node{
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
//        this.next = next;
    }
}

class LinkedList{
    //空的辅助头节点
    Node head;
    Node rear = head;

    public  LinkedList(){
        head = new Node(Integer.MAX_VALUE);
    }

    public Node getHead(){
        return this.head;
    }

    public Node getRear(){
        return  rear;
    }

    public boolean insert(@NotNull Node node) {
        this.rear.next = node;
        rear = node;
        rear.next = null;
        return true;
    }

    @Override
    public String toString(){
        String EMPTY_LIST = "Empty LinkedList!";
        Node node = head.next;

        if(node == null) return EMPTY_LIST;

        StringBuilder stringBuilder = new StringBuilder();
        while(node != null){
            stringBuilder.append(node.data+"-->");
            node = node.next;
        }
        return stringBuilder.substring(0,stringBuilder.length()-3);
    }

}

public class ReverseLinkedList {
    static LinkedList linkedList = new LinkedList();
    static int MAX = 10;

    public static void main(String[] args) {
        for (int i = 0; i < MAX; i++) {
            linkedList.insert(new Node(i+1));
        }

        System.out.println(linkedList);

        reverseLinkedList(linkedList);

        System.out.println(linkedList);

    }


    //反转单链表，核心方法
    static boolean reverseLinkedList(LinkedList list){
        Node node = list.getHead().next;

        Node lastOne = null;
        Node nextOne;

        while(node != null){
            nextOne = node.next;
            node.next = lastOne;//将指针改为指向前一个节点

            lastOne = node;
            node = nextOne;//存储当前节点并前进
        }

        //将空头节点指向最后一个节点
        list.getHead().next = lastOne;
        return true;
    }
}

