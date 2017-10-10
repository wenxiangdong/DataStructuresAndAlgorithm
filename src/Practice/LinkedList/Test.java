package Practice.LinkedList;

import java.util.Iterator;

/**
 * Created by wenxi on 2017/10/9.
 */
public class Test {



    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }


        System.out.println(list);
        System.out.println("size:"+list.getSize());

        System.out.println(list.getNode(4));

        list.insert(2,100);
        System.out.println(list);
        System.out.println("size:"+list.getSize());

        for(Iterator<Integer> integerIterator = list.iterator();integerIterator.hasNext();){
            if(integerIterator.next()==100) integerIterator.remove();
        }
        System.out.println(list);
        System.out.println("size:"+list.getSize());




    }





}
