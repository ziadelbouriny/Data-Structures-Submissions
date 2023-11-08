import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList
{
    public void add(int index, int element);
    public void add(int element);
    public int get(int index);
    public void set(int index, int element);
    public void clear();
    public boolean isEmpty();
    public void remove(int index);
    public int size();
    public ILinkedList sublist(int fromIndex, int toIndex);
    public boolean contains(int o);
}

class Node
{
    Object data;
    Node next;
    
    Node(Object x)
    {
        this.data = x;
        this.next = null;
    }
}

class SingleLinkedList
{
    Node head;
    
    SingleLinkedList()
    {
        head = null;
    }

    public Node insertNode (int data)
    {
        Node new_node = new Node (data);
        new_node.next = head;
        head = new_node;

        return head;
    }

    public void display () 
    {
        Node node = head;
        System.out.print ("[");
        while (node != null)
        {
            if(node.next == null)
            {
                System.out.print (node.data);
            }
            else
            {
                System.out.print (node.data + ", ");
            }
            node = node.next;
        }
        System.out.print ("]");
        System.out.println ();
    }
    
    public void add(int index, Object element)
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
    
    public void add(Object element)
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
    
    public Object get(int index)
    {
        Object y = 0;

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
    
    public SingleLinkedList sublist(int fromIndex, int toIndex)
    {
        SingleLinkedList result = new SingleLinkedList();
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
    
    public boolean contains(Object o)
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
}    

interface IPolynomialSolver
{
    void setPolynomial(char poly, int[][] terms);
    String print(char poly);
    void clearPolynomial(char poly);
    float evaluatePolynomial(char poly, float value);
    int[][] add(char poly1, char poly2);
    int[][] subtract(char poly1, char poly2);
    int[][] multiply(char poly1, char poly2);
}

class equation
{
    int coeff;
    int exp;

    equation(int c, int e)
    {
        this.coeff = c;
        this.exp = e;
    }
}

public class PolynomialSolver
{

    SingleLinkedList A;
    SingleLinkedList B;
    SingleLinkedList C;
    SingleLinkedList R;
    
    PolynomialSolver()
    {
        A = new SingleLinkedList();
        B = new SingleLinkedList();
        C = new SingleLinkedList();
        R = new SingleLinkedList();
    }
    
    public void setPolynomial(char poly, int[][] terms)
    {
        SingleLinkedList L = null;
        
        switch(poly)
        {
            case 'A':
                L = A;
                break;
            case 'B':
                L = B;
                break;
            case 'C':
                L = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        for (int[] term : terms)
        {
            equation eqn = new equation(term[0], term[1]);
            L.add(eqn);
        }
    }
    
    String print(char poly)
    {
        SingleLinkedList L = null;
        
        switch(poly)
        {
            case 'A':
                L = A;
                break;
            case 'B':
                L = B;
                break;
            case 'C':
                L = C;
                break;
            case 'R':
                L = R;
            break;    
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        Node node = L.head;
        
        String eqn = "";
        int count = 0;
        while(node != null)
        {
            equation temp = (equation) node.data;
            if(temp.coeff != 0)
            {
                if(count > 0)
                {
                    if(temp.coeff > 0)
                    {
                        eqn = eqn + "+";
                    }
                    else
                    {
                        eqn = eqn + "-";
                        temp.coeff = Math.abs(temp.coeff);
                    }
                }
            }
            
            if(temp.exp == 0)
            {
                eqn = eqn + temp.coeff;
            }
            else
            {
                if (temp.coeff != 1)
                {
                    eqn = eqn + temp.coeff;
                }
                eqn = eqn + "x";
                
                if (temp.exp != 1)
                {
                    eqn = eqn + "^" + temp.exp;
                }
            }
            count ++;
            node = node.next;
        }
       return eqn; 
    }
    
    void clearPolynomial(char poly)
    {
        SingleLinkedList L = null;
        
        switch(poly)
        {
            case 'A':
                L = A;
                break;
            case 'B':
                L = B;
                break;
            case 'C':
                L = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        L = null;
        System.out.print("[]");
    }
    
    float evaluatePolynomial(char poly, float value)
    {
        SingleLinkedList L = null;
        
        switch(poly)
        {
            case 'A':
                L = A;
                break;
            case 'B':
                L = B;
                break;
            case 'C':
                L = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        float result = 0;
        
        Node node = L.head;
        while(node != null)
        {
            equation temp = (equation) node.data;
            result += (float)temp.coeff * Math.pow(value, (float)temp.exp);
            node = node.next;
        }
        return result;
    }
    
    int[][] add(char poly1, char poly2)
    {
        R.clear();
        SingleLinkedList L = null;
        SingleLinkedList M = null;
        
        switch(poly1)
        {
            case 'A':
                L = A;
                break;
            case 'B':
                L = B;
                break;
            case 'C':
                L = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        switch(poly2)
        {
            case 'A':
                M = A;
                break;
            case 'B':
                M = B;
                break;
            case 'C':
                M = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        Node x = L.head;
        Node y = M.head;
        Node r = R.head;
        while(x != null && y != null)
        {
            equation eqnX = (equation) x.data;
            equation eqnY = (equation) y.data;
            
            if(eqnX.exp > eqnY.exp)
            {
                if(eqnX.coeff == 0)
                {
                    x = x.next;
                }
                else
                {
                    equation res = new equation(eqnX.coeff, eqnX.exp); 
                    R.add(res);
                    x = x.next;    
                }
            }
            else if(eqnX.exp < eqnY.exp)
            {
                if(eqnY.coeff == 0)
                {
                    y = y.next;
                }
                else
                {
                    equation res = new equation(eqnY.coeff, eqnY.exp); 
                    R.add(res);
                    y = y.next;    
                }
            }
            else
            {
                if(eqnX.coeff + eqnY.coeff == 0)
                {
                    x = x.next;
                    y = y.next;
                }
                else
                {
                    equation res = new equation(eqnX.coeff + eqnY.coeff, eqnX.exp); 
                    R.add(res);
                    x = x.next;
                    y = y.next;
                }
            }
        }
        
        int[][] result = new int[R.size()][2];
        int i = 0;
        while (r != null)
        {
            equation res = (equation) r.data;
            result[i][0] = res.coeff;
            result[i][1] = res.exp;
            i++;
            r = r.next;
        }
        return result;
    }
    
    int[][] subtract(char poly1, char poly2)
    {
        R.clear();
        SingleLinkedList L = null;
        SingleLinkedList M = null;
        
        switch(poly1)
        {
            case 'A':
                L = A;
                break;
            case 'B':
                L = B;
                break;
            case 'C':
                L = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }
        
        switch(poly2)
        {
            case 'A':
                M = A;
                break;
            case 'B':
                M = B;
                break;
            case 'C':
                M = C;
                break;
            default:
                System.out.print("Error");
                System.exit(0);
        }

        Node x = L.head;
        Node y = M.head;
        Node r = R.head;
        while(x != null && y != null)
        {
            equation eqnX = (equation) x.data;
            equation eqnY = (equation) y.data;
            
            if(eqnX.exp > eqnY.exp)
            {
                if(eqnX.coeff == 0)
                {
                    x = x.next;
                }
                else
                {
                    equation res = new equation(eqnX.coeff, eqnX.exp);
                    R.add(res);
                    x = x.next;
                }
            }
            else if(eqnX.exp < eqnY.exp)
            {
                if(eqnY.coeff == 0)
                {
                    y = y.next;
                }
                else
                {
                    equation res = new equation(-eqnY.coeff, eqnY.exp);
                    R.add(res);
                    y = y.next;
                }
            }
            else
            {
                if(eqnX.coeff - eqnY.coeff == 0)
                {
                    x = x.next;
                    y = y.next;
                }
                else
                {
                    equation res = new equation(eqnX.coeff - eqnY.coeff, eqnY.exp);
                    R.add(res);
                    x = x.next;
                    y = y.next;
                }
            }
        }
        
        int[][] result = new int[R.size()][2];
        int i = 0;
        while (r != null)
        {
            equation res = (equation) r.data;
            result[i][0] = res.coeff;
            result[i][1] = res.exp;
            i++;
            r = r.next;
        }
        return result;
    }
    
    int[][] multiply(char poly1, char poly2)
    {
        SingleLinkedList L = null;
        SingleLinkedList M = null;
        R.clear();
        switch (poly1) {
        case 'A':
            L = A;
            break;
        case 'B':
            L = B;
            break;
        case 'C':
            L = C;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
        }

        switch (poly2) {
        case 'A':
            M = A;
            break;
        case 'B':
            M = B;
            break;
        case 'C':
            M = C;
            break;
        default:
            System.out.print("Error");
            System.exit(0);
        }

        Node x = L.head;
        Node y = M.head;
        equation eqn1 = (equation) x.data;
        equation eqn2 = (equation) y.data;
        int range = eqn1.exp + eqn2.exp;

        for (int i = range; i >= 0; i--)
        {
            equation temp = new equation(1, i);
            R.add(temp);
        }

        Node r = R.head;        
        while (r != null)
        {
            equation accu = (equation) r.data;
            int sumCoeff = 0;
            while (x != null)
            {
                equation eqnx = (equation) x.data;
                while (y != null) 
                {
                    equation eqny = (equation) y.data;
                    if (eqnx.exp + eqny.exp == accu.exp) 
                    {
                        sumCoeff += eqny.coeff * eqnx.coeff;
                    }
                    y = y.next;
                }
                y = M.head;
                x = x.next;
            }
            x = L.head;
            accu.coeff = sumCoeff;
            r = r.next;
        }

        r = R.head;
        int[][] result = new int[R.size()][2];
        int i = 0;
        while (r != null) {
            equation temp = (equation) r.data;
            result[i][0] = temp.coeff;
            result[i][1] = temp.exp;
            i++;
            r = r.next;
        }    
        return result;
    }    
    
    public static void main(String[] args)
    {
        PolynomialSolver p = new PolynomialSolver();
        
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext())
        {
            String keyword = sc.next();
            sc.nextLine();
            
            char poly;
            char poly1;
            char poly2;
            
            switch(keyword)
            {
                case "eval":
                    poly = sc.next().charAt(0);
                    sc.nextLine();
                    float value = sc.nextFloat();
                    float result = p.evaluatePolynomial(poly, value);
                    if (result - (int) result == 0.0)
                    {
                        System.out.println((int) result);
                    }
                    else
                    {
                        System.out.println(result);    
                    }
                    break;
                 
                case "clear":
                    poly = sc.next().charAt(0);
                    p.clearPolynomial(poly);
                    break;
                
                case "print":
                    poly = sc.next().charAt(0);
                    System.out.println(p.print(poly));
                    break;
                    
                case "set":
                    String test = sc.nextLine();
                    poly = test.charAt(0);
                    
                    String sin = sc.nextLine().replaceAll("\\[|\\]", "");
                    String[] s = sin.split(",");;
                    int[][] arr = new int[s.length][2];
                    if (s.length == 1 && s[0].isEmpty())
                    {
                        System.out.println("Error");
                        System.exit(0);
                    }
                    else 
                    {
                        for (int i = 0; i < s.length; ++i) {
                            arr[i][0] = Integer.parseInt(s[i]);
                            arr[i][1] = s.length - i - 1;
                        }
                    }
                    p.setPolynomial(poly, arr);
                    break;
                
                case "sub":
                    poly1 = sc.next().charAt(0);
                    sc.nextLine();
                    poly2 = sc.next().charAt(0);
                    p.subtract(poly1, poly2);
                    System.out.println(p.print('R'));
                    break;
                    
                case "add":
                    poly1 = sc.next().charAt(0);
                    sc.nextLine();
                    poly2 = sc.next().charAt(0);
                    p.add(poly1, poly2);
                    System.out.println(p.print('R'));
                    break;
                    
                case "mult":
                    poly1 = sc.next().charAt(0);
                    sc.nextLine();
                    poly2 = sc.next().charAt(0);
                    p.multiply(poly1, poly2);
                    System.out.println(p.print('R'));
                    break;
                    
                default:
                    System.out.println("Error");
                    System.exit(0);
            }
        }   
    }
}