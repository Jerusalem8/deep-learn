package com.jerusalem.dataStructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/****
 * @Author: jerusalem
 * @Description: PolandNotation
 * 逆波兰计算器
 * @Date 2020/10/14 14:45
 *****/
public class PolandNotation {
    public static void main(String[] args) {
        System.out.println("请输入运算表达式：");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.next();
        //步骤一：将中缀表达式转成对应的List
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList);
        //步骤二：将得到的中缀表达式对应的List => 后缀表达式对应的List（逆波兰表达式）
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpressionList);
        //步骤三：逆波兰表达式的运算
        System.out.printf("运算结果 = %d", calculate(suffixExpressionList));
    }

    //步骤一：将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式
        List<String> ls = new ArrayList<String>();
        int i = 0; //这是一个指针，用于遍历中缀表达式字符串
        String str; // 多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls
            if((c=s.charAt(i)) < 48 ||  (c=s.charAt(i)) > 57) { //'0'[48]->'9'[57]
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成""
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
    }

    //步骤二：将得到的中缀表达式对应的List => 后缀表达式对应的List（逆波兰表达式）
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        //说明：因为s2栈，在整个转换过程中，没有pop操作，而且需要逆序输出,直接使用List<String> s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的Lists2
        //遍历ls
        for(String item: ls) {
            //如果是一个数，加入s2
            if(item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将 ( 弹出s1栈， 消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次与s1中新的栈顶运算符相比较
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }

    //步骤三：逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        // 创建栈, 只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        // 遍历 ls
        for (String item : ls) {
            // 使用正则表达式取数
            if (item.matches("\\d+")) { // 匹配多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

/***
 * 定义一个类 Operation 可以返回一个运算符 对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        if ("+".equals(operation)) {
            result = ADD;
        } else if ("-".equals(operation)) {
            result = SUB;
        } else if ("*".equals(operation)) {
            result = MUL;
        } else if ("/".equals(operation)) {
            result = DIV;
        } else {
            System.out.println("不存在该运算符" + operation);
        }
        return result;
    }
}
