package Practice;

import java.util.ArrayList;

/**
 * Created by wenxi on 2017/9/4.
 * 计算最大子序列和
 */
public class MaximumContigousSubsequenceSum {


    /*
    递归方法得到最大子序列和
    @param a为主序列，left和right分别为左右边界
     */
    public static int maxSumRec(int[] a,int left,int right){

        if(left==right){//基准条件
            if(a[left]>0){
                return a[left];
            }else{
                return 0;
            }
        }


        int center=(left+right)/2;
        int maxLeftSum=maxSumRec(a,left, center);
        int maxRightSum=maxSumRec(a,center+1,right);

        int maxLeftBorderSum=0,leftBorderSum=0;
        for(int i=center;i>=left;i--){
            leftBorderSum+=a[i];
            if(leftBorderSum>maxLeftBorderSum)
                maxLeftBorderSum=leftBorderSum;
        }

        int maxRightBorderSum=0,rightBorderSum=0;
        for(int i=center+1;i<=right;i++){
            rightBorderSum+=a[i];
            if(rightBorderSum>maxRightBorderSum)
                maxRightBorderSum=rightBorderSum;
        }

        return max3(maxLeftSum,maxRightSum,maxLeftBorderSum+maxRightBorderSum);
    }

    public static int max3(int a,int b,int c){
        int max=a;
        if(b>max) max=b;
        if(c>max) max=c;
        return max;
    }


    /*
    线性方法
    如果a[i]是负的，那么a[i]不可能是序列的前缀，因为在这种情况下，可以用a[i+1]取代a[i]
    同理，如果a[left...right]的和是负的，那么也不可能是前缀

     */
    public static int maxSumLiner(int[] a){
        ArrayList<Integer> subsequence=new ArrayList<>();
        ArrayList<Integer> temp=new ArrayList<>();
        int max=0,sum=0;
        for(int i=0;i<a.length;i++){

            sum+=a[i];
            temp.add(a[i]);

            if(sum>max) {
                max=sum;
                subsequence=new ArrayList<>(temp);
            }
            else if(sum<0) {
                sum=0;
                //重置子序列
                temp.clear();
            }
        }

        for (int n : subsequence){
            System.out.print(n+",");
        }
        System.out.println();
        return max;
    }


    public static void main(String[] args) {
        int a[]={4,-3,5,-2,-1,2,6,-2};
        int b[]={2,3,-7,3};
        int c[]={1,-5,4,9,3};

        test(a);
        test(b);
        test(c);

    }

    public static void test(int[] a){
        int result=maxSumRec(a,0,a.length-1);
        System.out.println(result);
        result=maxSumLiner(a);
        System.out.println(result);
    }


}
