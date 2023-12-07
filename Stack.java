import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack
{
  public Object pop();
  public Object peek();
  public void push(Object element);
  public boolean isEmpty();
  public int size();
}


public class MyStack implements IStack
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
        int max = size();
        if (top == null)
        {
            System.out.println("Error");
            return 0;
        }
        else if(max == 1)
        {
            top = null;
        }
        else
        {
            top = (top).next;
        }
        return 1;
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

    public static void main(String[] args)
    {
        MyStack stack = new MyStack();
        
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
        {
            arr = new int[] {};
        }
        else 
        {
            for(int i = 0; i < s.length; ++i)
            {
                arr[i] = Integer.parseInt(s[i]);
            }
            
            for(int i = s.length - 1; i >= 0; --i) 
            {
                stack.push(arr[i]);
            }
        }
        
        String keyword = sc.nextLine();
        
        switch(keyword)
        {
            case "push":
                int element = sc.nextInt();
                stack.push(element);
                stack.display();
                break;
                
            case "pop":
                Object poping = stack.pop();
                if((int)poping != 0)
                {
                   stack.display();
                }
                break;
            
            case "peek":
                Object peeking = stack.peek();
                if((int)peeking != 0){System.out.print(peeking);}
                break;
                
            case "isEmpty":
                boolean x = stack.isEmpty();
                if(x == false)
                {
                    System.out.print("False");
                }
                else
                {
                    System.out.print("True");
                }
                break;
                
            case "size":
                int size = stack.size();
                System.out.print (size);
                break;
                
            default:
                System.out.print ("Error");
                
        }
        
        
    }
}