/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) 
    {
        if(headA == null || headB == null)
            return null;
        
        int sizeA = 0; 
        int sizeB = 0;
        ListNode a = headA;
        ListNode b = headB;
        
        //get each linked list's size
        while(a != null)
        {
            a = a.next;
            sizeA++;
        }
        while(b != null)
        {
            b = b.next;
            sizeB++;
        }
        
        //make sure the two list start from beginning
        a = headA;
        b = headB;
        int sizeDiff = Math.abs(sizeA - sizeB);
        int bigger = 0;
        if(sizeA >= sizeB)
        {
            bigger = sizeA;
            for(int i = 0; i < sizeDiff; i++)
                a = a.next;
        }
        else
        {
            bigger = sizeB;
            for(int i = 0; i < sizeDiff; i++)
                b = b.next;
        }
        
        for(int i = 0; i < bigger - sizeDiff; i++)
        {
            if(a.val == b.val)
                return a;
            a = a.next;
            b = b.next;
        }
        return null;   
    }
}