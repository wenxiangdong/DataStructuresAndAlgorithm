package Practice.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by wenxi on 2017/10/9.
 * 自己实现链表，双向
 */
public class MyLinkedList<T> implements Iterable<T> {

    private int size=0;
    private Node<T> head;//头结点，空结点
    private Node<T> tail;//尾结点 ，空


    //构造空链表
    public MyLinkedList(){
        head = new Node<T>(null,null,null);
        tail = new Node<T>(null,head,null);
        head.next = tail;

        this.size=0;
    }

    public int getSize(){return this.size;}

    //加到最后
    public boolean add(T item){
        Node<T> node = new Node<>(item,null,null);


        node.pre = this.tail.pre;
        this.tail.pre.next = node;
        this.tail.pre = node;
        node.next=this.tail;

        this.size++;
        return true;
    }

    //在某一位置插入
    public boolean insert(int index,T data){

        Node<T> current = getNode(index);

        Node<T> newItem = new Node<>(data,current.pre,current);
        current.pre.next = newItem;
        current.pre = newItem;
        this.size++;

        return  true;
    }

    public Node<T> getNode(int index){
        if(index<0 || index>this.size) throw  new IndexOutOfBoundsException();

        Node<T> current=null;
        if(index < this.size/2){//从头找起
            current=this.head;
            for(int i=0;i<index;i++){
                current=current.next;
            }
        }else{//从尾找起
            current = this.tail;
            for(int i=this.size;i>index;i--){
                current=current.pre;
            }
        }

        return current;
    }

    private boolean remove(Node<T> p){
        if(p==this.head) throw new IndexOutOfBoundsException();
        p.pre.next=p.next;
        p.next.pre = p.pre;
        this.size--;
        return  true;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Iterator<T> iterator = this.iterator();iterator.hasNext();){
            stringBuilder.append(iterator.next());
            stringBuilder.append("-->");
        }

        return stringBuilder.substring(0,stringBuilder.length()-3);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }


    //结点类，嵌套类
    private static class Node<T>{
        public T data;
        public Node<T> pre;
        public Node<T> next;

        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data; this.pre = pre; this.next = next;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }
    }

    private class LinkedListIterator implements Iterator<T>{

        private Node<T> current = head.next;//指向第一个节点
        private boolean isValidToRemove = false;

        @Override
        public boolean hasNext() {
            return current!=tail;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();

            T nextItem = current.data;
            current = current.next;

            isValidToRemove=true;

            return  nextItem;
        }

        @Override
        public void remove() {
            if(!isValidToRemove) throw new IllegalStateException();

            MyLinkedList.this.remove(current.pre);
            isValidToRemove=false;
        }
    }
}
