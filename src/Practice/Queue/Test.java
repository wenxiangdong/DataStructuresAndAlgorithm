package Practice.Queue;

/**
 * Created by wenxi on 2017/10/9.
 */
public class Test {
    public static void main(String[] args) {
    	MyQueue<Integer> queue = new MyQueue<>(10);


        for(int i=0;i<10;i++){
            queue.push(i);
        }
        System.out.println(queue);

        System.out.println(queue.peek());

        queue.pop();
        System.out.println(queue);

        queue.push(11);
        System.out.println(queue);

    }
}
