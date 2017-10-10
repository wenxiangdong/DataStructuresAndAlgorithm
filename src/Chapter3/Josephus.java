package Chapter3;

/**
 * Created by wenxi on 2017/9/24.
 * 约瑟夫问题的求解
 */
public class Josephus {


    public static void main(String[] args) {
        int n=10;
        int gap = 3;

        System.out.println("By Array");
        byArray(n,gap);
        System.out.println("-------------------------");
        System.out.println("By LinkedList");
        byLinkedList(n,gap);
    }

    //用数组实现
    static void byArray(int numOfPeople,int gap){
        int[] peoples = new int[numOfPeople];

        //编号
        for(int i =0;i<numOfPeople;i++){
            peoples[i]=i+1;
        }
        int rest = numOfPeople;
        int count=0;//报数器
//        boolean flag = true;
        while (rest>1){
            for(int i=0;i<numOfPeople;i++){
                if (peoples[i]!=0){
                    count++;//报数加1
                    if (count == gap){
                        System.out.println(peoples[i]+" out!");
                        peoples[i]=0;
                        count = 0;//重置报数器
                        rest--;
                        if (rest == 1) break;
                    }
                }

            }
        }

        for (int id : peoples){
            if (id != 0) System.out.println("The lucky one is "+id);
        }



    }

    //用循环链表实现
    static void byLinkedList(int numOfPeople,int gap){
        //构建循环链表
        People first=new People(0);
        People current=null;
        for(int i=0;i<numOfPeople;i++){
            if(i==0) {
                current=new People(i+1);
                first.next=current;
            } else{
                current.next=new People(i+1);
                current = current.next;
                if(i==numOfPeople-1)
                    current.next = first.next;//首尾连接
            }
        }
        //开始报数
        int rest = numOfPeople;
        current = first;
        while (rest>1){
            for(int i=0;i<gap-1;i++){
                current = current.next;
            }
            if(rest>1){
                System.out.println(current.next.id+" out!");
                current.next=current.next.next;
                rest--;
            }

        }

        System.out.println("The lucky one is "+current.id);

    }
}


class People{
    int id;
    People next;

    public People(int id){
        this.id = id;
        next=null;
    }
}



