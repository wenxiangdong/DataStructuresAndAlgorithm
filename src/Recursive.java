/**
 * Created by wenxi on 2017/9/21.
 * 利用递归进行组合
 */
import java.util.Scanner;

public class Recursive {

    public static void main(String[] args) {
        int n=0,k=0;
        Scanner reader=new Scanner(System.in);
        n=reader.nextInt();
        k=reader.nextInt();
        reader.close();

        combine(n, k, "");

    }


    //利用递归实现    组合
    //dfs
	/*
	 * index为遍历到的数
	 * rest剩下的要选取的个数
	 * result 为要打印的组合
	 */
    static void combine(int index,int rest,String result){
        if(rest==0){
            System.out.println(result);
        }else{
            for(;index>0;index--){//树的展开
                combine(index-1,rest-1,result+String.valueOf(index)+" ");
            }
        }
    }
}

