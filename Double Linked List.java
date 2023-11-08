import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;

interface ILinkedList 
{
    void add(int index, int element);
    void add(int element);
    int get(int index);
    void set(int index, int element);
    void clear();
    boolean isEmpty();
    void remove(int index);
    int size();
    ILinkedList sublist(int fromIndex, int toIndex);
    boolean contains(int o);
}

public class DoubleLinkedList implements ILinkedList {
    Node head;
    Node tail;

    class Node {
        int data;
        Node next;
        Node prev;

        Node(int x) {
            data = x;
            next = null;
            prev = null;
        }
    }

    public Node insertNode(int data)
    {
        Node new_node = new Node(data);
        if (head == null) {
            head = new_node;
            tail = new_node;
        } else {
            new_node.next = head;
            head.prev = new_node;
            head = new_node;
        }
        return new_node;
    }

    public void display()
    {
        Node node = head;
        System.out.print("[");
        while (node != null) {
            if (node.next == null) {
                System.out.print(node.data);
            } else {
                System.out.print(node.data + ", ");
            }
            node = node.next;
        }
        System.out.print("]");
        System.out.println();
    }
    
    public void add(int index, int element)
    {
        
        Node new_node = new Node (element);
        
        if(index == 0)
        {
            new_node.next = head;
            head = new_node;
            return;
        }
        
        int count = 1;
        Node node = head;
        while(node != null && count < index)
        {
            node = node.next;
            count++;
        }
        
        new_node.next = node.next;
        node.next = new_node;
        return;
    }
    
    public void add(int element)
    {
        Node new_node = new Node (element);
        
        if(head == null)
        {
            head = new_node;
        }
        else
        {
            Node node = head;
            while(node.next != null)
            {
                node = node.next;
            }
            node.next = new_node;
        }
    }
    
    public int get(int index)
    {
        int y = 0;

        if(index == 0)
        {
            y = head.data;
        }
        else
        {
            int count = 1;
            Node node = head;
            while(node != null && count < index)
            {
                node = node.next;
                count++;
            }
                
            y = node.next.data;
        }
        return y;
    }
    
    public void set(int index, int element)
    {   
        if(index == 0)
        {
            head.data = element;
            return;
        }
        
        int count = 0;
        Node node = head;
        while(node != null && count < index )
        {
            node = node.next;
            count++;
        }
            
        node.data = element;
        return;
    }
    
    public void clear()
    {
        head = null;
    }
    
    public boolean isEmpty()
    {
        if(head == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void remove(int index)
    {
        if(index == 0)
        {
            head = head.next;
            return;
        }
        
        Node node = head;
        Node previous = null;
        int count = 0;
        while(node != null && count < index)
        {
            previous = node;
            node = node.next;
            count++;
        }
        
        if(node != null)
        {
            previous.next = node.next;
            return;
        }
    }
    
    public int size()
    {
        if(head == null)
        {
            return 0;
        }
        else
        {
            Node node = head;
            int count = 0;
            while(node != null)
            {
                node = node.next;
                count++;
            }
            return count;
        }
    }
    
    public DoubleLinkedList sublist(int fromIndex, int toIndex)
    {
        DoubleLinkedList result = new DoubleLinkedList();
        Node node = head;
        int currentIndex = 0;

        while (node != null && currentIndex < fromIndex ) {
            node = node.next;
            currentIndex++;
        }

        while (node != null && currentIndex <= toIndex ) {
            result.add(node.data);
            node = node.next;
            currentIndex++;
        }

        return result;
    } 
    
    public boolean contains(int o)
    {
        if(head == null)
        {
            return false;
        }
        else
        {
            Node node = head;
            while(node != null)
            {
                if(node.data == o)
                {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DoubleLinkedList theList = new DoubleLinkedList();

        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty()) {
            arr = new int[]{};
        } else {
            for (int i = 0; i < s.length; ++i) {
                arr[i] = Integer.parseInt(s[i]);
            }

            for (int i = s.length - 1; i >= 0; --i) {
                theList.insertNode(arr[i]);
            }
        }

        String keyword = sc.nextLine();

        switch(keyword)
        {
            case "add":
                int element = sc.nextInt();
                theList.add(element);
                theList.display();
                break;
                
            case "addToIndex":
                int index1 = sc.nextInt();
                int element1 = sc.nextInt();
                if(index1 > theList.size() || index1 < 0)
                {
                    System.out.print ("Error");
                }
                else
                {
                    theList.add(index1, element1);
                    theList.display();
                }
                break;
            
            case "get":
                int index2 = sc.nextInt();
                if(index2 >= theList.size() || index2 < 0)
                {
                    System.out.print ("Error");
                }
                else
                {
                    int result2 = theList.get(index2);
                    System.out.print (result2);
                }
                break;
                
            case "set":
                int index3  = sc.nextInt();
                int element3 = sc.nextInt();
                if(index3 >= theList.size() || index3 < 0)
                {
                    System.out.print ("Error");
                }
                else
                {
                    theList.set(index3, element3);
                    theList.display();
                }
                break;
                
            case "clear":
                theList.clear();
                System.out.print ("[]");
                break;
                
            case "isEmpty":
                boolean result4 = theList.isEmpty();
                if(result4 == true)
                {
                    System.out.print("True");
                }
                else
                {
                    System.out.print("False");
                }
                break;
                
            case "remove":
                int index5 = sc.nextInt();
                if(index5 >= theList.size() || index5 < 0)
                {
                    System.out.print ("Error");
                }
                else
                {
                    theList.remove(index5);
                    theList.display();
                }
                break;
                
            case "sublist":
                int fromIndex = sc.nextInt();
                int toIndex = sc.nextInt();
                if (fromIndex < 0 || toIndex < fromIndex || theList.size() <= toIndex) 
                {
                    System.out.println ("Error");
                }
                else{
                DoubleLinkedList result = theList.sublist(fromIndex, toIndex);
                result.display();}
                break;
                
            case "contains":
                int o = sc.nextInt();
                boolean result6 = theList.contains(o);
                if(result6 == true)
                {
                    System.out.print("True");
                }
                else
                {
                    System.out.print("False");
                }
                break;
                
            case "size":
                int result7 = theList.size();
                System.out.print (result7);
                break;
                
            default:
                System.out.print ("Error");
                
        }
    }
}
