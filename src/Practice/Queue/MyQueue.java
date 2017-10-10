package Practice.Queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by wenxi on 2017/10/9.
 */
public class MyQueue<T> {


    private Object data[];
    private int defaultLen = 10;
    int front=0;
    int back=0;
    int size=0;


    //constructor
    public MyQueue(){
        data = new Object[defaultLen];
    }
    public MyQueue(int len){
        data = new Object[len];
        defaultLen=len;
    }


    public boolean isEmpty(){return this.size == 0;}

    public boolean isFull(){return this.size == defaultLen;}

    public boolean push(T item){
        if(isFull()) throw new IndexOutOfBoundsException("队列已满！");

        data[back] = item;
        back=(++back)%defaultLen;
        size++;

        return true;
    }

    //获得队列最前元素
    public T peek(){
        if(isEmpty()) throw new IllegalStateException("队列为空！");
        return (T)data[front];
    }

    public T pop(){
        if(isEmpty()) throw new IllegalStateException("队列为空！");

        T item = (T)data[front];
        front = (++front)%defaultLen;
        size--;

        return item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = front;
        for(int i=0;i<this.size;i++){
            stringBuilder.append(data[index]);
            stringBuilder.append("-->");
            index = (++index)%defaultLen;
        }
        return stringBuilder.substring(0,stringBuilder.length()-3);
    }
}
