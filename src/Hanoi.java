import java.util.Scanner;

/**
 * Created by wenxi on 2017/9/22.
 */
public class Hanoi {

    public static String PROMP = "Please input the number of dicks to move:";

    public static void moveDicks(char start,char help,char target,int n){
        if(n==1){
            System.out.println("Dick No."+n+": "+start+" --> "+target);
            return;
        }
        moveDicks(start,target,help,n-1);
        System.out.println("Dick No."+n+": "+start+" --> "+target);
        moveDicks(help,start,target,n-1);
    }

    public static void main(String[] args) {
        System.out.println(PROMP);

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        //将'A'上的n个dick移动到'c'上，借助'B'
        moveDicks('A','B','C',n);


    }
}
