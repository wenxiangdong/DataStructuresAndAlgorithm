import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by wenxi on 2017/9/18.
 * 将中缀表达式转换为后缀表达式输出并计算
 * 要求在 O(n) 时间内完成
 */
public class Expression {

    Stack<Integer> results=new Stack<Integer>();

    //in stack
    int isp(char c) {
        switch (c) {
            case '#':
                return 0;
            case '(':
                return 1;
            case '*':
                return 5;
            case '/':
                return 5;
            case '%':
                return 5;
            case '+':
                return 3;
            case '-':
                return 3;
            case ')':
                return 6;
            default:
                return 0;
        }
    }
    //in coming
    int icp(char c){
        switch (c)
        {
            case '#':
                return 0;
            case '(':
                return 6;
            case '*':
                return 4;
            case '/':
                return 4;
            case '%':
                return 4;
            case '+':
                return 2;
            case '-':
                return 2;
            case ')':
                return 1;
            default:
                return 0;
        }
    }
    //对后缀表达式进行计算
    void operate(char op) {
        int right = results.pop();
        int left = results.pop();
        int value = 0;
        switch (op){
            case '+':
                value = right + left;
                break;
            case '-':
                value = left - right;
                break;
            case '*':
                value = right * left;
                break;
            case '/':
                value = left / right;
                break;
            case '%':
                value = left % right;
                break;
        }
        results.push(value);
    }


    //输入格式为 表达式加上'#'结束，不能出现空格
    public static void main(String[] args) {
        Expression expression = new Expression();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );
        String s = "";

        try {
            s = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stack<Character> operation = new Stack<>();
        operation.push('#');

        int numberBegin=0;//数字在字符串中的起始位置
        char top,coming=s.charAt(0);
        for(int i = 0; !operation.isEmpty();){

            if(coming>='0'&&coming<='9'){
                i++;
                coming=s.charAt(i);
                continue;
            }else{
                if(i>numberBegin){
                    //推入数字，考虑到数字可能多于一位
                    expression.results.push(Integer.valueOf(s.substring(numberBegin,i)));
                    System.out.print(s.substring(numberBegin,i)+" ");
                }
                numberBegin=i+1;

                top = operation.peek();

                if(expression.icp(coming)>expression.isp(top)){
                    operation.push(coming);
                    i++;
                    coming = s.charAt(i);
                }else if(expression.icp(coming)<expression.isp(top)){
                    operation.pop();
                    System.out.print(top+" ");
                    expression.operate(top);
                }else{
                    operation.pop();
                    if(top=='(') {
                        i++;
                        coming = s.charAt(i);
                    }
                }
            }
        }

        System.out.println("\n"+expression.results.pop());
    }

}

