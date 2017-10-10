package Practice.BinSearchTree;

import java.util.Arrays;

/**
 * Created by wenxi on 2017/10/9.
 */
public class Test {
    public static void main(String[] args) {
    	BinSearchTree<Integer> tree = new BinSearchTree<>();


    	boolean flag[] = new boolean[20];
        Arrays.fill(flag,false);

    	for(int i=0;i<10;i++){
    	    int rand = (int)(Math.random()*20);
    	    while (flag[rand]){rand=(int)(Math.random()*20);}
    	    flag[rand]=true;
    	    tree.insert(rand);
            System.out.print(rand+"->");
        }
        System.out.println();

        tree.print(Order.in);

        tree.print(Order.level);


    }
}

