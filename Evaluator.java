import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator
{
    public String infixToPostfix(String expression);
    public int evaluate(String expression);
    public void setValues(int c1, int c2, int c3);
    public int pre(char operator);
    public int getVal(char x);
}

interface IStack
{
   public Object pop();
   public Object peek();
   public void push(Object element);
   public boolean isEmpty();
   public int size();
}


class MyStack
{
    
    private class Node
    {
        Object data;
        Node next;
    }
    
    Node top;
    
    MyStack()
    {
        this.top = null;
    }
    
    public Object pop()
    {
        Node temp = top;
        Object val;
        int max = size();
        if (temp == null)
        {
            throw new RuntimeException("Error");
        }
        else if(max == 1)
        {
            val = temp.data;
            top = null;
            return val;
        }
        else
        {
            val = temp.data;
            top = top.next;
            temp = null;
            return val;
        }
    }
    
    public Object peek()
    {
        if (!isEmpty())
        {
            return top.data;
        }
        else
        {
            System.out.println("Error");
            return 0;
        }
    }
    
    public void push(Object element)
    {
        Node temp = new Node();
        temp.data = element;
        temp.next = top;
        top = temp;
    }
    
    public boolean isEmpty()
    {
        if(top == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int size()
    {
        int count = 0;
        if (top == null)
        {
            return 0;
        }
        else
        {
            Node temp = new Node();
            temp = top;
            while (temp != null)
            {
                count++;
                temp = temp.next;
            }
        }
        
        return count;
    }
    
    public void display()
    {   
        System.out.print("[");
        Node temp = top;
        while (temp != null)
        {
            if(temp.next == null)
            {
                System.out.print(temp.data);
            }
            else
            {
                System.out.print(temp.data + ", ");
            }
            temp = temp.next;
        }
        System.out.print("]");
        System.out.println();
    }
}


public class Evaluator
{
    
    int a = 0;
    int b = 0;
    int c = 0;
    
    public void setValues(int c1, int c2, int c3)
    {
        this.a = c1;
        this.b = c2;
        this.c = c3;
    }
    
    public String infixToPostfix(String expression)
    {   
        
        expression = expression.replace("^--", "^").replace("*--", "*").replace("/--", "/").replace("+--", "+")
                .replace("(--", "(");
        if (expression.startsWith("--"))
            expression = expression.replaceFirst("--", "");
        if (expression.contains("--"))
            expression = expression.replaceAll("--", "+");
            
        
        int x = 0;
        char character;
        char poping;
        int count = 0;
        
        MyStack stack = new MyStack();
        String postfix = "";
        
        for(int i = 0; i < expression.length(); i++)
        {
            character = expression.charAt(i);
            switch(character)
            {
                case'(':
                    stack.push(character);
                    count++;
                    break;
                    
                case')':
                    while(!stack.isEmpty() && (char)stack.peek() != '(')
                    {
                        postfix = postfix + stack.pop();
                    }
                    if(!stack.isEmpty())
                    {
                        stack.pop();
                    }
                    count--;
                    break;
                    
                case'-':
                case'+':    
                case'*':
                case'/':
                case'^':
                    if ((i == 0 && character != '-') || (i == expression.length() - 1))
                    {
                        System.out.println("Error");
                        System.exit(0);
                    }
    
                    if ((i > 0) && !Character.isLetter(expression.charAt(i - 1)) && expression.charAt(i - 1) != '(' && expression.charAt(i - 1) != ')')
                    {
                        System.out.println("Error");
                        System.exit(0);
                    }
                    if (stack.isEmpty())
                    {
                        stack.push(character);
                    }
                    else
                    {
                      while(!stack.isEmpty() && pre((char)stack.peek()) >= pre(character))
                      {
                          postfix = postfix + stack.peek();
                          stack.pop();
                      }
                      stack.push(character);
                   }
                    break;
                    
                default:
                    postfix = postfix + character;
                    if ((i > 0) && Character.isLetter(expression.charAt(i - 1)))
                    {
                        System.out.println("Error");
                        System.exit(0);
                    }
            }
        }
        
        while(!stack.isEmpty())
        {
            postfix += stack.pop();
        }
        
        if (count != 0)
        {
            System.out.println("Error");
            System.exit(0);
        }
        
        return postfix;
    }
    
    public int pre(char operator)
    {
        int precedence = 0;
        switch(operator)
        {
            case'+':
            case'-':
                precedence = 1;
                break;
            case'*':
            case'/':
                precedence = 2;
                break;
            case'^':
                precedence = 3;
                break;
            case '(':
            case ')':
                precedence = 0;
                break;    
             default:
                System.out.println("Error");
                System.exit(0);
                break;
        }
        return precedence;
    }
    
    public int evaluate(String expression)
    {
        MyStack stack = new MyStack();
        for(int i = 0; i < expression.length(); i++)
        {
            char letter = expression.charAt(i);
            if(Character.isLetter(letter))
            {
                stack.push(getVal(letter));
            }
            else if (!Character.isLetter(letter))
            {
                int x = 0;
                int y = 0;
                try {
                    x = (int)stack.pop();
                    y = (int)stack.pop();
                } catch (Exception e) {
                    if (letter != '-')
                    {
                        System.out.println("Error");
                        System.exit(0);
                    }
                    y = 0;
                }
                
                switch(letter)
                {
                    case'+':
                        stack.push(x+y);
                        break;
                    case'-':
                        stack.push(y-x);
                        break;
                    case'*':
                        stack.push(x*y);
                        break;
                    case'/':
                        if (x == 0)
                        {
                            System.out.println("Error");
                            System.exit(0);
                        }
                        stack.push(y/x);
                        break;
                    case'^':
                        if (y < 0)
                        {
                            stack.push(0);
                        }
                        else
                        {
                            stack.push((int)Math.pow(y,x));
                        }
                        break;
                    default:
                        System.out.println("Error");
                        System.exit(0);
                        break;    
                }
            }
        }
        
        return (int)stack.pop();
    }
    
    public int getVal(char x)
    {
        int value = 0;
        switch (x) {
        case 'a':
            value = a;
            break;
        case 'b':
            value = b;
            break;    
        case 'c':
            value = c;
            break;    
        default:
            System.out.println("Error");
            System.exit(0);
            break;
        }
        return value;
    }
  
    public static void main(String[] args)
    {
        Evaluator eval = new Evaluator();
        
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();
        
        String strA = sc.nextLine().replaceAll("(a|b|c)=", "");
        String strB = sc.nextLine().replaceAll("(a|b|c)=", "");
        String strC = sc.nextLine().replaceAll("(a|b|c)=", "");
        int A = Integer.parseInt(strA);
        int B = Integer.parseInt(strB);
        int C = Integer.parseInt(strC);
        
        eval.setValues(A, B, C);
        String post = eval.infixToPostfix(exp);
        System.out.println(post);
        System.out.println(eval.evaluate(post));
    }
}