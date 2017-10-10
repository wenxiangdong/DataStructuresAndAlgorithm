package Chapter3;

import java.util.Scanner;

/**
 * Created by wenxi on 2017/9/24.
 * Chapter 3作业
 */

class PolynomialNode{
    int coef;
    int exp;
    PolynomialNode next;

    public PolynomialNode(){
        this.next = null;
    }

    public PolynomialNode(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
        this.next = null;
    }
}

class PolynomialList{
    private PolynomialNode head;
    private PolynomialNode rear;

    public PolynomialNode getHead() {
        return head;
    }

    public PolynomialNode getRear() {
        return rear;
    }



    public PolynomialList(){
        this.head = new PolynomialNode();
        this.rear = this.head;
    }

    public boolean insert(PolynomialNode node){
        this.rear.next = node;
        this.rear = node;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        PolynomialNode node = this.getHead().next;


        while (node != null){
            int coef = node.coef;
            int exp = node.exp;

            if(coef!=0){
                if(coef>0){
                    stringBuilder.append("+"+(coef==1?"":coef));
                }else{
                    stringBuilder.append(coef==-1?"-":coef);
                }
            }else{
                continue;
            }
            switch (exp){
                case 0: stringBuilder.append("");break;
                case 1: stringBuilder.append("X");break;
                default: stringBuilder.append("X^"+exp);break;
            }
            node = node.next;
        }

        return stringBuilder.charAt(0)=='+'?stringBuilder.substring(1):stringBuilder.toString();
    }
}


public class Polynomial {




    public static void main(String[] args) {
        additionTest();
    }

    /*
    需要输入两个多项式
    每个多项式的格式如入：
    总共有 n 行
    第一行：整数长度，即非零项的数量n
    接下来2到n-1行：整数coef 和 exp
     */
    public static void additionTest(){
        PolynomialList a = new PolynomialList();
        PolynomialList b = new PolynomialList();

        Scanner scanner = new Scanner(System.in);

        int aLen = scanner.nextInt();
        for(int i = 0; i < aLen; i++){
            int cof = scanner.nextInt();
            int exp = scanner.nextInt();
            a.insert(new PolynomialNode(cof,exp));
        }
        System.out.println(a);

        int bLen = scanner.nextInt();
        for(int i = 0; i < bLen; i++){
            int cof = scanner.nextInt();
            int exp = scanner.nextInt();
            b.insert(new PolynomialNode(cof,exp));
        }
        System.out.println(b);

        System.out.println("result:"+addPolynomial(a,b));
    }


    public static PolynomialList addPolynomial(PolynomialList a, PolynomialList b){
        PolynomialList c = new PolynomialList();//结果

        PolynomialNode aNode = a.getHead().next;
        PolynomialNode bNode = b.getHead().next;

        while(aNode!=null && bNode!=null){
//            System.out.println("flag");
            int exp_a = aNode.exp;
            int exp_b = bNode.exp;
            if(exp_a == exp_b){//指数相同 ???
                c.insert(new PolynomialNode(aNode.coef+bNode.coef,exp_a));
                aNode = aNode.next;
                bNode = bNode.next;
            } else {
                if(exp_a>exp_b){
                    c.insert(new PolynomialNode(aNode.coef,exp_a));
                    aNode = aNode.next;
                }else{
                    c.insert(new PolynomialNode(bNode.coef,exp_b));
                    bNode = bNode.next;
                }
            }
        }

        //将剩余项加入
        while (aNode!=null){
            c.insert(new PolynomialNode(aNode.coef,aNode.exp));
            aNode = aNode.next;
        }
        while (bNode!=null){
            c.insert(new PolynomialNode(bNode.coef,bNode.exp));
            bNode = bNode.next;
        }

        return c;
    }

}
